package com.wjp.hellospring.domain.repo

import com.wjp.hellospring.domain.model.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepo : JpaRepository<Book, Long> {

}