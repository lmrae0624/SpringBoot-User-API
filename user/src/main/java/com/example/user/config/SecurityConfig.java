package com.example.user.config;

import com.example.user.security.ExceptionHandlerFilter;
import com.example.user.security.JwtAuthenticationFilter;
import com.example.user.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {


    private final JwtTokenProvider jwtTokenProvider;
    private final ExceptionHandlerFilter exceptionHandlerFilter;


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()

                .authorizeRequests()
                .antMatchers("/api/users/login").permitAll()
                .antMatchers(HttpMethod.POST,"/api/users").permitAll()
                //.anyRequest().hasRole("USER")
                .anyRequest().authenticated()
                .and()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //토큰을 활용하면 세션이 필요 없으므로 STATELESS로 설정하여 Session 사용 안함
                .and()

                .formLogin().disable()  //폼 로그인 방식 비활성화

                //exceptionHandlerFilter -> JwtAuthenticationFilter -> UsernamePasswordAuthenticationFilter
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(exceptionHandlerFilter, JwtAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // swagger
        return  (web) -> web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui",
                "/swagger-resources/**", "/configuration/security", "/swagger-ui.html", "/webjars/**");
    }
}



