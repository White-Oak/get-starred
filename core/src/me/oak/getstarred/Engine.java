package me.oak.getstarred;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import me.oak.getstarred.network.*;
import me.oak.getstarred.network.messages.FindMessage;
import me.oak.getstarred.screens.*;
import me.oak.getstarred.server.chat.messages.ChatMessage;
import me.oak.getstarred.server.replies.*;
import me.whiteoak.minlog.Log;
import spaceisnear.game.ui.ChatPanel;
import spaceisnear.game.ui.FlashMessage;
import spaceisnear.game.ui.core.Corev3;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor public class Engine {

    private final static long TIME_BETWEEN_FINDS = 2000l;

    private final Network network;
    private final Corev3 corev3;
    private LoginReply loginReply;

    private boolean findingMatch;
    private long lastTimeAskedToFound;

    private final ChatClient chatClient = new ChatClient(this);
    private ChatPanel chatPanel;
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
	    if (findingMatch) {
		if (System.currentTimeMillis() - lastTimeAskedToFound > TIME_BETWEEN_FINDS) {
		    FindMessage findMessage = new FindMessage(loginReply.getDigest());
		    network.queue(findMessage);
		    lastTimeAskedToFound = System.currentTimeMillis();
		}
	    }
	    try {
		Thread.sleep(200L);
	    } catch (InterruptedException ex) {
		Log.error("client", "While iterating through casual engine update cycle", ex);
	    }
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
	    case FINDING:
		Statusable statusable = (Statusable) reply;
		if (!findingMatch && statusable.getStatus() == Statusable.Status.ERROR) {
		    lastTimeAskedToFound = System.currentTimeMillis();
		}
		findingMatch = statusable.getStatus() == Statusable.Status.ERROR;
		if (!findingMatch) {
		    FindReply fr = (FindReply) reply;
		    lobbyUser = fr.getUser();
		    chatPanel = corev3.createChatPanel();
		    chatPanel.setActivationListener(actor -> {
			if (lobbyUser.getId() > 0) {
			    String text = chatPanel.getTextField().getText();
			    chatClient.message(loginReply.getUser().getId(), lobbyUser.getId(), text);
			}
		    });
		    LobbyScreen lobbyScreen = new LobbyScreen(loginReply.getUser(), lobbyUser);
		    corev3.setNextScreen(lobbyScreen);
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
