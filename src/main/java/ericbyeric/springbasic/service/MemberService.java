package ericbyeric.springbasic.service;

import ericbyeric.springbasic.domain.Member;
import ericbyeric.springbasic.repository.MemberRepository;
import ericbyeric.springbasic.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
//    private final MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    // 회원가입
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {throw new IllegalStateException("이미 존재하는 회원입니다"); });
    }

    // 전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    // 단일 회원 조회 (id)
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
