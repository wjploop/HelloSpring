package com.wjp.hellospring.api

import com.wjp.hellospring.domain.dto.AuthorRequest
import com.wjp.hellospring.domain.model.Author
import com.wjp.hellospring.domain.repo.AuthorRepo
import com.wjp.hellospring.domain.repo.BookRepo
import org.springframework.data.domain.PageRequest
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
class Api(val authorRepo: AuthorRepo, val bookRepo: BookRepo) {


    @RequestMapping(value = ["author/create"],method = [RequestMethod.POST])
    private fun createAuthor(@Validated @RequestBody authorRequest: AuthorRequest):Author{
        val author = authorRepo.save(Author(authorRequest.username, authorRequest.email))
        println("save $author")
        return author
    }

    @RequestMapping("authors")
    fun authors() = authorRepo.findAll()

    @RequestMapping("books")
    fun books(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ) =
        bookRepo.findAll(PageRequest.of(page, size))

    @RequestMapping("hello")
    fun hello() = "你好啊"


}