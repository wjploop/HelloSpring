package com.wjp.hellospring.config

import com.wjp.hellospring.domain.model.Author
import com.wjp.hellospring.domain.model.Book
import com.wjp.hellospring.domain.repo.AuthorRepo
import com.wjp.hellospring.domain.repo.BookRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InitData(val authorRepo: AuthorRepo, val bookRepo: BookRepo) {

    @Bean
    fun commandLine() = CommandLineRunner {
        val admin = Author("wjp", "wjploop@163.com","qwer")

        authorRepo.save(admin)

        bookRepo.saveAll((1..5).map { Book("Live to death $it", author = admin) }.toList())
    }

}