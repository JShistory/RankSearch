package com.example.Foods.member.service;

import com.example.Foods.member.controller.MemberController;
import com.example.Foods.member.entity.Member;
import com.example.Foods.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Member createMember(Member member){
        return memberRepository.save(member);
    }

}
