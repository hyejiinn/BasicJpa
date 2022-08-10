package hellojpa.ex2;

import hellojpa.ex1.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 트랜잭션을 지연하는 쓰기 지연
 * : JDBC BATCH SQL 를 사용
 */
public class JpaCommit {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 영속
            Member memberA = new Member(100L, "memberA");
            Member memberB = new Member(101L, "memberB");

            em.persist(memberA);
            em.persist(memberB);
            System.out.println("======================"); // insert 쿼리 실행을 확인하기 위한 구분선

            tx.commit(); // 커밋하는 순간 insert sql 보낸다.
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
