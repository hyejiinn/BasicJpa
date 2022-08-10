package hellojpa.ex1;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Entity : JPA가 관리할 객체
 * @Id : 데이터베이스 PK와 매핑
 */
@Entity
@Getter @Setter
public class Member {

    @Id
    private Long id;
    private String name;

    public Member() {
    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
