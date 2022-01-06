package eventos.trabalho1b.actions;

import eventos.trabalho1b.genericcontroller.GenericController;
import eventos.trabalho1b.model.Usuario;
import eventos.trabalho1b.model.dao.UsuarioDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewListaUsuariosAction extends GenericController{

    public ViewListaUsuariosAction(boolean needLogin) {
        super(needLogin);
    }

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String filtro_login = request.getParameter("filtro_login")!=null?request.getParameter("filtro_login"):"";
        
        String filtro_adm = request.getParameter("filtro_adm")!=null?request.getParameter("filtro_adm"):"";
        
        List<Usuario> usuarios = UsuarioDao.getFiltroUsuarios(filtro_login, filtro_adm);
       
        RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
        
        request.setAttribute("page", "Pages/Usuario/listaUsuario.jsp");
        
        request.setAttribute("filtro_login", filtro_login);
        
        request.setAttribute("filtro_adm", filtro_adm);
        
        request.setAttribute("usuarios", usuarios);
        
        rd.forward(request, response);
        
    }
    
}
