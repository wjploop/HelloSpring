package com.wjp.hellospring

import com.wjp.hellospring.domain.model.Image
import com.wjp.hellospring.domain.model.Tag
import com.wjp.hellospring.domain.repo.ImageRepo
import com.wjp.hellospring.domain.repo.TagRepo
import org.junit.Before
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

@SpringBootTest
class AppTests {

    @Autowired
    lateinit var imageRepo: ImageRepo

    @Autowired
    lateinit var tagRepo: TagRepo

    @Test
    fun contextLoads() {

    }

    @Before
    fun setup() {
    }

    @Test
    fun testImageList() {
        val page = imageRepo.findByTagId(25, PageRequest.of(1, 2))
        println("res: ${page.content}")
    }

    @Test
    fun testInsertTag() {
        val tag = tagRepo.save(Tag("good life"))
        println("${tag.id} $tag")

    }

    @Test
    fun `image find all`() {
        imageRepo.save(Image("www.baidu.com",null))

        val all = imageRepo.findAll()
        println("all $all")
    }

    @Test
    fun `find all tag`(){
        val tags = tagRepo.findAll()
        print("tags $tags")
    }
}
