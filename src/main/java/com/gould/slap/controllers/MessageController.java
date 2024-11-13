package com.gould.slap.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gould.slap.domain.MessageEntity;
import com.gould.slap.domain.UserEntity;
import com.gould.slap.services.MessageService;

import java.util.List;
import java.util.Optional;

@RestController
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    //Create a new message
    @PostMapping(path = "/messages")
    public ResponseEntity<MessageEntity> createMessage(@RequestBody MessageEntity messageEntity) {
        MessageEntity createdMessage = messageService.createMessage(messageEntity);
        
        return new ResponseEntity<>(createdMessage, HttpStatus.CREATED);
    }

    //Get all messages From a specific sender
    @GetMapping(path = "/messages/sender/{sender}")
    public List<MessageEntity> listSenderMessages(@PathVariable("sender") UserEntity sender) {
        return messageService.findallbySender(sender);
    }
    
    //Get all messages From a specific sender
    @GetMapping(path = "/messages/reciever/{reciever}")
    public List<MessageEntity> listRecieverMessages(@PathVariable("reciever") UserEntity reciever) {
        return messageService.findallbySender(reciever);
    }

    //Get a message by id
    @GetMapping(path = "/messages/{id}")
    public Optional<MessageEntity> listMessageByID(@PathVariable("id") Long id) {
        return messageService.findOne(id);
    }

    //Edit a Message (Partial Update)
    @PatchMapping(path = "/messages/{id}")
    public ResponseEntity<MessageEntity> editMessage(@PathVariable("id") Long id, @RequestBody MessageEntity messageEntity){
        if(!messageService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        MessageEntity updatedMessage = messageService.partialUpdate(id,messageEntity);
        return new ResponseEntity<>(updatedMessage, HttpStatus.OK);
    }

    //Delete a message by ID
    @DeleteMapping(path = "/messages/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable("id") Long id){
        messageService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    

}
