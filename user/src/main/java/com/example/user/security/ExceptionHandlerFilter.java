package com.example.user.security;

import com.example.user.exception.common.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //JwtAuthenticationFilter 를 호출하는데, 이 필터에서 JwtTokenException이 떨어진다.
        try {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch(JwtTokenException e){
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
            httpServletResponse.setCharacterEncoding("UTF-8");

            ErrorResponse errorObject = new ErrorResponse(e.getErrorCode().getStatus(), e.getErrorCode().getCode(), e.getErrorCode().getMessage());
            objectMapper.writeValue(httpServletResponse.getWriter(), errorObject);
        }
    }

}
