package hellojpa.ex5;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 객체지향 모델링 
 */
public class JpaMain3 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 객체를 테이블에 맞추어 모델링 했을 때..
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            People2 people = new People2();
            people.setName("people1");
            people.setTeam(team); // 단방향 연관관계
            em.persist(people);

            // 조회 
            People2 findPeople = em.find(People2.class, people.getId());
            Team findTeam = findPeople.getTeam(); // 객체 그래프 탐색 가능

            System.out.println("findTeam.getName() = " + findTeam.getName());


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
