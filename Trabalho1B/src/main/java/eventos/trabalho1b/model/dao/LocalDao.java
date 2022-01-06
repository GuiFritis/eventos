package eventos.trabalho1b.model.dao;

import eventos.trabalho1b.model.Local;
import static eventos.trabalho1b.model.dao.DaoBase.getCon;
import javax.persistence.Query;

public class LocalDao {
    
    public static Local getLocalById(int id){
        
        Query q = getCon().createNamedQuery("Local.findByIdLocal");
        
        q.setParameter("id", id);
        
        try{
            return (Local) q.getSingleResult();
        } catch (Exception e){
            return new Local();
        }       
    }
    
    public static Local getLocalByNome(String nome){
        
        Query q = getCon().createNamedQuery("Local.findByNome");
        
        q.setParameter("nome", nome);
        
        try{
            return (Local) q.getSingleResult();
        } catch (Exception e){
            return new Local();
        }       
    }
    
}
