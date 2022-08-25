package hellojpa.ex9;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

/**
 * 값타입 컬렉션 수정
 * 다른 테이블임에도 불구하고 같이 Persist 된다.
 */
public class JpaMain3 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setName("memberA");
            member.setHomeAddress(new Address("city", "street", "zipcode"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new Address("old1", "oldStreet1", "oldZipcode1"));
            member.getAddressHistory().add(new Address("old2", "oldStreet2", "oldZipcode2"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("========= START =========");
            Member findMember = em.find(Member.class, member.getId());

            // homeCity -> newCity
//            findMember.getHomeAddress().setCity("newCity"); --> X , 값 객체는 immutable 해야한다. !!
            Address address = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity",address.getStreet(), address.getZipcode())); // 값 타입은 통으로 새로 넣어줘야 한다.

            // 값 타입 수정
            // 치킨 -> 한식
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            findMember.getAddressHistory().remove(new Address("old1", "oldStreet1", "oldZipcode1"));
            findMember.getAddressHistory().add(new Address("newCity1", "oldStreet1", "oldZipcode1"));

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
