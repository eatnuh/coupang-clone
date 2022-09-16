package com.cpcl.member;

import com.cpcl.common.BaseTimeEntity;
import lombok.*;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    public void update(@Nullable final String password,
                       @Nullable final String name,
                       @Nullable final String phone) {
        if(StringUtils.hasText(password)) this.password = password;
        if(StringUtils.hasText(name)) this.name = name;
        if(StringUtils.hasText(phone)) this.phone = phone;
    }

}
