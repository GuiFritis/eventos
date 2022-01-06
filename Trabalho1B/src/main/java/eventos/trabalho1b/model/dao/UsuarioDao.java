package eventos.trabalho1b.model.dao;

import eventos.trabalho1b.model.Usuario;
import static eventos.trabalho1b.model.dao.DaoBase.getCon;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

public class UsuarioDao extends DaoBase{
    
    public static Usuario getUsuarioById(int id){

        Query q = getCon().createNamedQuery("Usuario.findByIdUsuario");

        q.setParameter("id", id);

        try{
            return (Usuario) q.getSingleResult();
        } catch (Exception ex){
            return new Usuario();
        }

    }
        
    public static List<Usuario> getFiltroUsuarios(String login, String isAdmin){
        
        Query q = getCon().createQuery("SELECT u FROM Usuario u "
                + " WHERE (u.login like :login OR '' = :login) "
                + " AND (u.adm = :isAdmin OR '' = :isAdmin) ");
        
        q.setParameter("login", login + "%");
        q.setParameter("isAdmin", isAdmin);
        
        return q.getResultList();
        
    }
    
    public static Usuario verifyUser(String login, String senha) throws SQLException{

        Query q = getCon().createNamedQuery("Usuario.findByLoginAndSenha");

        q.setParameter("login", login);
        q.setParameter("senha", senha);

        try {

            return (Usuario) q.getSingleResult(); 

        } catch (NonUniqueResultException e) {
            throw new SQLException("foi encontrado 2 usuários no banco, contate o administrador");
        } catch (NoResultException e){
            return null;
        }        

    }
      
    public static Boolean VerifyLogin(String login){

        Query q = getCon().createQuery("SELECT COUNT(u) FROM Usuario u WHERE u.login = :login");

        q.setParameter("login", login);

        Long l = (Long) q.getSingleResult();

        return l.intValue() > 0;

    }
    
}
