package com.example.demo.model

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "message")
class LatestMessageWithCount(id: Long, text: String, created: LocalDateTime, @Column(name = "total_saved_messages") val count: Long) : AbstractMessage(id, text, created) {
}