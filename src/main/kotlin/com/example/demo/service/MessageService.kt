package com.example.demo.service

import com.example.demo.api.MessageCreationResponse
import com.example.demo.model.Message
import com.example.demo.repository.MessageRepository
import org.apache.commons.lang3.StringUtils.isBlank
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.*
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service(value = "MESSAGE_SERVICE")
class MessageService {
    @Autowired
    lateinit var messageRepository: MessageRepository

    public fun create(text: String): MessageCreationResponse? {
        var message: Message? = null
        var validationText: String
        if (isBlank(text)) return MessageCreationResponse(UNPROCESSABLE_ENTITY, "Message text should not be empty")
        try {
            message = messageRepository.save(Message(0, text, LocalDateTime.now()))
            validationText = "Message was created"

        } catch (ex: Exception) {
            validationText = "Cannot save message due to $ex"
        }

        return if (message != null) MessageCreationResponse(OK, validationText) else MessageCreationResponse(INTERNAL_SERVER_ERROR, validationText)
    }

}