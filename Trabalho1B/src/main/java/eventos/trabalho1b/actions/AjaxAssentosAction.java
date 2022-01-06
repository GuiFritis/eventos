package eventos.trabalho1b.actions;

import eventos.trabalho1b.genericcontroller.GenericController;
import eventos.trabalho1b.model.dao.EventoDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxAssentosAction extends GenericController{

    public AjaxAssentosAction(boolean needLogin) {
        super(needLogin);
    }

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int evento = request.getParameter("evento") != null?Integer.parseInt(request.getParameter("evento")):0;
        
        int setor = request.getParameter("setor") != null?Integer.parseInt(request.getParameter("setor")):-1;
        
        List<Integer> cadeiras = EventoDao.getCadeirasSessao(evento, setor);
       
        RequestDispatcher rd = request.getRequestDispatcher("Pages/Ingresso/ajaxAssentos.jsp");
        
        request.setAttribute("setor", setor);
        
        request.setAttribute("cadeiras", cadeiras);
        
        rd.forward(request, response);
        
    }
    
}
