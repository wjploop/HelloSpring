package com.wjp.hellospring.service

import org.springframework.security.access.annotation.Secured
import org.springframework.stereotype.Service

@Service
class ZooService {

    @Secured("role_vip")
    fun enjoy(): String {
        return "享受VIP服务"
    }

    fun justLook() :String{
        return "看看而已"
    }
}