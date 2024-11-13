package com.gould.slap.services;

import com.gould.slap.domain.MessageEntity;
import com.gould.slap.domain.UserEntity;
import com.gould.slap.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    //Create a new message
    public MessageEntity createMessage(MessageEntity messageEntity){
        return messageRepository.save(messageEntity);
    }

    //Save a Message
    public MessageEntity saveMessage(MessageEntity messageEntity){
        return messageRepository.save(messageEntity);
    }

    //Find All Messages
    public List<MessageEntity> findall(){
        return StreamSupport.stream(messageRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    //Find All Messages by Sender (Could maybe use findAllByID??)
    public List<MessageEntity> findallbySender(UserEntity sender){
        return StreamSupport.stream(messageRepository.findAll().spliterator(), false)
        .filter(message -> sender.equals(message.getSender()))
        .toList();
    }

    //Find All Messages by Reciever (Could maybe use findAllByID??)
    public List<MessageEntity> findallbyReciever(UserEntity reciever){
        return StreamSupport.stream(messageRepository.findAll().spliterator(), false)
        .filter(message -> reciever.equals(message.getReceiver()))
        .toList();
    }

    // Find a Message by ID
    public Optional<MessageEntity> findOne(Long id) {
        return messageRepository.findById(id);
    }

    // Check if a Message exists by ID
    public boolean isExists(Long id) {
        return messageRepository.existsById(id);
    }

    //Partial Update
    public MessageEntity partialUpdate(Long id, MessageEntity messageEntity) {
        messageEntity.setMessageId(id);

        return messageRepository.findById(id).map(existingMessage -> {
            Optional.ofNullable(messageEntity.getContent()).ifPresent(existingMessage::setContent);
            //Might need to change the functionality of this to make sure the recievers get properly updated
            Optional.ofNullable(messageEntity.getReceiver()).ifPresent(existingMessage::setReceiver);

            // Save and return the updated Message
            return messageRepository.save(existingMessage);
        }).orElseThrow(() -> new RuntimeException("Message not found"));
    }

    //Delete a message
    public void delete(Long id){
        messageRepository.deleteById(id);
    }
}
