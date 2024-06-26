package com.alpha.kooing.chat.entity

import com.alpha.kooing.chat.dto.ChatResponseDto
import com.alpha.kooing.chatRoom.entity.ChatRoom
import com.alpha.kooing.user.User
import com.alpha.kooing.util.BaseTimeEntity
import com.alpha.kooing.util.DateUtil
import jakarta.persistence.*
import org.joda.time.DateTime
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity
class Chat(
    @Column(name = "content")
    var content:String,

    @Column(name= "unread")
    var unread:Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    var chatRoom: ChatRoom,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null
):BaseTimeEntity(){

    fun toResponseDto():ChatResponseDto{
        return ChatResponseDto(
            this.id,
            this.unread,
            this.user.id,
            this.chatRoom.id,
            this.content,
            DateUtil.getDateTimeFormat(this.createdAt)
        )
    }
}