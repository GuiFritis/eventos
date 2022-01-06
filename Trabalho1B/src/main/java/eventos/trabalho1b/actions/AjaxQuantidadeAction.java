package eventos.trabalho1b.actions;

import eventos.trabalho1b.genericcontroller.GenericController;
import eventos.trabalho1b.model.Evento;
import eventos.trabalho1b.model.dao.EventoDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxQuantidadeAction extends GenericController{

    public AjaxQuantidadeAction(boolean needLogin) {
        super(needLogin);
    }

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Evento evento = EventoDao.getEventoById(Integer.parseInt(request.getParameter("evento")));
        
        int vagas = evento.getQuantIngressos() - evento.getIngressos().size();
       
        RequestDispatcher rd = request.getRequestDispatcher("Pages/Ingresso/ajaxQuantidade.jsp");
        
        request.setAttribute("vagas", vagas);
        
        rd.forward(request, response);
        
    }
    
}
