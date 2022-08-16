package hellojpa.ex5;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 객체를 테이블에 맞추어 모델링
 */
//@Entity
@Getter @Setter
public class People {

    @Id
    @GeneratedValue
    @Column(name = "PEOPLE_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    @Column(name = "TEAM_ID")
    private Long teamId;
}
