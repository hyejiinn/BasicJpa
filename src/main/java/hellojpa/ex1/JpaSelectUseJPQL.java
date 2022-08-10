package hellojpa.ex1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Jpa 조회
 * : Jpql 사용, 객체가 대상이 된다!!
 * JPQL 객체지향 SQL
 * : 엔티티 객체를 대상으로 쿼리
 *   JPQL은 SQL을 추상화한 JPQL이라는 객체지향 쿼리 언어 제공
 */
public class JpaSelectUseJPQL
{
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            List<Member> result = em.createQuery("select m from Member m", Member.class)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.getName() = " + member.getName());
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
