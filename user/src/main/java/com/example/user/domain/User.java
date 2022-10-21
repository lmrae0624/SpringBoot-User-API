package com.example.user.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@DynamicInsert
@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(value = AuditingEntityListener.class) //생성시간 자동 매핑
@Table(name = "MR_USER")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; //pk

    @Column(name = "username", nullable = false, length = 30)
    private String username; //아이디

    @Column(name = "password", nullable = false, length = 30)
    private String password; //비밀번호

    @Column(name = "name", nullable = false, length = 30)
    private String name;    //이름

    @Column(name = "birth", length = 30)
    private Timestamp birth;     //생년월일

    @CreationTimestamp
    @Column(name = "regdate", nullable = false, length = 15)
    private Timestamp regDate = new Timestamp(System.currentTimeMillis());   //회원 가입 날짜

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "roles")
    @ColumnDefault("ROLE_USER")
    private List<String> roles = new ArrayList<>(); //권한

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return false;
    }
}
