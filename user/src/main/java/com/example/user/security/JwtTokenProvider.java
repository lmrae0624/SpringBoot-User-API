package com.example.user.security;

import com.example.user.domain.User;
import com.example.user.exception.common.ErrorCode;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
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
public class JwtTokenProvider {
    @Value("${jwt.secretKey}")
    private String secretKey; //SecretKeyTest

    @Value("${jwt.expirations}")
    private long expirations; //86400

    @Value("${jwt.authorization}")
    private String authorization; //Authorization

    private final UserDetailsServiceImpl userDetailsServiceImpl;


    // secretKey를 Base64로 인코딩
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // 토큰 생성
    public String createJwtToken(User user) {
        long now = (new Date()).getTime(); //현재

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setHeader(createHeader())
                .setClaims(createClaims(user))
                .setExpiration(new Date(now + expirations))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    //header
    private Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();

        header.put("typ", "JWT");
        header.put("alg", "HS256");

        return header;
    }

    //claim
    private Map<String, Object> createClaims(User user) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("id", user.getId());
        claims.put("roles", user.getRoles());

        return claims;
    }

    // Jwt 토큰 유효성 검사
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            throw new JwtTokenException(ErrorCode.INVALID_SIGNATURE_TOKEN);
        } catch (MalformedJwtException | IllegalArgumentException ex) {
            throw new JwtTokenException(ErrorCode.WRONG_TYPE_TOKEN);
        } catch (ExpiredJwtException ex) {
            throw new JwtTokenException(ErrorCode.EXPIRED_TOKEN);
        } catch (UnsupportedJwtException ex) {
            throw new JwtTokenException(ErrorCode.UNSUPPORTED_TOKEN);
        }
    }

    // Jwt에서 user id(pk) 추출
    private String getUserIdFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return String.valueOf(claims.get("id"));
    }


    // Jwt로 인증 정보 조회
    public Authentication getAuthentication(String token) {
        //DB로부터 회원정보를 가져와 있는 회원인지 아닌지 체크
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(this.getUserIdFromToken(token));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }


    //http request header에서 세팅된 token값
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(authorization);
    }

}
