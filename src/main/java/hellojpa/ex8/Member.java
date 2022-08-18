package hellojpa.ex8;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩
//    @ManyToOne(fetch = FetchType.EAGER) // 즉시 로딩
    @JoinColumn(name = "TEAM_ID")
    private Team team;
}
