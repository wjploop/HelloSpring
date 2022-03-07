package com.wjp.hellospring.log

import org.slf4j.LoggerFactory

object Logger {

    private val logger = LoggerFactory.getLogger(Logger.toString())

    fun info(msg: String) {
        val stackTraceElement = Thread.currentThread().stackTrace
        val e = stackTraceElement[3]
        logger.info("{} {}: {}", e.className.split(".").lastOrNull(), e.methodName, msg)
    }

    fun warn(msg: String) {
        val stackTraceElement = Thread.currentThread().stackTrace
        val e = stackTraceElement[3]
        logger.warn("{} {}: {}", e.className.split(".").lastOrNull(), e.methodName, msg)
    }

    fun error(msg: String) {
        val stackTraceElement = Thread.currentThread().stackTrace
        val e = stackTraceElement[3]
        logger.error("{} {}: {}", e.className.split(".").lastOrNull(), e.methodName, msg)
    }

}