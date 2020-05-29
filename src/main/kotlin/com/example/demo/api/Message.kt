package com.example.demo.api

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

class Message(@field:JsonProperty(value = "id") val id: Long,
              @field:JsonProperty(value = "text") val text: String,
              @field:JsonProperty(value = "created") val created: String
) : Serializable {

}