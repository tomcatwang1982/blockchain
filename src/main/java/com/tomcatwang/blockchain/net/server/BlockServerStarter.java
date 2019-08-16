package com.tomcatwang.blockchain.net.server;

import com.tomcatwang.blockchain.net.common.Const;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tio.server.AioServer;
import org.tio.server.ServerGroupContext;
import org.tio.server.intf.ServerAioHandler;
import org.tio.server.intf.ServerAioListener;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * server启动器
 *
 * @author wuweifeng wrote on 2018/3/12.
 */
@Component
public class BlockServerStarter {

    @Value("${peer.server.port}")
    private int peerServerPort;

    @PostConstruct
    public void serverStart() throws IOException {
        ServerAioHandler serverAioHandler = new BlockServerAioHandler();
        ServerAioListener serverAioListener = new BlockServerAioListener();
        ServerGroupContext serverGroupContext = new ServerGroupContext(serverAioHandler, serverAioListener);
        AioServer aioServer = new AioServer(serverGroupContext);
        //本机启动服务
        aioServer.start(null, peerServerPort);
    }
}
