package com.example.demo.controller

import com.example.demo.model.LatestMessageWithCountV1
import com.example.demo.model.LatestMessageWithCountV2
import com.example.demo.model.Message
import com.example.demo.repository.MessageRepository
import com.example.demo.service.MessageService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.apache.commons.lang3.StringUtils.isBlank
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.*

@CrossOrigin
@Api("Message controller")
@RestController("MESSAGE_CONTROLLER")
class MessageController {
    @Autowired
    @Qualifier("MESSAGE_SERVICE")
    lateinit var messageService: MessageService

    @Autowired
    @Qualifier("MESSAGE_REPOSITORY")
    lateinit var messageRepository: MessageRepository

    @ApiOperation(value = "V2 Find the latest message along with it's creation time and the total number of messages saved")
    @GetMapping(value = ["messages/v2/latest-message"])
    @ResponseBody
    @ResponseStatus(value = OK)
    private fun findLatestAndCountV2(): LatestMessageWithCountV2? {
        val message: LatestMessageWithCountV2 = messageRepository.findTopByOrderByIdDesc()
        message.count = messageRepository.findAllMessages().count().toLong()
        return message
    }

    @ApiOperation(value = "V1 Find the latest message along with it's creation time and the total number of messages saved")
    @GetMapping(value = ["messages/v1/latest-message"])
    @ResponseBody
    @ResponseStatus(value = OK)
    private fun findLatestAndCountV1(): LatestMessageWithCountV1? {
        return messageRepository.findLatestMessageWithCount()
    }

    @ApiOperation(value = "Create message")
    @PostMapping(value = ["/messages/message"])
    @ResponseBody
    private fun createMessage(
        @ApiParam(name = "text", example = "Example message content", required = true)
        @RequestParam(value = "text", required = true) text: String
    ): HttpStatus? {
        if (isBlank(text)) return UNPROCESSABLE_ENTITY
        else if (!messageService.create(text)) return INTERNAL_SERVER_ERROR
        return OK
    }

    @ApiOperation(value = "Find all saved messages")
    @GetMapping(value = ["/messages"])
    @ResponseBody
    @ResponseStatus(value = OK)
    private fun findAllMessages(): Collection<Message>? {
        return messageRepository.findAllMessages()
    }
}