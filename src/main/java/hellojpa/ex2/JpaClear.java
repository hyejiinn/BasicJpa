package hellojpa.ex2;

import hellojpa.ex1.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 영속성 컨텍스트 초기화
 */
public class JpaClear {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = em.find(Member.class, 101L);
            member.setName("memberAAA");

            em.clear(); // 영속성 컨텍스트 완전 초기화, 1차 캐시를 전체 다 지워버림

            tx.commit(); // 커밋하는 순간 insert sql 보낸다.
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
