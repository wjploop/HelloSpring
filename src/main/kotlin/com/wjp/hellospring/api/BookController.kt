package com.wjp.hellospring.api

import com.wjp.hellospring.domain.model.Book
import com.wjp.hellospring.domain.repo.BookRepo
import org.springframework.data.domain.PageRequest
import org.springframework.data.rest.webmvc.RepositoryRestController
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.annotation.Resource

@RestController
@RequestMapping("/book")
class BookController {

    @Resource
    lateinit var bookRepo: BookRepo

    @RequestMapping("list")
    fun list(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ) =
        bookRepo.findAll(PageRequest.of(page, size))

    @PostMapping("create")
    fun create(@RequestBody book: Book) = bookRepo.save(book)


}