package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;
    //jpa는 EntityManager로 모든 동작을함.
    //implementation 'org.springframework.boot:spring-boot-starter-data-jpa' 이 부분이 있으면 프로젝트를 시작할때
    // EntityManager를 만들어줌, 우리는 이걸 주입받아서 쓰면 되는(데이터 베이스랑 연결도 막 해줌.)

    public JpaMemberRepository(EntityManager em) {

        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member as m where m.name= :name", Member.class).setParameter("name", name).getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {

        return em.createQuery("select m from Member as m ", Member.class).getResultList();

    }

    @Override
    public void clean() {

    }
}
//jpql쿼리라고 함. 이 방식은 멤버엔티티, select의 대상이 특별한데, 멤버 객체자체를 조회하는거임-> 조금더 조사하고 블로그 ㄱ
//
        /*
        em.createQuery("select m from Member as m ", Member.class)는 JPQL 쿼리를 생성하고 해당 쿼리를 실행하여 결과를 반환합니다.
        그러나 이때 실제로 쿼리가 실행되는 것이 아니라 쿼리 객체만 생성됩니다. 실제 데이터베이스 쿼리가 실행되는 시점은 getResultList() 메서드가 호출될 때입니다.
        */