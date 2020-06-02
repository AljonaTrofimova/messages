package com.example.demo.repository

import com.example.demo.model.AbstractMessage
import com.example.demo.model.LatestMessageWithCountV1
import com.example.demo.model.LatestMessageWithCountV2
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository("ABSTRACT_MESSAGE_REPOSITORY")
interface AbstractMessageRepository : JpaRepository<AbstractMessage, Long> {
    @Query(
        value = "select top 1 (select count(*) from message) as total_saved_messages, * from message group by(id, text, created) ORDER BY CREATED DESC;",
        nativeQuery = true
    )
    fun findLatestMessageWithCount(): LatestMessageWithCountV1?

    fun findTopByOrderByIdDesc(): LatestMessageWithCountV2?
}