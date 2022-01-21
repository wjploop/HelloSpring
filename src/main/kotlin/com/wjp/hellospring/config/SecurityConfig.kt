package com.wjp.hellospring.config

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.firewall.DefaultHttpFirewall
import org.springframework.security.web.firewall.HttpFirewall
import org.springframework.security.web.firewall.HttpStatusRequestRejectedHandler
import org.springframework.security.web.firewall.RequestRejectedHandler

@Configuration
@EnableWebSecurity
class SecurityConfig() : WebSecurityConfigurerAdapter() {

    @Bean
    fun initDb(): CommandLineRunner {
        return CommandLineRunner {
        }
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

//    override fun configure(auth: AuthenticationManagerBuilder) {
//        auth.userDetailsService(UserDetailsService { username ->
//            userRepo.findByUsername(username).orElseThrow {
//                throw UsernameNotFoundException("User: $username not found")
//            }
//        });
//    }

    override fun configure(http: HttpSecurity) {

        http.cors().and().csrf().disable()

//        http.httpBasic()


        // add jwt token filter before spring
//        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter::class.java)

    }

    @Bean
    override fun userDetailsService(): UserDetailsService {
        var builder = User.builder().passwordEncoder(NoOpPasswordEncoder.getInstance()::encode)
        var user = builder.username("user").password("123").roles("user").build()
        // 有两种角色
        var admin = builder.username("admin").password("123").roles("user", "admin").build()
        return InMemoryUserDetailsManager(user, admin)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = NoOpPasswordEncoder.getInstance()


    // 默认的StrictHttpFirewall太严格了，暂时不用
    @Bean
    fun defaultHttpFireWall(): HttpFirewall = DefaultHttpFirewall()
}