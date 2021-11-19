package com.wjp.hellospring.advice

import com.fasterxml.jackson.databind.ObjectMapper
import com.wjp.hellospring.domain.entity.ResultVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice

@RestControllerAdvice
//    (basePackages = ["com.wjp.hellospring"])
class ResponseControllerAdvice : ResponseBodyAdvice<Any> {


    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        return returnType.genericParameterType != ResultVO::class
    }

    override fun beforeBodyWrite(
        body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse
    ): Any? {
        if (returnType.genericParameterType == String::class.java) {
            val mapper = ObjectMapper()
            return mapper.writeValueAsString(ResultVO(body))
        }
        if (body is ResultVO<*>) {
            return body
        }
        // 处理BaseController的error()
        // 应该有更好的方法
        if (returnType.member.name == "error") {
            body as Map<String, Any>
            return ResultVO<Any>(code = (body["status"]) as Int, msg = "${body["status"]} ${body["path"]}")
        }

        return ResultVO(data = body)
    }

}