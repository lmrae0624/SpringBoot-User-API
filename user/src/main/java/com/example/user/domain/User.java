package com.example.user.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

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
public class User {
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

}
