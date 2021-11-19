package com.wjp.hellospring.domain.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.Email

@Entity
data class Author(
    val name: String,
    @Email
    val email: String,
) {
    val age: Int = 0
    val sex: String = "unknown"

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0

}