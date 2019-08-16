package com.tomcatwang.blockchain.core.sqlparser;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.tomcatwang.blockchain.block.Operation;
import com.tomcatwang.blockchain.common.CommonUtil;
import com.tomcatwang.blockchain.core.model.MessageEntity;
import com.tomcatwang.blockchain.core.repository.MessageRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 解析语句入库的具体实现，Message表的
 * @author tomcatwang wrote on 2019/08/19.
 */
@Service
public class MessageSqlParser extends AbstractSqlParser<MessageEntity> {
    @Resource
    private MessageRepository messageRepository;

    @Override
    public void parse(byte operation, String messageId, MessageEntity entity) {
         if (Operation.ADD == operation) {
        	 entity.setCreateTime(CommonUtil.getNow());
             entity.setMessageId(messageId);
             messageRepository.save(entity);
         } else if (Operation.DELETE == operation) {
             messageRepository.deleteByMessageId(messageId);
         } else if (Operation.UPDATE == operation) {
             MessageEntity messageEntity = messageRepository.findByMessageId(messageId);
             BeanUtil.copyProperties(entity, messageEntity, CopyOptions.create().setIgnoreNullValue(true).setIgnoreProperties("id", "createTime"));
             messageRepository.save(messageEntity);
         }
    }

    @Override
    public Class getEntityClass() {
        return MessageEntity.class;
    }

}
