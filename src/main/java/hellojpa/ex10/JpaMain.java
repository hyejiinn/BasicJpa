package hellojpa.ex9;

<<<<<<< HEAD
import hellojpa.ex8.Member;
=======
>>>>>>> origin/master
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
<<<<<<< HEAD
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * JPQL 소개
=======

/**
 * 값타입
>>>>>>> origin/master
 */
public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

<<<<<<< HEAD
            // 1) JPQL 소개
            List<Member> result = em.createQuery("select m from Member m where m.name like '%hello%'", Member.class).getResultList();

            for (Member member : result) {
                System.out.println("member.getName() = " + member.getName());
            }

            // 2) Criteria 소개 -> 근데 좀 복잡하다.. 이렇게 까지 해야하나 싶음..! 동적 쿼리 용이할 수는 있음
            // -> QueryDsl을 사용하자..!
            // Criteria 준비
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = criteriaBuilder.createQuery(Member.class);

            // 루트 클래스 (조회를 시작할 클래스)
            Root<Member> from = query.from(Member.class);

            // 쿼리 생성
            CriteriaQuery<Member> criteriaQuery = query.select(from).where(criteriaBuilder.equal(from.get("name"), "hello"));

            List<Member> resultList = em.createQuery(criteriaQuery).getResultList();

            for (Member member : resultList) {
                System.out.println("member.getName() = " + member.getName());
            }

            // 3) Native SQL
            em.createNativeQuery("select MEMBER_ID, name from MEMBER").getResultList();

=======
            Address address = new Address("city", "street", "10000");

            Member member = new Member();
            member.setName("member1");
            member.setHomeAddress(address);
            em.persist(member);

            Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());

            Member member2 = new Member();
            member2.setName("member2");
            member2.setHomeAddress(copyAddress);
            em.persist(member2);

            // city 변경 -> member1, member2의 city 모두 변경되버림,, side effect 발생
            // 해결 방법 : 값 복사해서 사용하기
            member.getHomeAddress().setCity("new city");
>>>>>>> origin/master

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
