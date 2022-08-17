package hellojpa.ex5;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 양방향 연관관계 (양방향 매핑)
 */
//@Entity
@Table(name = "Team")
@Getter @Setter
public class Team2 {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<People3> peoples = new ArrayList<>();


}
