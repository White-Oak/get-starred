package me.oak.getstarred.server.controllers;

import me.oak.getstarred.server.entites.User;
import me.oak.getstarred.server.managers.AccountManager;
import me.oak.getstarred.server.managers.FindingManager;
import me.oak.getstarred.server.replies.ReadyReply;
import me.oak.getstarred.server.replies.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author White Oak
 */
@RestController
@RequestMapping("/ready/")
public class ReadyController {

    @Autowired public AccountManager accountManager;
    @Autowired public FindingManager findingManager;

    @RequestMapping(method = RequestMethod.POST)
    public ReadyReply ready(@RequestParam(value = "digest") String digest) {
	User user = accountManager.getUser(digest);
	if (user != null) {
	    return findingManager.ready(user);
	}
	return new ReadyReply(Status.ERROR, "no user for this digest", null);
    }
}
