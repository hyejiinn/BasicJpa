package hellojpa.ex2;

import hellojpa.ex1.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 영속성 컨텍스트의 이점
 * 1. 1차 캐시
 * 2. 동일성 보장
 * 3. 트랜잭션을 지원하는 쓰기지연
 * 4. 변경 감지 -> JPA 수정과 관련
 * 5. 지연 로딩
 */
public class JpaPersist
{
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            // 비영속 상태 : 객체를 단순히 생성한 상태 (JPA와 관련 X)
            Member member = new Member();
            member.setId(2L);
            member.setName("helloB");

            // 영속 상태 : 객체를 저장한 상태
            System.out.println("=== Before ===");
            em.persist(member);  // DB 저쟝 X
//            em.detach(member); 준영속 상태 : 회원 엔티티를 영속성 컨텍스트에서 분리
            System.out.println("=== After ===");

            tx.commit(); // 트랜잭션을 커밋하는 시점에 insert 한다.

        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
