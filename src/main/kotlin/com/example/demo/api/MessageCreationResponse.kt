package com.example.demo.api

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.HttpStatus
import java.io.Serializable

class MessageCreationResponse(@field:JsonProperty(value = "status") val httpStatus: HttpStatus,
                              @field:JsonProperty(value = "validationMessage") val validationText: String
) : Serializable