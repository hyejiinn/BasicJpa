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
public class JpaRemove {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member findMember = em.find(Member.class, 101L);
            em.remove(findMember); // 엔티티 삭제

            tx.commit(); // 커밋하는 순간 insert sql 보낸다.
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
