package hellojpa.ex5;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 양방향 연관관계 (양방향 매핑)
 */
@Entity
@Table(name = "People")
@Getter @Setter
public class People3 {

    @Id
    @GeneratedValue
    @Column(name = "PEOPLE_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team2 team;

    public void changeTeam(Team2 team) {
        this.team = team;
        team.getPeoples().add(this); // 연관관계 편의 메서드
    }
}
