package eventos.trabalho1b.actions;

import eventos.trabalho1b.genericcontroller.GenericController;
import eventos.trabalho1b.model.dao.EventoDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxCabinesAction extends GenericController{

    public AjaxCabinesAction(boolean needLogin) {
        super(needLogin);
    }

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int evento = request.getParameter("evento") != null?Integer.parseInt(request.getParameter("evento")):0;
        
        List<Integer> cadeiras = EventoDao.getCadeirasSessao(evento, 0);
       
        RequestDispatcher rd = request.getRequestDispatcher("Pages/Ingresso/ajaxCabines.jsp");
        
        request.setAttribute("cadeiras", cadeiras);
        
        rd.forward(request, response);
        
    }
    
}
