package com.wjp.hellospring.config

import com.wjp.hellospring.domain.model.User
import com.wjp.hellospring.domain.repo.UserRepo
import org.bson.types.ObjectId
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
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(val userRepo: UserRepo, val jwtTokenFilter: JwtTokenFilter) : WebSecurityConfigurerAdapter() {

    @Bean
    fun initDb(): CommandLineRunner {
        return CommandLineRunner {
            userRepo.insert(User(ObjectId(), "wjp", "qwer"))
        }
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(UserDetailsService { username ->
            userRepo.findByUsername(username) ?: throw UsernameNotFoundException("User: $username not found")
        });
    }

    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()

        http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.authorizeRequests()
            .antMatchers("/api/public/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/author/**").permitAll()
            .antMatchers(HttpMethod.POST, "/api/author/search").permitAll()
            .antMatchers(HttpMethod.GET, "/api/book/**").permitAll()
            .antMatchers(HttpMethod.POST, "/api/book/search").permitAll()

            .anyRequest().authenticated()

        // add jwt token filter before spring
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter::class.java)

    }

    @Bean
    fun passwordEncoder()  = BCryptPasswordEncoder()

//    @Autowired
//    lateinit var dataSource:DataSource
//
//    @Autowired
//    fun initialize(builder: AuthenticationManagerBuilder, dataSource: DataSource?) {
//        builder.jdbcAuthentication().dataSource(dataSource).withUser("wjp")
//            .password("qwer").roles("USER")
//    }


//    override fun configure(http: HttpSecurity) {
////        super.configure(http)
//        http.authorizeRequests()
//            .antMatchers("/", "/home").permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .formLogin().loginPage("/login")
//            .permitAll()
//            .and()
//            .logout()
//            .permitAll()
//    }
//
//    @Bean
//    override fun userDetailsService(): UserDetailsService {
//        val user = User.withDefaultPasswordEncoder()
//            .username("q")
//            .password("q")
//            .roles("USER")
//            .build()
//        return InMemoryUserDetailsManager(user)
//    }

}