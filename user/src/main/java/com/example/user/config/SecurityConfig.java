package com.example.user.config;

import com.example.user.security.JwtAuthenticationFilter;
import com.example.user.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    //BcryptPasswordEncoder는 PasswordEncoder 인터페이스의 구현체이며
    //Bcrypt 해싱 함수를 사용해 비밀번호를 인코딩해주는 메서드와 사용자가 로그인할 때 제출한 비밀번호와 DB에 저장되어 있는 비밀번호의 동일 여부를 확인해주는 메서드를 제공
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()   //rest api라 상태를 저장하지 않으니 CSRF 설정 Disable
                .authorizeRequests()
                .antMatchers("/*/login","/*/signup").permitAll()
                .anyRequest().hasRole("USER")
                /*//해당하는 url은 admin 역할을 가진 사용자만 접근 가능
                //.anyRequest().authenticated()
                //그 외 나머지 url들은 권한 상관없이 접근 가능 (permitAll)
                // 토큰을 활용하는 경우 모든 요청에 대해 접근이 가능하도록 함*/
                // .anyRequest().permitAll()
                .and()
                //토큰을 활용하면 세션이 필요 없으므로 STATELESS로 설정하여 Session 사용 안함
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //폼 로그인 방식 비활성화
                .formLogin().disable()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // resources 모든 접근을 허용하는 설정을 해버리면
        // HttpSecurity 설정한 ADIM권한을 가진 사용자만 resources 접근가능한 설정을 무시해버린다.
        //web.ignoring().antMatchers("/resources/**");

        // 정적 자원에 대해서는 security 설정 안함
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());

        web.ignoring().antMatchers("/v2/api-docs","/swagger-resources/**","/swagger-ui.html","/swagger/**");

    }
}

