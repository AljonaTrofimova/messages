package com.example.demo.model

import com.sun.istack.NotNull
import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "message")
data class LatestMessageWithCount(
        @Id
        @GeneratedValue(strategy = IDENTITY)
        @Column(name = "id")
        val id: Long,

        @NotNull
        @Column(name = "text")
        val text: String,

        @NotNull
        @Column(name = "created")
        val created: String,

        @NotNull
        @Column(name = "count")
        val count: Long
)