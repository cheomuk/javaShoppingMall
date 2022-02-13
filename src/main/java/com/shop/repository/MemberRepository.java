package com.shop.repository;

import com.shop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);   // 회원 가입 시 중복된 회원이 있는지 검사할 수 있도록 하는 쿼리 메소드
}
