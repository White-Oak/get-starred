package me.oak.getstarred.server.spring;

import me.oak.getstarred.server.spring.replies.RegisterReply;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author White Oak
 */
@RestController
public class RegisterController {

    @RequestMapping("/register")
    public RegisterReply greeting(@RequestParam(value = "login", defaultValue = "") String login,
				  @RequestParam(value = "password", defaultValue = "") String password) {
	return new RegisterReply("oh yes", "shut up");
    }
}
