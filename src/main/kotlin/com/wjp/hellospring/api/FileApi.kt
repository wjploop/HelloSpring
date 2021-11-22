package com.wjp.hellospring.api

import com.wjp.hellospring.domain.entity.ResultCode
import com.wjp.hellospring.domain.entity.ResultVO
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.awt.print.Pageable
import java.io.File
import java.io.IOException
import kotlin.io.path.Path

@RestController
@RequestMapping("files")
class FileApi {

    val path = "files/"

    @GetMapping()
    fun files(extendName: String?) = File(path).listFiles().map { it.name }
        .filter { if (extendName == null) true else it.substringAfterLast(".").matches(Regex(extendName)) }.toList()

    @GetMapping("{file}")
    @ResponseBody
    fun file(@PathVariable("file") filename: String): ResponseEntity<File> {
        val file = File("$path$filename")
        if (!File("$path$filename").exists()) {
            throw Exception("该文件不存在")
        }
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=${file.name}")
            .body(file)
    }

    @PostMapping
    fun file(@RequestParam("files") files: List<MultipartFile>): ResultVO<Any> {
        try {
            files.forEach {
                it.transferTo(Path("$path${it.originalFilename}"))
            }
        } catch (e: IOException) {
            e.printStackTrace()
            return ResultVO(ResultCode.failed.code, "上传失败")
        }
        return ResultVO("上传成功")
    }
}