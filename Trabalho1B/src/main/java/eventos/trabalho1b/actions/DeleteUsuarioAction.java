package eventos.trabalho1b.actions;

import eventos.trabalho1b.genericcontroller.GenericController;
import eventos.trabalho1b.model.Usuario;
import eventos.trabalho1b.model.dao.UsuarioDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUsuarioAction extends GenericController {

    public DeleteUsuarioAction(boolean b) {
        super(b);
    }

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Usuario usuario = UsuarioDao.getUsuarioById( Integer.parseInt( request.getParameter("id") ) );
        
        UsuarioDao.getCon().getTransaction().begin();
        UsuarioDao.getCon().remove(usuario);
        UsuarioDao.getCon().getTransaction().commit();
        
        request.setAttribute("msg", "Usuario excluído com sucesso!");
        
        new ViewListaUsuariosAction(true).executa(request, response);
        
    }
    
}
