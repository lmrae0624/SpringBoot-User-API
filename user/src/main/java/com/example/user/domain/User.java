package com.example.user.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(value = AuditingEntityListener.class)
//@DynamicUpdate
//@Transactional
@Table(name = "MR_USER")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    @Column(name = "id")
    private Long id; //pk

    @Column(name = "username")
    private String username; //아이디

    @Column(name = "password")
    private String password; //비밀번호

    @Column(name = "name")
    private String name;    //이름

    @Column(name = "birth")
    private Timestamp birth;     //생년월일

    @CreationTimestamp
    @Column(name = "regdate")
    private Timestamp regDate = new Timestamp(System.currentTimeMillis());   //회원 가입 날짜


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
