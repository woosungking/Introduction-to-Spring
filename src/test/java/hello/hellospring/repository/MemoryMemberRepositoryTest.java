package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class MemoryMemberRepositoryTest{

    @Autowired MemberService memberService;
    @Autowired MemberRepository MemberRepository;

//    @AfterEach
//    public void reset(){
//        MemberRepository.clean();
//    }

    @Test
    public void save(){
        Member member = new Member();

        member.setName("spring12");

        MemberRepository.save(member);

        Member result = MemberRepository.findById(member.getId()).get();
        System.out.printf("012839129837891273891287371982379812389123798");
        System.out.printf("result="+(result==member));
        Assertions.assertEquals(result, member);

    }

    @Test
    public void findByName(){
        Member member = new Member();
        member.setName("spring12");

        MemberRepository.save(member);

        Member result = MemberRepository.findByName(member.getName()).get();

        Assertions.assertEquals(result, member);
    }

    @Test
    public void findAll(){
        Member member = new Member();
        Member member1 = new Member();
        member.setName("string1231231231213");
        member1.setName("string2ㅁㄴㅇㅁㄴ1ㄹㅁㄴ");

        Member result = MemberRepository.save(member);
        Member result1 = MemberRepository.save(member1);

        List<Member> result2 = MemberRepository.findAll();

        Assertions.assertEquals(result2.size(), 2);



    }
}
