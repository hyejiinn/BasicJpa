package hellojpa.ex2;

import hellojpa.ex1.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 준영속 상태
 * 영속 상태의 엔티티가 영속성 컨텍스트에서 분리
 * 영속성 컨텍스트가 제공하는 기능을 사용하지 못함
 */
public class JpaDetach {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = em.find(Member.class, 101L);
            member.setName("memberAAA");

            // 특정 엔티티만 준영속 상태로 전환
            em.detach(member); // 준영속, update 쿼리가 발생하지 않는 것을 확인할 수 있다.

            tx.commit(); // 커밋하는 순간 insert sql 보낸다.
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
