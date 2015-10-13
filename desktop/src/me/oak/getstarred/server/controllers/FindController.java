package me.oak.getstarred.server.controllers;

import me.oak.getstarred.server.AccountManager;
import me.oak.getstarred.server.FindingManager;
import me.oak.getstarred.server.entites.User;
import me.oak.getstarred.server.replies.FindReply;
import me.oak.getstarred.server.replies.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author White Oak
 */
@RestController
@RequestMapping("/find/")
public class FindController {

    @Autowired public AccountManager accountManager;
    @Autowired public FindingManager findingManager;

    @RequestMapping(method = RequestMethod.POST)
    public FindReply find(@RequestParam(value = "digest") String digest) {
	User user = accountManager.getUser(digest);
	if (user != null) {
	    return findingManager.find(user);
	}
	return new FindReply(Status.ERROR, "no user for this digest");
    }
}
