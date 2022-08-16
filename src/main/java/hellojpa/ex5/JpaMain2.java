package hellojpa.ex5;

import hellojpa.ex1.Member;
import hellojpa.ex3.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain2 {
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

            People people = new People();
            people.setName("member1");
            people.setTeamId(team.getId()); // 이 부분이 애매하다.. 객체지향적이지 않다!
            em.persist(people);

            People findPeople = em.find(People.class, people.getId());
            Long findTeamID = findPeople.getTeamId();
            Team findTeam = em.find(Team.class, findTeamID);


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
