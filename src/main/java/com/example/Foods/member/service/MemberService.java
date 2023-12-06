package com.example.Foods.member.service;

import com.example.Foods.member.controller.MemberController;
import com.example.Foods.member.entity.Member;
import com.example.Foods.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long createMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member).getId();
    }

    private void validateDuplicateMember(Member member){
        Member member1 = memberRepository.findByName(member.getName());
        if(member1 != null){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

}
