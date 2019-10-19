import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Main {
    private static EntityManager em;

    public static void main(String[] args) throws ClassNotFoundException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyBay");
        em = emf.createEntityManager();
        //Date d = new Date(1992,12,1);
    }
}