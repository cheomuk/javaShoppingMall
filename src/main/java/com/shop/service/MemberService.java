package com.shop.service;

import com.shop.entity.Member;
import com.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional              // 로직을 처리하다가 에러가 발생할 시 변경된 데이터를 로직을 수행하기 이전 상태로 콜백시킴.
@RequiredArgsConstructor    // final 혹은 @NonNull이 붙은 필드에 생성자를 생성해준다.
public class MemberService {

    private final MemberRepository memberRepository;    // Bean을 주입하는 방법 중 생성자 주입을 이용한 타입.
    // 빈에 생성자가 1개이고 생성자의 파라미터 타입이 빈으로 등록이 가능하면 @Autowired 어노테이션 없이 의존성 주입이 가능하다.

    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member){    // 이미 가입된 경우 예외 발생시킴.
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}
