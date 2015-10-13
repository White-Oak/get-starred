package me.oak.getstarred.server.chat;

import com.esotericsoftware.kryonet.Connection;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor @Getter public class Client {

    private final Connection connection;
    private final int id;
}
