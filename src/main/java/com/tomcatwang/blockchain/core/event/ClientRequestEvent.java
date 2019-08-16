package com.tomcatwang.blockchain.core.event;

import com.tomcatwang.blockchain.net.packet.BlockPacket;
import org.springframework.context.ApplicationEvent;

/**
 * 客户端对外发请求时会触发该Event
 * @author tomcatwang wrote on 2019/08/19.
 */
public class ClientRequestEvent extends ApplicationEvent {
    public ClientRequestEvent(BlockPacket blockPacket) {
        super(blockPacket);
    }
}
