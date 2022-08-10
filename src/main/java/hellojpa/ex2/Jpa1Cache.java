package hellojpa.ex2;

import hellojpa.ex1.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 1. 1차 캐시에서 조회
 * 2. 영속 엔티티의 동일성 보장 (== 비교 시 true) -> 1차 캐시가 있기 때문에 가능한 일이다.
 * 3. 트랜잭션을 지원하는 쓰기 지연
 */
public class Jpa1Cache {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 비영속 상태
            Member member = new Member();
            member.setId(3L);
            member.setName("helloC");

            // 영속 상태
            System.out.println("=== Before ===");
            em.persist(member);
            System.out.println("=== After ===");

            Member findMember = em.find(Member.class, 3L);

            // 조회 쿼리 Select...하지 않는다. DB에서 조회가 아닌 1차캐시에서 정보 조회함
            System.out.println("findMember.getId() = " + findMember.getId());
            System.out.println("findMember.getName() = " + findMember.getName());

            // 2L는 1차캐시에 저장된 값이 아니기 때문에 DB에서 조회함
            Member find2LMember = em.find(Member.class, 2L);

            System.out.println("find2LMember.getId() = " + find2LMember.getId());
            System.out.println("find2LMember.getName() = " + find2LMember.getName());

            // 이제 1차 캐시에 저장되어 있기 때문에 DB에서 조회가 아닌 1차캐시에서 가져옴
            Member find2LMember2 = em.find(Member.class, 2L);

            // 영속 엔티티의 동일성 보장
            // 동일한 트랜잭션 안에서는 엔티티의 동일성 보장한다.
            System.out.println("find2LMember == find2LMember2 = " + (find2LMember == find2LMember2)); // 같음

            // 트랜잭션을 지원하는 쓰기 지연
            // 커밋하는 순간에 DB에 INSERT SQL을 보낸다.
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
