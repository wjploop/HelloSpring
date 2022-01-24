package com.wjp.hellospring.advice

import com.wjp.hellospring.domain.entity.ResultCode
import com.wjp.hellospring.domain.entity.ResultVO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.validation.BindException
import org.springframework.validation.FieldError
import org.springframework.validation.beanvalidation.SpringValidatorAdapter
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException
import java.util.*
import java.util.spi.LocaleServiceProvider
import javax.annotation.Resource

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
class GlobalExceptionHandler {

    var logger: Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)
//
//    @ExceptionHandler(Exception::class, NoHandlerFoundException::class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    fun commonException(e: Exception): ResultVO<String> {
//        return ResultVO(ResultCode.failed, e.message)
//    }
//
//    @ExceptionHandler(ApiException::class)
//    fun apiExceptionHandler(e: ApiException): ResultVO<String> {
//        return ResultVO(e.code, e.message)
//    }
//
//    @ExceptionHandler(RequestRejectedException::class)
//    fun apiExceptionHandler(e: RequestRejectedException): ResultVO<String> {
//        return ResultVO(ResultCode.error, e.message)
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException::class)
//    fun methodArgumentNotValidHandler(ex: MethodArgumentNotValidException): ResultVO<String> {
//        return ResultVO(
//            ResultCode.invalid_param,
//            ex.bindingResult.allErrors.first().defaultMessage
//        )
//    }

    @ExceptionHandler(java.lang.Exception::class)
    fun handleException(e: Exception):ResultVO<String> {
        e.printStackTrace()
        return ResultVO<String>(
            ResultCode.error,
            e.message
        )
    }

    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleNotFound(e: NotImplementedError) = ResultVO<String>(
        ResultCode.error,
        e.message
    )


    @Autowired
    lateinit var messageSource: MessageSource

    @ExceptionHandler(BindException::class)
    fun handleValid(e: BindException) {
        e.printStackTrace()
        println("wolf handler valid ")
        logger.info("hello")
        return e.bindingResult.allErrors
            .map {
                it as FieldError
                "'${messageSource.getMessage(it.field,null, LocaleContextHolder.getLocale())}${it.defaultMessage}"
            }.joinToString()
            .let {
                ResultVO<String>(
                    ResultCode.invalid_param.code,
                    it
                )
            }
    }


}