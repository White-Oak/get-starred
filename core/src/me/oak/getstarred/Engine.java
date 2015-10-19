package me.oak.getstarred;

import com.annimon.stream.Objects;
import com.annimon.stream.Optional;
import java.util.Collection;
import java.util.LinkedHashMap;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.oak.getstarred.network.*;
import me.oak.getstarred.network.messages.FindMessage;
import me.oak.getstarred.network.messages.ReadyMessage;
import me.oak.getstarred.screens.*;
import me.oak.getstarred.server.chat.messages.ChatMessage;
import me.oak.getstarred.server.replies.*;
import me.whiteoak.minlog.Log;
import spaceisnear.game.ui.FlashMessage;
import spaceisnear.game.ui.SideTextPanel;
import spaceisnear.game.ui.core.Corev3;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor public class Engine {

    private final Network network;
    private final Corev3 corev3;
    private LoginReply loginReply;

    private boolean findingMatch;
    private long lastTimeAskedToFound;

    private final ChatClient chatClient = new ChatClient(this);
    private SideTextPanel chatPanel;
    private User lobbyUser;

    public void start() {
	Thread thread = new Thread(this::proccessNetwork, "network");
	thread.start();
	chatClient.connect();
    }

    public void addToChatPanel(ChatMessage chatMessage) {
	if (chatPanel != null) {
	    chatPanel.add(chatMessage.toString());
	}
    }

    private void proccessNetwork() {
	while (true) {
	    network.sendQueued();
	    Collection<Reply> replies = network.processReceived();
	    if (replies != null) {
		replies.forEach(this::processReply);
	    }
	    for (Action action : actions.values()) {
		if (!action.finished) {
		    action.act();
		} else {
		    actions.removeAction(action);
		}
	    }
	    try {
		Thread.sleep(200L);
	    } catch (InterruptedException ex) {
		Log.error("client", "While iterating through casual engine update cycle", ex);
	    }
	}
    }
    private Actions actions = new Actions();

    @RequiredArgsConstructor private class Action {

	private long lastTimeActed;
	private final Runnable toRun;
	private final long delta;
	private final String id;
	@Setter private boolean finished;

	{
	    lastTimeActed = System.currentTimeMillis();
	}

	@Override
	public int hashCode() {
	    int hash = 7;
	    hash = 53 * hash + Objects.hashCode(this.id);
	    return hash;
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
		return true;
	    }
	    if (obj == null) {
		return false;
	    }
	    if (getClass() != obj.getClass()) {
		return false;
	    }
	    final Action other = (Action) obj;
	    return Objects.equals(this.id, other.id);
	}

	void act() {
	    if (!finished) {
		if (System.currentTimeMillis() - lastTimeActed > delta) {
		    toRun.run();
		    lastTimeActed = System.currentTimeMillis();
		}
	    }
	}
    }

    private class Actions extends LinkedHashMap<String, Action> {

	public boolean containsAction(String id) {
	    return containsKey(id);
	}

	public Optional<Action> getAction(String id) {
	    return Optional.ofNullable(get(id));
	}

	public void putAction(Action action) {
	    put(action.id, action);
	}

	public Action removeAction(Action key) {
	    return super.remove(key.id);
	}

	public void finishIfPresent(String id) {
	    getAction(id).ifPresent(action -> action.setFinished(true));
	}

    }

    public void processReply(Reply reply) {
	flash(reply);
	switch (reply.getType()) {
	    case REGISTER:
		final NothingScreen nothingScreen = new NothingScreen(network);
		corev3.setNextScreen(nothingScreen);
		break;
	    case LOGIN:
		loginReply = (LoginReply) reply;
		chatClient.handshake(loginReply.getUser().getId());
		network.setDigest(loginReply.getDigest());
		final MainMenuScreen mainMenuScreen = new MainMenuScreen(network);
		corev3.setNextScreen(mainMenuScreen);
		break;
	    case FINDING: {
		Statusable statusable = (Statusable) reply;
		findingMatch = statusable.getStatus() == Statusable.Status.ERROR;
		if (findingMatch) {
		    if (!actions.containsAction("finding match")) {
			Action action = new Action(() -> {
			    FindMessage findMessage = new FindMessage(loginReply.getDigest());
			    network.queue(findMessage);
			}, 3000L, "finding match");
			actions.putAction(action);
		    }
		} else {
		    actions.finishIfPresent("finding match");
		    FindReply fr = (FindReply) reply;
		    lobbyUser = fr.getUser();
		    chatPanel = new SideTextPanel();
		    corev3.addToMainStage(chatPanel);
		    chatPanel.setActivationListener(actor -> {
			if (lobbyUser.getId() > 0) {
			    String text = chatPanel.getTextField().getText();
			    chatClient.message(loginReply.getUser().getId(), lobbyUser.getId(), text);
			}
		    });
		    LobbyScreen lobbyScreen = new LobbyScreen(loginReply.getUser(), lobbyUser, network);
		    corev3.setNextScreen(lobbyScreen);
		}
	    }
	    break;
	    case MATCH_READY: {
		Statusable statusable = (Statusable) reply;
		if (statusable.getStatus() == Statusable.Status.ERROR) {
		    if (!actions.containsAction("waiting for match to be ready")) {
			Action action = new Action(() -> {
			    ReadyMessage readyMessage = new ReadyMessage(loginReply.getDigest());
			    network.queue(readyMessage);
			}, 3000L, "waiting for match to be ready");
			actions.putAction(action);
		    }
		} else {
		    actions.finishIfPresent("waiting for match to be ready");
		    ReadyReply rr = (ReadyReply) reply;
		    corev3.setNextScreen(new MatchScreen());
		}
	    }
	    break;
	}
    }

    private void flash(Reply reply) {
	FlashMessage.Level level;
	if (reply instanceof Statusable) {
	    level = transmute(((Statusable) reply).getStatus());
	} else {
	    level = FlashMessage.Level.DEBUG;
	}
	final FlashMessage flashMessage = new FlashMessage(level, reply.getMessage());
	corev3.addFlashMessage(flashMessage);
    }

    private FlashMessage.Level transmute(Statusable.Status status) {
	switch (status) {
	    case DEBUG:
		return FlashMessage.Level.DEBUG;
	    case INFO:
		return FlashMessage.Level.INFO;
	    case ERROR:
		return FlashMessage.Level.ERROR;
	    case SUCCESS:
		return FlashMessage.Level.SUCCESS;
	    case WARNING:
		return FlashMessage.Level.WARNING;
	    default:
		throw new AssertionError("wut");
	}
    }

}
