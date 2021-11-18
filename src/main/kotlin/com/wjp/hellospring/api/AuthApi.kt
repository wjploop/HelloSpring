package com.wjp.hellospring.api

import com.wjp.hellospring.config.JwtTokenUtil
import com.wjp.hellospring.domain.dto.AuthRequest
import com.wjp.hellospring.domain.dto.UserView
import com.wjp.hellospring.domain.model.User
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException
import javax.validation.Valid

@RestController
@RequestMapping("api/public")
class AuthApi(val authenticationManager: AuthenticationManager, val jwtTokenUtil: JwtTokenUtil) {

    @PostMapping("login",)
    fun login(@Valid request: AuthRequest): ResponseEntity<UserView> {
        try {
            val authenticate =
                authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken(
                        request.username,
                        request.password
                    )
                )

            val user = authenticate.principal as User

            return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(user))
                .body(UserView(user.id.toString(), user.username,user.fullName))

        } catch (ex: Exception) {
            ex.printStackTrace()
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }
    }
}