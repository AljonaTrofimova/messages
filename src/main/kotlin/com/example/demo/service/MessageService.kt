package com.example.demo.service

import com.example.demo.model.LatestMessageWithCountV2
import com.example.demo.model.Message
import com.example.demo.repository.AbstractMessageRepository
import com.example.demo.repository.MessageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service(value = "MESSAGE_SERVICE")
class MessageService {
    @Autowired
    lateinit var messageRepository: MessageRepository

    fun findLastMessageWithCount(): LatestMessageWithCountV2? {
        val message: Message = messageRepository.findTopByOrderByIdDesc() ?: return null
        return LatestMessageWithCountV2(message.id, message.text, message.created, messageRepository.count())
    }

    fun create(text: String): Boolean {
        var message: Message? = null
        try {
            message = messageRepository.save(Message(0, text, LocalDateTime.now()))
            println("Message was created")

        } catch (ex: Exception) {
            println("Cannot create message due to $ex") // todo logs
        }
        return message != null // or try to fetch created object by id and return it if success
    }

}