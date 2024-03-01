package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
//jpa를 쓰려면 트렌젝션이 꼭 있어야함.
//모든 데이터 변경이 트렌젝션 안에있어야함
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){

        this.memberRepository = memberRepository;
    }

    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);

        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("중복된 회원 입니다.");
        });
    }

    public List<Member> findmembers(){
        List<Member> result = memberRepository.findAll();
        return result;

    }

    public Optional<Member> findone(Long memberid){

        return memberRepository.findById(memberid);


    }
}
