package eventos.trabalho1b.actions;

import eventos.trabalho1b.genericcontroller.GenericController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewLogoutAction extends GenericController {

    public ViewLogoutAction(boolean needLogin) {
        super(needLogin);
    }

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
        request.getSession().invalidate();
               
       response.sendRedirect("control");
       
    }
 
}
