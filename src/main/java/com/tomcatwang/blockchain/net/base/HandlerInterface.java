package com.tomcatwang.blockchain.net.base;

import com.tomcatwang.blockchain.net.packet.BlockPacket;
import org.tio.core.ChannelContext;

/**
 * 业务处理器接口
 * @author wuweifeng
 */
public interface HandlerInterface {

	/**
	 * handler方法在此封装转换
	 * @param packet packet
	 * @param channelContext channelContext
	 * @return Object对象
	 * @throws Exception  Exception
	 */
	Object handler(BlockPacket packet, ChannelContext channelContext) throws Exception;

}
