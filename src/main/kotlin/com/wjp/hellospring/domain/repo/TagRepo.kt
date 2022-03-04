package com.wjp.hellospring.domain.repo

import com.wjp.hellospring.domain.model.Tag
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import javax.transaction.Transactional

@Transactional
interface TagRepo : JpaRepository<Tag, Long> {

    @Query(
        "select t.id,t.name from tag t " +
                " join image_tag_mapping itm on t.id = itm.tag_id" +
                " where itm.image_id = ?", nativeQuery = true
    )
    fun findByImageId(imageId: Long): List<Tag>
}