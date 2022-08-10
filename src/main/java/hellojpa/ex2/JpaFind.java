package hellojpa.ex2;

import hellojpa.ex1.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 엔티티 수정
 * - 변경 감지 Dirty checking
 * (1) flush
 * (2) 엔티티와 스냅샷 비교
 * (3) update sql 생성
 * (4) flush
 * (5) commit
 */
public class JpaFind {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = em.find(Member.class, 101L);
            member.setName("memberAAA");

            tx.commit(); // 커밋하는 순간 insert sql 보낸다.
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
