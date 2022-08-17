package hellojpa.ex7;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
//@Inheritance(strategy = InheritanceType.JOINED) // 조인 전략
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 단일 테이블 전략
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // 구현 테이블 마다 전략
@DiscriminatorColumn // DTYPE -> 이게 있어야 운영하기 편함, 어떤건지 모르니까 !
public abstract class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;

}
