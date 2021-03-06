package com.wjp.hellospring.domain.repo

import com.wjp.hellospring.domain.model.Book
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Repository

@PreAuthorize("hasAnyRole('ROLE_USER')")
interface BookRepo : JpaRepository<Book, Long> {

    override fun <S : Book?> findAll(example: Example<S>, pageable: Pageable): Page<S>

    override fun findAll(): MutableList<Book>

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    override fun <S : Book?> saveAll(entities: MutableIterable<S>): MutableList<S>

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    override fun <S : Book?> save(entity: S): S
}