package eventos.trabalho1b.model.dao;

import eventos.trabalho1b.model.Evento;
import eventos.trabalho1b.model.Local;
import eventos.trabalho1b.model.dto.EventoDTO;
import eventos.trabalho1b.model.dto.EventoSemIngressoDTO;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

public class EventoDao extends DaoBase {
        
    public static Evento getEventoById(int id){
        Query q = getCon().createNamedQuery("Evento.findByIdEvento");
        
        q.setParameter("id", id);
        
        try{
            return (Evento) q.getSingleResult();
        } catch (Exception e){
            return new Evento();
        }
        
    }
    
    public static EventoSemIngressoDTO getEventoSemIngressoById(int id){
        Query q = getCon().createQuery("SELECT new "+ src +"EventoSemIngressoDTO(e.idevento, e.nome, e.quantIngressos, e.tipoEvento, e.precoIngresso, e.horario, e.local) "
                + " FROM Evento e WHERE e.idevento = :id");
        
        q.setParameter("id", id);
        
        try{
            return (EventoSemIngressoDTO) q.getSingleResult();
        } catch (Exception e){
            return new EventoSemIngressoDTO();
        }
        
    }
    
    public static List<Integer> getCadeirasSessao(int evento, int setor){
        
        Query q = getCon().createQuery("SELECT i.numero FROM Ingresso i "
                + "WHERE i.numero BETWEEN :iSessao AND :fSessao "
                + "AND i.evento.idevento = :evento");
        
        if(setor != 0){
            q.setParameter("iSessao", (setor - 1) * 81);
            q.setParameter("fSessao", (setor * 81) + 1);
        } else {
            q.setParameter("iSessao", (81 * 6));
            q.setParameter("fSessao", (81 * 6) + (10 * 15) + 1);
        }
        
        q.setParameter("evento", evento);
        
        return q.getResultList();
    }

    public static List<Evento> getEventosFiltro(String filtro_tipo, String filtro_nome, Local filtro_local, Date filtro_dataInic, Date filtro_dataFim, String filtro_preco_1, String filtro_preco_2, String filtro_ingressos_1, String filtro_ingressos_2 ) {
        
        double preco1 = 0;
        double preco2 = 0;
        
        int ings1 = 0;
        int ings2 = 0;
        
        String sql = "select E from Evento E Where 1=1 ";
        
        if (filtro_tipo != null) {
            sql += " and E.tipoEvento = :filtro_tipo";      
        }
        
        if (filtro_nome != null) {
            sql += " and E.nome like :filtro_nome";    
        }
        
        if (filtro_local != null) {
            sql += " and E.local = :filtro_local"; 
        }
        
        if (filtro_dataInic != null && filtro_dataFim != null) {
            sql += " and E.horario between :filtro_dataInic and :filtro_dataFim"; 
        }
                
        if (filtro_preco_1 != null && filtro_preco_2 != null) {
            preco1 = Double.parseDouble(filtro_preco_1);
            preco2 = Double.parseDouble(filtro_preco_2);
            
            sql += " and E.precoIngresso between :preco1 and :preco2"; 
        }
        
        if (filtro_ingressos_1 != null && filtro_ingressos_2 != null) {
            ings1 = Integer.parseInt(filtro_ingressos_1);
            ings2 = Integer.parseInt(filtro_ingressos_2);
            
            sql += " and E.quantIngressos between :ings1 and :ings2"; 
        }
         
        sql += " order by E.horario";

        Query q = getCon().createQuery(sql);
        
        if (filtro_tipo != null) {
            q.setParameter("filtro_tipo", filtro_tipo);
        }
        
        if (filtro_nome != null) {
            q.setParameter("filtro_nome", '%' + filtro_nome + '%');
        }
       
        if (filtro_local != null) {
            q.setParameter("filtro_local", filtro_local);
        }
        
        if (filtro_dataInic != null) {
            q.setParameter("filtro_dataInic", filtro_dataInic);
        }
        
        if (filtro_dataFim != null) {
            q.setParameter("filtro_dataFim", filtro_dataFim);
        }
        
        if (filtro_preco_1 != null && filtro_preco_2 != null) {
            q.setParameter("preco1", preco1);
            q.setParameter("preco2", preco2);
        }
        
        if (filtro_ingressos_1 != null && filtro_ingressos_2 != null) {
            q.setParameter("ings1", ings1);
            q.setParameter("ings2", ings2);
        }
                        
        return q.getResultList();
        
    }

    public static List<EventoSemIngressoDTO> getEventosFuturos() {
        
        Query q = getCon().createQuery("SELECT new "+ src +"EventoSemIngressoDTO(e.idevento, e.nome, e.quantIngressos, e.tipoEvento, e.precoIngresso, e.horario, e.local) "
                + " FROM Evento e WHERE e.horario >= :horario "
                + " GROUP BY e.idevento "
                + " HAVING COUNT(e.ingressos) < e.quantIngressos");
        
        q.setParameter("horario", new Date());
        
        return q.getResultList();
        
    }
    
    public static boolean verifyEventoByNome(Evento evento){
        
        Query q = getCon().createQuery("SELECT count(e) FROM Evento e WHERE e.nome = :nome");
        
        q.setParameter("nome", evento.getNome());
        
        return (long)q.getSingleResult() >= 1; 
                      
    }
    
    public static List<EventoDTO> getEventosRelatorio(String filtro_tipo, Local filtro_local, Date filtro_dataInic, Date filtro_dataFim){
        
        String sql = "SELECT new "+ src +"EventoDTO(e.idevento, e.tipoEvento, e.local.nome, e.nome, e.horario, e.local.luz, e.local.agua, e.local.limpeza, e.local.aluguel) "
                + " FROM Evento e WHERE 1=1";
        
        if (filtro_tipo != null) {
            sql += " and E.tipoEvento = :filtro_tipo";      
        }
                
        if (filtro_local != null) {
            sql += " and E.local = :filtro_local"; 
        }
        
        if (filtro_dataInic != null && filtro_dataFim != null) {
            sql += " and E.horario between :filtro_dataInic and :filtro_dataFim"; 
        }
              
        sql += " order by E.horario";
        
        Query q = getCon().createQuery(sql);
        
        if (filtro_tipo != null) {
            q.setParameter("filtro_tipo", filtro_tipo);
        }       
       
        if (filtro_local != null) {
            q.setParameter("filtro_local", filtro_local);
        }
        
        if (filtro_dataInic != null) {
            q.setParameter("filtro_dataInic", filtro_dataInic);
        }
        
        if (filtro_dataFim != null) {
            q.setParameter("filtro_dataFim", filtro_dataFim);
        }
        
        return q.getResultList();
        
    }

    public static boolean getEventoByHorario(Date horario) {
                
        Date horarioMinus = new Date(horario.getTime());
        
        horarioMinus.setDate(horarioMinus.getDate() - 1);
                 
        Date horarioPlus = new Date(horario.getTime());
        
        horarioPlus.setDate(horarioPlus.getDate() + 1);
                       
        Query q = getCon().createQuery("SELECT count(e) FROM Evento e WHERE e.horario BETWEEN :horarioMinus and :horarioPlus");
        
        q.setParameter("horarioMinus", horarioMinus);
        q.setParameter("horarioPlus", horarioPlus);
        
        Long validade = (Long)q.getSingleResult(); 
        
        return validade.intValue() == 0;
               
    }

}
