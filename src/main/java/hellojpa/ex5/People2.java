package hellojpa.ex5;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 객체지향모델링
 */
@Entity
@Table(name = "People")
@Getter @Setter
public class People2 {

    @Id
    @GeneratedValue
    @Column(name = "PEOPLE_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;
}
