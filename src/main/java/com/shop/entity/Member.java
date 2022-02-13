package com.shop.entity;

import com.shop.constant.Role;
import com.shop.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name="member")
@Getter @Setter
@ToString
public class Member {

    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)  // 컬럼 속성 유니크 부여
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)    // enum의 순서가 바뀔 경우를 대비해 String 타입으로 지정하여 저장한다.
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){    // 멤버 엔티티 생성
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        // BCryptPasswordEncoder Bean을 파라미터로 넘겨서 비밀번호를 암호화한다.
        member.setPassword(password);
        member.setRole(Role.USER);
        return member;
    }
}
