package hellojpa.ex9;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

/**
 * 값타입 컬렉션 조회
 * 다른 테이블임에도 불구하고 같이 Persist 된다.
 */
public class JpaMain2 {
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

            // 값 타입 컬렉션은 지연 로딩 !
            List<Address> addressHistory = findMember.getAddressHistory();
            for (Address address : addressHistory) {
                System.out.println("address.getCity() = " + address.getCity());
            }

            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            for (String favoriteFood : favoriteFoods) {
                System.out.println("favoriteFood = " + favoriteFood);
            }

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
