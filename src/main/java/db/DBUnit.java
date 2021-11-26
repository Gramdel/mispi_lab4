package db;

import other.Point;

import javax.persistence.*;
import java.util.List;

public class DBUnit {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private final EntityManager em = emf.createEntityManager();

    public List<Point> getResults() throws PersistenceException {
        return (List<Point>) em.createQuery("Select p FROM Point p").getResultList();
    }

    public void sendResult(Point point) throws PersistenceException {
        em.getTransaction().begin();
        em.persist(point);
        em.getTransaction().commit();
    }

    public void removeResult(Point point) throws PersistenceException {
        em.getTransaction().begin();
        em.remove(point);
        em.getTransaction().commit();
    }
}
