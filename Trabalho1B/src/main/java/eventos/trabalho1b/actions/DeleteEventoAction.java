package eventos.trabalho1b.actions;

import eventos.trabalho1b.genericcontroller.GenericController;
import eventos.trabalho1b.model.Evento;
import eventos.trabalho1b.model.Ingresso;
import eventos.trabalho1b.model.dao.EventoDao;
import eventos.trabalho1b.model.dao.IngressoDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteEventoAction extends GenericController {

    public DeleteEventoAction(boolean b) {
        super(b);
    }

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Evento evento = EventoDao.getEventoById( Integer.parseInt( request.getParameter("id") ) );
                
        IngressoDao.getCon().getTransaction().begin();       
        for(Ingresso ing : evento.getIngressos()){
            IngressoDao.getCon().remove(ing);     
        }            
        IngressoDao.getCon().getTransaction().commit();
        
        EventoDao.getCon().getTransaction().begin();
        EventoDao.getCon().remove(evento);   
        EventoDao.getCon().getTransaction().commit();
        
        request.setAttribute("msg", "Evento cancelado com sucesso!");
        
        new ViewListaEventosAction(true).executa(request, response);
        
    }
    
}
