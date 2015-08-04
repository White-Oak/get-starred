package me.oak.getstarred.server;

/**
 *
 * @author White Oak
 */
public class ServerCore implements Runnable {

    ServerContext serverContext = new ServerContext();

    public ServerCore() {
	serverContext.getNetwork().host();
    }

    @Override
    public void run() {
	while (true) {
	    serverContext.getNetwork().processQueues();
	}
    }
}
