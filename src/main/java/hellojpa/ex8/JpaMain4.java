package hellojpa.ex8;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * 영속성 전이 CASCADE -> 소유자(?) 가 1개일때만 사용하는게 운영하기에 편리하다 (단일엔티티에 종속적일때)
 * : 특정 엔티티를 영속 상태로 만들 때 연관된 엔티티도 함께 영속 상태로 만들고 싶을 때 사용한다.
 * (하나씩 persist하기 귀찮으니까,,)
 * 영속성 전이와 연관관계 매핑하는것과는 전~~~혀 관계 없다. 영속화하는 편리함을 제공할 뿐이지 그 이상도 그 이하도 아니다.
 */
public class JpaMain4 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

//            em.persist(child1);
//            em.persist(child2);
            em.persist(parent);

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
