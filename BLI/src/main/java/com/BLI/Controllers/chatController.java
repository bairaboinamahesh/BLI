//package com.BLI.Controllers;
//
//import org.apache.logging.log4j.message.Message;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class chatController {
//
//    @Autowired
//    SimpMessagingTemplate simpMessagingTemplate;
//
//    @MessageMapping("/application")
//    @SendTo("/all/messages")
//    Message send(final Message message) throws Exception{
//        return message;
//    }
////
////    public void sendToSpecificUser(@payload Message message){
////        simpMessagingTemplate.convertAndSendToUser(message.getTo(),"/specific", message);
////    }
//}
