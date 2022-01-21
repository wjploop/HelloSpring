package com.wjp.hellospring.api

import com.wjp.hellospring.domain.dto.AuthorRequest
import com.wjp.hellospring.domain.dto.AuthorUpdateRequest
import com.wjp.hellospring.domain.entity.ResultCode
import com.wjp.hellospring.domain.entity.ResultVO
import com.wjp.hellospring.domain.model.Author
import com.wjp.hellospring.domain.repo.AuthorRepo
import com.wjp.hellospring.domain.repo.BookRepo
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

//@RestController
class Api(val authorRepo: AuthorRepo, val bookRepo: BookRepo) {

    @PostMapping("author/create")
    private fun createAuthor(@Valid request: AuthorRequest): ResultVO<Any> {

//        authorRepo.findByEmail(email = request.email)?.let {
//            return ResultVO(ResultCode.invalid_param.code, "该邮箱已注册过了")
//        }
//
        authorRepo.findByUsername(username = request.username)?.let {
            return ResultVO(ResultCode.invalid_param.code, "该用户名已存在")
        }

        val author =  authorRepo.save(
            Author(
                request.username,
                request.email,
                password = request.password,
                sex = request.sex,
                age = request.age,
            )
        )
        return ResultVO(author)
    }

    @PostMapping("author/update")
    private fun updateAuthor(@Valid request: AuthorUpdateRequest): Author {
        val author = authorRepo.findById(request.id).orElseThrow { Exception("id $request not exit") }
        request.email?.let {
            author.email = it
        }
        request.username?.let {
            author.username = it
        }
        request.password?.let {
            author.password = it
        }
        request.age?.let {
            author.age = it
        }
        request.sex?.let {
            author.sex = it
        }
        return author
    }

    @RequestMapping("authors")
    fun authors(): MutableList<Author> = authorRepo.findAll()





}