package com.wjp.hellospring.domain.repo

import com.wjp.hellospring.domain.model.Author
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepo : JpaRepository<Author, Long> {
}