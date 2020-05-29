package com.example.demo.repository

import com.example.demo.model.LatestMessageWithCount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository("LATEST_MESSAGE_REPOSITORY")
interface LatestMessageRepository : JpaRepository<LatestMessageWithCount, Long> {
    @Query(value = "select top 1 (select count(*) from message) as total_saved_messages, * from message group by(id, text, created) ORDER BY CREATED DESC;",
            nativeQuery = true)
    fun findLatestMessageWithCount(): LatestMessageWithCount?
}