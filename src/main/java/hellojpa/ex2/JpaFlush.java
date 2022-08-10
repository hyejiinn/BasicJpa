package hellojpa.ex2;

import hellojpa.ex1.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * flush
 * 변경 감지
 * 수정된 엔티티 쓰기 지연 SQL 저장소에 등록
 * 쓰기 지연 SQL 저장소의 쿼리를 데이터베이스에 전송
 * 트랜잭션 커밋할 때 플러시 자동 호출 !
 */
public class JpaFlush
{
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member200 = new Member(200L, "member200");
            em.persist(member200);

            // 커밋 전 sql 이 실행되는 것을 확인할 수 있다.!!!!!!!!!
            em.flush();

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
