package eventos.trabalho1b.actions;

import eventos.trabalho1b.genericcontroller.GenericController;
import eventos.trabalho1b.model.dao.EventoDao;
import eventos.trabalho1b.model.dto.EventoSemIngressoDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewCadastraIngressoAction extends GenericController{

    public ViewCadastraIngressoAction(boolean needLogin) {
        super(needLogin);
    }

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        List<EventoSemIngressoDTO> eventos = EventoDao.getEventosFuturos();
        
        RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
        
        request.setAttribute("page", "Pages/Ingresso/cadastraIngresso.jsp");
        
        request.setAttribute("eventos", eventos);
        
        rd.forward(request, response);
        
    }
    
}
