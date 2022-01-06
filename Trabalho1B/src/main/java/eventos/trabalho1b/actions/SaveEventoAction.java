package eventos.trabalho1b.actions;

import eventos.trabalho1b.genericcontroller.GenericController;
import eventos.trabalho1b.model.Evento;
import eventos.trabalho1b.model.Local;
import eventos.trabalho1b.model.dao.EventoDao;
import eventos.trabalho1b.model.dao.LocalDao;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveEventoAction extends GenericController {

    public SaveEventoAction(boolean b) {
        super(b);
    }

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try {

            Evento evento;
            Local local;
            String msg;
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
             
            local = LocalDao.getLocalById(Integer.parseInt(request.getParameter("local")));
       
            if (request.getParameter("idEv").equals("")) {

                evento = new Evento(0,
                                    request.getParameter("nomeEvento"),
                                    Integer.parseInt(request.getParameter("max_ingressos_input")),
                                    String.valueOf(request.getParameter("tipoEvento")),
                                    Double.parseDouble(request.getParameter("preco")),
                                    sdf.parse(request.getParameter("data")),
                                    local,
                                    null);
                
                
                if(EventoDao.verifyEventoByNome(evento)){
                    
                    msg = "Já existe um evento cadastrado com esse nome.";
                         
                } else {
                        
                    EventoDao.getCon().getTransaction().begin();
 
                    msg = "Evento criado com sucesso!!!";
                    
                    EventoDao.getCon().persist(evento);
            
                    EventoDao.getCon().getTransaction().commit();
  
                }
           
            } else {
                
                
                evento = EventoDao.getEventoById(( Integer.parseInt(request.getParameter("idEv")) ));
                                
                evento.setNome(String.valueOf(request.getParameter("nomeEvento")));               
                evento.setQuantIngressos((Integer.parseInt(request.getParameter("max_ingressos_input"))));
                evento.setTipoEvento(String.valueOf(request.getParameter("tipoEvento")));
                evento.setPrecoIngresso(Double.parseDouble(request.getParameter("preco")));
                evento.setHorario(sdf.parse(request.getParameter("data")));
                evento.setLocal(local);
                                
                msg = "Evento alterado com sucesso!!!";
                
                EventoDao.getCon().getTransaction().begin();
                
                EventoDao.getCon().persist(evento);
            
                EventoDao.getCon().getTransaction().commit();
                
            }
                   
            request.setAttribute("msg", msg);

            new ViewCadastraEventoAction(true).executa(request, response);
            
    } catch (Exception ex) {
            throw new ServletException("Erro ao cadastrar evento." + ex);
        }                 
    }    
}
