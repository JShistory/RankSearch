package com.example.Foods.member.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.Foods.member.entity.Member;
import com.example.Foods.member.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    EntityManager em;
    @Autowired
    MemberRepository memberRepository;


    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long saveId = memberService.createMember(member);
        //then
        em.flush();
        assertEquals(member, memberRepository.findById(member.getId()).get());

    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("kim");
        member2.setName("kim");
        //when
        memberService.createMember(member1);
        memberService.createMember(member2);
        //then
        fail("예외가 발생해야 됨");
    }
}