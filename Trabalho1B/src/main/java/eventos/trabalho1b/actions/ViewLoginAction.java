package eventos.trabalho1b.actions;

import eventos.trabalho1b.genericcontroller.GenericController;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewLoginAction extends GenericController{

    public ViewLoginAction(boolean needLogin) {
        super(needLogin);
    }

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        RequestDispatcher rd = request.getRequestDispatcher("Pages\\login.jsp");
        
        rd.forward(request, response);
        
    }
    
}
