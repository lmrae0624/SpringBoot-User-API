package com.example.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    @Column(name = "ID")
    private Long id; //pk

    @Column(name = "USERNAME")
    private String username; //아이디

    @Column(name = "PASSWORD")
    private String password; //비밀번호

    @Column(name = "NAME")
    private String name;    //이름

    @Column(name = "BIRTH")
    private Date birth;     //생년월일

    //@Temporal(TemporalType.DATE)
    @Column(name = "REGDATE")
    private LocalDateTime regDate;   //회원 가입 날짜

}
