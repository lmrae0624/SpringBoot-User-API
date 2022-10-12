package com.example.user.security;

import com.example.user.domain.User;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider {
    @Value("${jwt.secretKey}")
    private static String secretKey;

    @Value("${jwt.token-validity-in-seconds}")
    private Long tokenValidityInMilliseconds;

    private final UserDetailsServiceImpl userDetailsServiceImpl;


    // secretKey를 Base64로 인코딩
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }


    public String createJwtToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());

        long now = (new Date()).getTime();

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setClaims(claims)
                .setExpiration(new Date(now + tokenValidityInMilliseconds)) //만료 시간
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }


    // Jwt 토큰 유효성 검사
    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

    // jwt에서 username 추출
    private String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return (String) claims.get("username");
    }


    // jwt로 인증 정보 조회
    public Authentication getAuthentication(String token) {
        //이 메소드에서 DB로부터 회원정보를 가져와 있는 회원인지 아닌지 체크여부를 하는 중요한 메소드: loadUserByUsername 오버라이딩
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(this.getUsernameFromToken(token));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }


    //http request header에서 세팅된 token값
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

}
