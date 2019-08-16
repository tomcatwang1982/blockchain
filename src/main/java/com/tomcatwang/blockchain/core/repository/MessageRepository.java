package com.tomcatwang.blockchain.core.repository;


import com.tomcatwang.blockchain.core.model.MessageEntity;

/**
 * @author tomcatwang wrote on 2019/08/19.
 */
public interface MessageRepository extends BaseRepository<MessageEntity> {
    /**
     * 删除一条记录
     * @param messageId  messageId
     */
    void deleteByMessageId(String messageId);

    /**
     * 查询一个
     * @param messageId messageId
     * @return MessageEntity
     */
    MessageEntity findByMessageId(String messageId);
}
