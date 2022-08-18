package hellojpa.ex8;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * 프록시와 즉시로딩 주의!!!
 * JPQL에서 즉시로딩은 N+1 문제를 일으킨다..!!!
 * em.find()는 JPA가 최적화를 해서 SQL을 실행하지만 JPQL은 아니다..!!
 */
public class JpaMain3 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setName("hello");
            member.setTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

            // 즉시 로딩이어도
            // SELECT * FROM MEMBER
            // SELECT * FROM TEAM WHERE TEAM.ID = MEMBER.ID  두번 SELECT 하게 된다.
            List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();



            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
