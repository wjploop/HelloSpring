package com.wjp.hellospring.api

import com.wjp.hellospring.domain.dto.AuthorRequest
import com.wjp.hellospring.domain.dto.AuthorUpdateRequest
import com.wjp.hellospring.domain.entity.ResultCode
import com.wjp.hellospring.domain.entity.ResultVO
import com.wjp.hellospring.domain.model.Author
import com.wjp.hellospring.domain.repo.AuthorRepo
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource
import javax.validation.Valid

@RestController
@RequestMapping("author")
@PreAuthorize("hasAnyRole('ROLE_USER')")
class AuthorController {

    @Resource
    lateinit var authorRepo: AuthorRepo

    @RequestMapping("list")
    fun authors(): MutableList<Author> = authorRepo.findAll()


    @PostMapping("create")
    private fun createAuthor(@Valid request: AuthorRequest): ResultVO<Any> {

//        authorRepo.findByEmail(email = request.email)?.let {
//            return ResultVO(ResultCode.invalid_param.code, "该邮箱已注册过了")
//        }
//
        authorRepo.findByUsername(username = request.username)?.let {
            return ResultVO(ResultCode.invalid_param.code, "该用户名已存在")
        }

        val author = authorRepo.save(
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

    @PostMapping("update")
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
}