package com.example.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(value = AuditingEntityListener.class)
@DynamicUpdate
@Transactional
@Table(name = "MR_USER")
public class User {
    @Id
    private Long id; //pk
    private String username; //아이디
    private String password; //비밀번호
    private String name;    //이름

    @Column(name = "BIRTH")
    private Timestamp birth;     //생년월일

    @CreationTimestamp
    @Column(name = "REGDATE")
    private Timestamp regDate = new Timestamp(System.currentTimeMillis());

}
