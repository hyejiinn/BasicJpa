package hellojpa.ex5;

import hellojpa.ex1.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * 양방향 연관관계 (양방향 매핑)
 */
public class JpaMain4 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team2 team = new Team2();
            team.setName("TeamA");
            em.persist(team);

            People3 people = new People3();
            people.setName("People1");
            people.setTeam(team);
            em.persist(people);

            em.flush();
            em.clear();

            People3 findPeople = em.find(People3.class, people.getId());
            List<People3> peoples = findPeople.getTeam().getPeoples();

            for (People3 people3 : peoples) {
                System.out.println("people3.getName() = " + people3.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
