package me.oak.getstarred.server.spring;

import me.oak.getstarred.server.AccountManager;
import me.oak.getstarred.server.spring.replies.LoginReply;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author White Oak
 */
@RestController
@RequestMapping(name = "/session")
public class SessionController {

    @RequestMapping(method = RequestMethod.PUT)
    public LoginReply login(@RequestParam(value = "login", required = true) String login,
			    @RequestParam(value = "password", required = true) String password) {
	LoginReply loginReply = AccountManager.INSTANCE.tryLogin(login, password);
	return loginReply;
    }
}
