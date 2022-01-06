package eventos.trabalho1b.actions;

import eventos.trabalho1b.genericcontroller.GenericController;
import eventos.trabalho1b.model.Usuario;
import eventos.trabalho1b.model.dao.UsuarioDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewCadastraUsuarioAction extends GenericController{

    public ViewCadastraUsuarioAction(boolean needLogin) {
        super(needLogin);
    }

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int id = 0;
        String titulo;
        Usuario usuario;
        try{
            id = Integer.parseInt(request.getParameter("id")!=null?request.getParameter("id"):"0");
        } catch(Exception ex){
            id = 0;
        }
        
        if(id > 0){
            usuario = UsuarioDao.getUsuarioById(id);
            
            titulo = "Atualizar Usuario";
        } else {
            usuario = new Usuario();
            
            titulo = "Cadastrar Usuario";
        }
       
        RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
        
        request.setAttribute("page", "Pages/Usuario/cadastraUsuario.jsp");
        
        request.setAttribute("usuario", usuario);
        
        request.setAttribute("titulo", titulo);
        
        rd.forward(request, response);
        
    }
    
}
