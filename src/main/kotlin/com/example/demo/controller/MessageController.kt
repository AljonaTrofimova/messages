package com.example.demo.controller

import com.example.demo.model.LatestMessageWithCountV1
import com.example.demo.model.LatestMessageWithCountV2
import com.example.demo.model.Message
import com.example.demo.repository.AbstractMessageRepository
import com.example.demo.repository.MessageRepository
import com.example.demo.service.MessageService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.apache.commons.lang3.StringUtils.isBlank
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
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

    @Autowired
    @Qualifier("ABSTRACT_MESSAGE_REPOSITORY")
    lateinit var abstractMessageRepository: AbstractMessageRepository

    @ApiOperation(value = "V2 Find the latest message along with it's creation time and the total number of messages saved")
    @GetMapping(value = ["messages/v2/latest-message"])
    @ResponseBody
    @ResponseStatus(value = OK)
    private fun findLatestAndCountV2(): LatestMessageWithCountV2? {
        return messageService.findLastMessageWithCount()
    }

    @ApiOperation(value = "V1 Find the latest message along with it's creation time and the total number of messages saved")
    @GetMapping(value = ["messages/v1/latest-message"])
    @ResponseBody
    @ResponseStatus(value = OK)
    private fun findLatestAndCountV1(): LatestMessageWithCountV1? {
        return abstractMessageRepository.findLatestMessageWithCount()
    }

    @ApiOperation(value = "Create message")
    @PostMapping(value = ["/messages/message"])
    private fun createMessage(
        @ApiParam(name = "text", example = "Example message content", required = false)
        @RequestParam(value = "text", required = false) text: String
    ): ResponseEntity<Unit> {
        if (isBlank(text)) return ResponseEntity.status(UNPROCESSABLE_ENTITY).build()
        else if (!messageService.create(text)) return ResponseEntity.status(INTERNAL_SERVER_ERROR).build()
        return ResponseEntity.status(OK).build()
    }

    @ApiOperation(value = "Find all saved messages")
    @GetMapping(value = ["/messages"])
    @ResponseBody
    @ResponseStatus(value = OK)
    private fun findAllMessages(): Collection<Message>? {
        return messageRepository.findAll()
    }
}