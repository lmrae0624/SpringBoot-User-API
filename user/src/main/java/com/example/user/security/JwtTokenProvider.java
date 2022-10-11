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
    private String secretKey;

    @Value("${jwt.token-validity-in-seconds}")
    private Long tokenValidityInMilliseconds;

    private final userDetailsServiceImpl userDetailsServiceImpl;


    // secretKey를 Base64로 인코딩
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }


    public String createJwtToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());

        // 동일
//        Claims c= Jwts.claims().setSubject(user.getUsername());
//        c.put("role", user.getRole());

        long now = (new Date()).getTime();

        return Jwts.builder()
                .setSubject(user.getUsername())
                //.setHeader(createHeader())
                .setClaims(claims)
                .setExpiration(new Date(now + tokenValidityInMilliseconds)) //만료 시간
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    //key 인코딩
    private String createSigningKey() {
        //byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        //return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
        return Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // Jwt 토큰 유효성 검사
    public boolean validateToken(String token) {
        try {
            //암호화한 jwts를 복호화, 위의 .signWith사용하여 "secretkey"란 signature(private key)를 사용하여 암호화하였으므로
            //복호화 할때 같은 키를 입력하여 복호화함을 명시합니다
            //.parseClaimsJws(key)암호화 할때 signature을 사용하였으므로 Jwt가 아닌 Jws임을 유의해야 합니다
            //.getBody()를 통하여 복호화 후의 디코딩한 payload에 접근할수 있습니다
            //.get(key값)을 통하여 원하는 payload의 벨류에 접근할 수 있습니다
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

//    private Claims getClaimsFormToken(String token) {
//        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
//                .parseClaimsJws(token).getBody();
//    }

    // jwt에서  username 추출
    private String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }


    // jwt로 인증 정보 조회
    public Authentication getAuthentication(String token) {
        //이 메소드에서 DB로부터 회원정보를 가져와 있는 회원인지 아닌지 체크여부를 하는 중요한 메소드: loadUserByUsername 오버라이딩
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(this.getUsernameFromToken(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // Request의 Header에서 token 값을 가져옵니다. "X-AUTH-TOKEN" : "TOKEN값'
    public String resolveToken(HttpServletRequest request) {
        //System.out.println("request : "+request.getHeader("X-AUTH-TOKEN"));
        return request.getHeader("X-AUTH-TOKEN");
    }

}
