package com.wjp.hellospring.domain.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

@Document(collection = "users")
data class User(
    @Id
    val id: ObjectId,
    @Indexed
    private var username: String,
    private var password: String,
    private val authorities: Set<Role> = setOf(),

    val enabled: Boolean = true
) : UserDetails {

    @Indexed
    var fullName: String = ""

    @CreatedDate
    var createdAt: LocalDateTime?=null

    @LastModifiedDate
    var modifiedAt: LocalDateTime?=null


    override fun getUsername() = username

    override fun getAuthorities() = authorities;

    override fun getPassword() = password

    override fun isAccountNonExpired() = enabled

    override fun isAccountNonLocked() = enabled

    override fun isCredentialsNonExpired() = enabled

    override fun isEnabled() = enabled
}
