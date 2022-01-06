package eventos.trabalho1b.actions;

import eventos.trabalho1b.genericcontroller.GenericController;
import eventos.trabalho1b.model.Usuario;
import eventos.trabalho1b.model.dao.UsuarioDao;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveUserAction extends GenericController {

    public SaveUserAction(boolean b) {
        super(b);
    }

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try {

            Usuario usuario;
            String msg;
       
            if (request.getParameter("idUsuario").equals("")) {

                usuario = new Usuario(
                        0, 
                        request.getParameter("login"), 
                        request.getParameter("senha"),
                        request.getParameter("isAdmin")!=null?"SIM":"NÃO");
                
                
                if(UsuarioDao.VerifyLogin(usuario.getLogin())){
                    
                    msg = "Já existe um evento cadastrado com esse nome.";
                         
                } else if(!request.getParameter("senha").equals(request.getParameter("senha_redigita"))){
                    
                    msg = "As senhas não conferem!";
                    
                } else {
                        
                    UsuarioDao.getCon().getTransaction().begin();
 
                    msg = "Usuário criado com sucesso!!!";
                    
                    UsuarioDao.getCon().persist(usuario);
            
                    UsuarioDao.getCon().getTransaction().commit();
  
                }
           
            } else {
                
                if(request.getParameter("senha").equals(request.getParameter("senha_redigita"))){
                
                    usuario = UsuarioDao.getUsuarioById(( Integer.parseInt(request.getParameter("idUsuario")) ));
                    
                    if(!request.getParameter("senha").equals("")){
                        usuario.setSenha(request.getParameter("senha"));
                    }
                    
                    Usuario logado = (Usuario) request.getSession().getAttribute("user");
                    if(logado.getIdusuario() != usuario.getIdusuario()){
                        usuario.setAdm(request.getParameter("isAdmin")!=null?"SIM":"NÃO");
                    }
                    
                    msg = "Usuário alterado com sucesso!!!";                    
                    
                    UsuarioDao.getCon().getTransaction().begin();

                    UsuarioDao.getCon().persist(usuario);

                    UsuarioDao.getCon().getTransaction().commit();
                } else {
                    msg = "Senhas não são iguais!";
                }
                
            }
                   
            request.setAttribute("msg", msg);

            new ViewListaUsuariosAction(true).executa(request, response);
            
    } catch (Exception ex) {
            throw new ServletException("Erro ao cadastrar Usuário." + ex);
        }                 
    }    
}
