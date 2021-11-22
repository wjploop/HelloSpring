package com.wjp.hellospring.domain.model

import javax.persistence.*

@Entity
data class Author(
    @Column(unique = true)
    var username: String,
    var email: String,
    var password: String,

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,


    var age: Int = 0,
    var sex: String = "unknown"

)