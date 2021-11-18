package com.wjp.hellospring.domain.repo

import com.wjp.hellospring.domain.exeption.NotFoundException
import com.wjp.hellospring.domain.model.User
import org.bson.types.ObjectId
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
@CacheConfig(cacheNames = ["users"])
interface UserRepo : MongoRepository<User, ObjectId> {

    @CacheEvict(allEntries = true)
    override fun <S : User?> saveAll(entities: MutableIterable<S>): MutableList<S>

    @Caching(
        evict = [
            CacheEvict(key = "#p0.id", condition = "p0.id!=null"),
            CacheEvict(key = "#p0.username", condition = "p0.username!=null")
        ],
    )
    override fun <S : User?> save(entity: S): S

    @Cacheable
    fun getById(id: ObjectId): User {
        return findById(id).let {
            if (!it.isEmpty && it.get().isEnabled) {
                it.get()
            } else {
                throw NotFoundException(clz = User::class.java, id = id)
            }
        }
    }

    @Cacheable
    fun findByUsername(username: String): User?
}