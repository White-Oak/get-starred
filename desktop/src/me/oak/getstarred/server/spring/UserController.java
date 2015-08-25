package me.oak.getstarred.server.spring;

import me.oak.getstarred.server.AccountManager;
import me.oak.getstarred.server.spring.replies.RegisterReply;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author White Oak
 */
@RestController
@RequestMapping("/users/")
public class UserController {

    @RequestMapping(method = RequestMethod.POST)
    public RegisterReply register(@RequestParam(value = "login") String login,
				  @RequestParam(value = "password") String password) {
	RegisterReply registrationReply = AccountManager.INSTANCE.tryRegister(login, password);
	return registrationReply;
    }

}
