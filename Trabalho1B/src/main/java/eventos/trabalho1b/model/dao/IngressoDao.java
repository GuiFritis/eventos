package eventos.trabalho1b.model.dao;

import eventos.trabalho1b.model.Ingresso;
import eventos.trabalho1b.model.dto.IngressoDTO;
import java.util.List;
import javax.persistence.Query;

public class IngressoDao extends DaoBase {
    
    public static Ingresso getIngressoById(int idingresso){
        Query q = getCon().createNamedQuery("Ingresso.findByIdIngresso");
        
        q.setParameter("id", idingresso);
        
        try{
            return (Ingresso) q.getSingleResult();
        } catch(Exception ex){
            return new Ingresso();
        }        
    }
       
    public static List<Ingresso> getIngressosByEvento(int evento){
        
        Query q = getCon().createQuery("SELECT i FROM Ingresso i WHERE i.evento.idevento = :evento ORDER BY i.numero");
        
        q.setParameter("evento", evento);
        
        return q.getResultList();
        
    }
    
    public static List<Ingresso> getListaIngressosFiltro(int idevento, String filtro_entrada, int filtro_numero, int filtro_setor){
        
        Query q = getCon().createQuery("SELECT i FROM Ingresso i WHERE "
                + " i.evento.idevento = :idevento"
                + " AND (i.tipoEntrada like :filtro_entrada OR '' = :filtro_entrada) "
                + " AND (i.numero = :filtro_numero OR -1 = :filtro_numero) "
                + " AND (i.numero BETWEEN :filtro_setor_1 AND :filtro_setor_2 OR -1 = :filtro_setor_1)");
        
        q.setParameter("idevento", idevento);
        
        q.setParameter("filtro_entrada", filtro_entrada);
        
        q.setParameter("filtro_numero", filtro_numero);
        
        q.setParameter("filtro_setor_1", filtro_setor);
        
        if(filtro_setor == -1){
            q.setParameter("filtro_setor_2", -1);
        } else {
            if(filtro_setor < 487){
                q.setParameter("filtro_setor_2", filtro_setor + 80);
            } else {
                q.setParameter("filtro_setor_2", filtro_setor + 9);
            }
        }
        
        return q.getResultList();
        
    }

    public static List<IngressoDTO> getIngressosRelatorio(int ev) {
        
        String sql = "SELECT new "+ src +"IngressoDTO(i.tipoEntrada, i.evento.precoIngresso, i.numero) "
                + " FROM Ingresso i WHERE I.evento.idevento = :ev ";
        
        Query q = getCon().createQuery(sql);
               
        q.setParameter("ev", ev);
       
        return q.getResultList();
        
    }
    
    public static int getMaiorNumeroEvento(int evento){
        
        int max = 0;
         
        Query q = getCon().createQuery("SELECT MAX(i.numero) FROM Ingresso i WHERE i.evento.idevento = :id");

        q.setParameter("id", evento);

        if( q.getSingleResult() != null ){
            max = (int) q.getSingleResult();
        }
        
        return max;
            
    }
    
}
