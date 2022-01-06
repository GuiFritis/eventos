package eventos.trabalho1b.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoBase {
    
    protected static String src = "eventos.trabalho1b.model.dto.";
    
    private static EntityManagerFactory emf; // = Persistence.createEntityManagerFactory("eventosPU");
        
    private static EntityManager con; // = emf.createEntityManager();
    
    public static void close(){
        if (con.isOpen()){
            con.close();
        }
    }
    
    public static EntityManager getCon(){
        if (con == null || !con.isOpen()){
           emf = Persistence.createEntityManagerFactory("eventosPU");
           con = emf.createEntityManager();
        }
        return con;
    }     
    
}
