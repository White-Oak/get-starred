package me.oak.getstarred.server.messages;

import me.oak.getstarred.server.KryonetServer;
import me.oak.getstarred.server.managers.AccountManager;
import me.oak.getstarred.server.managers.FindingManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author White Oak
 */
@Component
public class Managers {

    @Autowired AccountManager accountManager;
    @Autowired FindingManager findingManager;
    @Autowired KryonetServer kryonetServer;

}
