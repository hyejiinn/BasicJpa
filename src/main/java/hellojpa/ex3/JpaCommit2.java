package hellojpa.ex3;

import hellojpa.ex1.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @GeneatedValue 전략 : Identity
 * : AUTO_INCREMENT 는 데이터베이스 INSERT SQL 을 실행한 이후에 ID 값을 알 수 있다.
 * @GeneatedValue 전략 : sequence
 * :   call next value for MEMBER_SEQ를 통해 DB에서 pk 값을 가져옴 em.persist일때 sql 실행 x
 */
public class JpaCommit2 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            User user = new User();
            user.setUsername("C");

            System.out.println("===============");
            em.persist(user); // @GeneratedValue 전략이 identity라면 em.persist에서 insert sql을 날린다.
            System.out.println("user.getId() = " + user.getId());
            System.out.println("===============");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
