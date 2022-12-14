package hellojpa.ex7;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Movie movie = new Movie();
            movie.setDirector("aaa");
            movie.setActor("bbb");
            movie.setName("바람과 함께 사라지다.");
            movie.setPrice(10000);

            em.persist(movie);

            em.flush(); // 영속성 컨텍스트 지움 , 1차캐시 날림
            em.clear();

            Item findMovie = em.find(Item.class, movie.getId());
            System.out.println("findMovie = " + findMovie);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
