package com.example.demo.model

import com.sun.istack.NotNull
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class AbstractMessage(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    open val id: Long,

    @NotNull
    @Column(name = "text")
    open val text: String,

    @NotNull
    @Column(name = "created")
    open val created: LocalDateTime

)