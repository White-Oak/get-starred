package me.oak.getstarred.server.controllers;

import me.oak.getstarred.server.AccountManager;
import me.oak.getstarred.server.replies.RegisterReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author White Oak
 */
@RestController
@RequestMapping("/users/")
public class UserController {

    @Autowired public AccountManager accountManager;

    @RequestMapping(method = RequestMethod.POST)
    public RegisterReply register(@RequestParam(value = "login") String login,
				  @RequestParam(value = "password") String password) {
	RegisterReply registrationReply = accountManager.tryRegister(login, password);
	return registrationReply;
    }

}
