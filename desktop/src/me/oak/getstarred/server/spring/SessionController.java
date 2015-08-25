package me.oak.getstarred.server.spring;

import me.oak.getstarred.server.AccountManager;
import me.oak.getstarred.server.spring.replies.LoginReply;
import me.oak.getstarred.server.spring.replies.Reply;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author White Oak
 */
@RestController
@RequestMapping(name = "/session")
public class SessionController {

    @RequestMapping(method = RequestMethod.POST)
    public LoginReply login(@RequestParam(value = "login") String login,
			    @RequestParam(value = "password") String password) {
	LoginReply loginReply = AccountManager.INSTANCE.tryLogin(login, password);
	return loginReply;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Reply logout(@RequestParam(value = "digest") String digest) {
	return AccountManager.INSTANCE.tryLogout(digest);
    }
}
