package com.tomcatwang.blockchain.core.manager;


import com.tomcatwang.blockchain.core.model.MessageEntity;
import com.tomcatwang.blockchain.core.repository.MessageRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tomcatwang wrote on 2019/08/19.
 */
@Component
public class MessageManager {
    @Resource
    private MessageRepository messageRepository;

    public List<MessageEntity> findAll() {
        return messageRepository.findAll();
    }

    public List<String> findAllContent() {
        return findAll().stream().map(MessageEntity::getContent).collect(Collectors.toList());
    }

    public MessageEntity findById(String id) {
        return messageRepository.findByMessageId(id);
    }
}
