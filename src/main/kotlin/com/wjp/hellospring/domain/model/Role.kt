package com.wjp.hellospring.domain.model

import org.springframework.security.core.GrantedAuthority

data class Role(private val authority: String? = null) : GrantedAuthority {

    override fun getAuthority() = authority

    companion object {
        const val USER_ADMIN = "user_admin"
        const val AUTHOR_ADMIN = "author_admin"
        const val BOOK_ADMIN = "book_admin"
    }
}
