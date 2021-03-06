package com.wjp.hellospring.config

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig() : WebSecurityConfigurerAdapter() {

    val log = LoggerFactory.getLogger(SecurityConfig::class.java)

    @Bean
    fun initDb(): CommandLineRunner {
        return CommandLineRunner {
            log.debug("command line")
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

    override fun configure(auth: AuthenticationManagerBuilder?) {
        super.configure(auth)
    }

    override fun configure(http: HttpSecurity) {



//        http.cors().and().
        http.csrf().disable()

        http.httpBasic()

        // add jwt token filter before spring
//        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter::class.java)

    }

    @Bean
    override fun userDetailsService(): UserDetailsService {
        var builder = User.builder()
//            .passwordEncoder(NoOpPasswordEncoder.getInstance()::encode)
//        var builder = User.builder().passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
        var user = builder.username("user").password("123").roles("USER").build()
        // ???????????????
        var admin = builder.username("admin").password("123").roles("USER", "ADMIN").build()
        return InMemoryUserDetailsManager(user, admin)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = NoOpPasswordEncoder.getInstance()


    // ?????????StrictHttpFirewall???????????????????????????
//    @Bean
//    fun defaultHttpFireWall(): HttpFirewall = DefaultHttpFirewall()
}