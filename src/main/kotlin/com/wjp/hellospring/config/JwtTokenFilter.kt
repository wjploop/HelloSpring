package com.wjp.hellospring.config

import com.wjp.hellospring.domain.repo.UserRepo
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class JwtTokenFilter(val userRepo: UserRepo, val util: JwtTokenUtil) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        // Get authorization header and validate
        val header: String? = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (header.isNullOrBlank() || !header.startsWith("Bearer")) {
            filterChain.doFilter(request, response)
            return
        }

        // get jwt token and validate
        val token = header.split(" ")[1].trim()
        if (!util.validate(token)) {
            filterChain.doFilter(request, response)
            return
        }

        println("do filter")

        // Get user identity and set it on the spring security context
        val userDetails = userRepo.findByUsername(util.getUsername(token))

        val authentication = UsernamePasswordAuthenticationToken(
            userDetails, null,
            mutableListOf<GrantedAuthority>().also { list ->
                userDetails?.let {
                    list.addAll(
                        it.authorities
                    )
                }
            }
        )
        authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authentication
        filterChain.doFilter(request, response)
    }
}