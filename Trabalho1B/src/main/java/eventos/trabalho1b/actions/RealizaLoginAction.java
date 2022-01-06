package eventos.trabalho1b.actions;

import eventos.trabalho1b.genericcontroller.GenericController;
import eventos.trabalho1b.model.Usuario;
import eventos.trabalho1b.model.dao.UsuarioDao;
import java.io.IOException;
import java.rmi.ServerException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RealizaLoginAction extends GenericController{

    public RealizaLoginAction(boolean needLogin) {
        super(needLogin);
    }

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        try {
             
            String username = request.getParameter("username");
            String senha = request.getParameter("senha");
            
            Usuario u = new UsuarioDao().verifyUser(username, senha);
                        
            if ( u != null  ) {
                
                request.getSession().setAttribute("user", u);
                
                new ViewHomeAction(true).executa(request, response);
                                
            } else {
                response.sendRedirect("control?error=1");
            }
        } catch (SQLException ex) {
            throw new ServerException(ex.getMessage());
        }
   
    }
    
}
