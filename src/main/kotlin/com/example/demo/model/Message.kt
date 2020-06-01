package com.example.demo.model

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "message")
open class Message(id: Long, text: String, created: LocalDateTime) : AbstractMessage(id, text, created)