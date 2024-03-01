package hello.hellospring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)는 JPA(Java Persistence API) 엔티티 클래스의 주요 키(primary key)를 자동으로 생성하는 방법을 지정하는데 사용됩니다.
//    GenerationType.IDENTITY 전략은 데이터베이스에서 키를 생성하고 관리하는 방식 중 하나입니다. 이 전략은 데이터베이스의 자동 증가(auto-increment) 기능을 활용하여 주요 키 값을 생성합니다. 주로 MySQL, PostgreSQL과 같은 일부 데이터베이스에서 사용됩니다.

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
