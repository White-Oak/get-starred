package me.oak.getstarred.server.messages;

import me.oak.getstarred.server.managers.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author White Oak
 */
@Component
public class Managers {

    @Autowired AccountManager accountManager;

}
