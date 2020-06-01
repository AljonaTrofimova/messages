package com.example.demo.controller

import com.example.demo.api.MessageCreationResponse
import com.example.demo.model.LatestMessageWithCount
import com.example.demo.model.Message
import com.example.demo.repository.LatestMessageRepository
import com.example.demo.repository.MessageRepository
import com.example.demo.service.MessageService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus.OK
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

    @ApiOperation(value = "Find the latest message along with it's creation time and the total number of messages saved")
    @GetMapping(value = ["messages/latest-message"])
    @ResponseBody
    @ResponseStatus(value = OK)
    private fun findLatestAndCount(): LatestMessageWithCount? {
        return messageRepository.findLatestMessageWithCount();
    }

    @ApiOperation(value = "Create message")
    @PostMapping(value = ["/messages/message"])
    @ResponseBody
    private fun createMessage(@ApiParam(name = "text", example = "Example message content", required = true)
                              @RequestParam(value = "text", required = true) text: String): MessageCreationResponse? {
        return messageService.create(text)
    }

    @ApiOperation(value = "Find all saved messages")
    @GetMapping(value = ["/messages"])
    @ResponseBody
    @ResponseStatus(value = OK)
    private fun findAllMessages(): Collection<Message>? {
        return messageRepository.findAllMessages()
    }
}