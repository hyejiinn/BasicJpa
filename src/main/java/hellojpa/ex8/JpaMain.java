package hellojpa.ex8;

import hellojpa.ex7.Item;
import hellojpa.ex7.Movie;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 프록시 Proxy
 */
public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setName("hello");

            em.persist(member);

            // 영속성 컨텍스트를 비우기 위해서, 즉 1차 캐시를 지우고, DB 쿼리를 확인하기 위해서 flush, clear를 함.
            em.flush();
            em.clear();

//            Member findMember = em.find(Member.class, member.getId());
            Member refMember = em.getReference(Member.class, member.getId()); // Proxy
            System.out.println("emf.getPersistenceUnitUtil().isLoaded(refMember) = " + emf.getPersistenceUnitUtil().isLoaded(refMember)); // false 아직 초기화 안함
            System.out.println("refMember = " + refMember.getClass()); // getReference()인 경우에 Proxy 클래스 출력됨
            System.out.println("refMember.getId() = " + refMember.getId());
            System.out.println("refMember.getName() = " + refMember.getName()); // getReference일 때, 실제 DB 쿼리를 실행해야 하는 시점에 쿼리를 실행함 !!

            Member findMember = em.find(Member.class, member.getId());
            System.out.println("findMember.getClass() = " + findMember.getClass());

            // 항상 true를 만들어줘야 하기 때문에 여기서는 find를 해도 proxy 를 반환한다..!!
            System.out.println("refMember == findMember ? " + (refMember == findMember));

            // 프록시 인스턴스의 초기화 여부 확인
            System.out.println("emf.getPersistenceUnitUtil().isLoaded(refMember) = " + emf.getPersistenceUnitUtil().isLoaded(refMember)); // true (getUsername 할 때 초기화 요청함)

            // 프록시 강제 초기화
            Hibernate.initialize(refMember);

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
