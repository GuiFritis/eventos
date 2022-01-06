package eventos.trabalho1b.actions;

import eventos.trabalho1b.genericcontroller.GenericController;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewHomeAction extends GenericController{

    public ViewHomeAction(boolean needLogin) {
        super(needLogin);
    }

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
        
        request.setAttribute("page", "Pages/home.jsp");
        
        rd.forward(request, response);
        
    }
    
}
