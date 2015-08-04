package me.oak.getstarred.server;

import lombok.Getter;

/**
 *
 * @author White Oak
 */
public class ServerContext {

    @Getter private final ServerNetwork network = new ServerNetwork(this);
}
