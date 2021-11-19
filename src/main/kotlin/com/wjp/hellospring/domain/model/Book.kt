package com.wjp.hellospring.domain.model

import org.springframework.context.annotation.Primary
import javax.persistence.*

@Entity
data class Book(
    val name: String,
    @OneToOne
    val author: Author
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0
}