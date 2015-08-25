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

    @RequestMapping(method = RequestMethod.PUT)
    public RegisterReply register(@RequestParam(value = "login", required = true) String login,
				  @RequestParam(value = "password", required = true) String password) {
	RegisterReply registrationReply = AccountManager.INSTANCE.tryRegister(login, password);
	return registrationReply;
    }

}
