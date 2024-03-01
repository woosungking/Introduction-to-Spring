package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {
    MemberService service;
    MemoryMemberRepository repository;

    @BeforeEach
    public void beforeEach(){
        repository = new MemoryMemberRepository();
        service = new MemberService(repository);
    }

    @AfterEach
    void clean(){
        repository.clean();

    }
    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("string");
        Member member1 = new Member();
        member1.setName("string1");
        //when
        Long member_id = service.join(member);
        Long member_id1 = service.join(member1);
        //then
        Optional<Member> result = service.findone(member_id);

        Assertions.assertEquals(member_id, result.get().getId());

    }

    @Test
    void validateDuplicateMember(){
        //given
        Member member = new Member();
        Member member1 = new Member();
        member.setName("string");
        member1.setName("string");
        service.join(member);
        //when

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> service.join(member));

        Assertions.assertEquals(e.getMessage(), "중복된 회원 입니다.");
        //assertYhrows는 주어진 람다식이 예외식을 던지는지 안던지는지 확인해줌
        //어느 예외인지 확인을 해야하는데 그 예외는 예외중에 IllegalAccessError라는 예외의 .class 를 가지고 올거야
        //()안에는 예외가 발생한 코드가 들어갈거임, 변수를 넣었을때는 그냥 알파벳을 넣었음


//        try{
//            service.join(member1);
//            fail();
//        }catch (IllegalStateException e){
//
//            Assertions.assertEquals(e.getMessage(), "중복된 회원 입니다.");
//        }

    }

    @Test
    void findmembers() {
        //given
        Member member = new Member();
        Member member1 = new Member();
        member.setName("string2");
        member1.setName("string3");
        List<Member> memberList = new ArrayList<>() {
        };
        memberList.addAll(Arrays.asList(member1,member));
        service.join(member);
        service.join(member1);
        //when
        List<Member> result = service.findmembers();
        //then


    }

    @Test
    void findone() {
    }
}