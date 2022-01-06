package eventos.trabalho1b.actions;

import eventos.trabalho1b.genericcontroller.GenericController;
import eventos.trabalho1b.model.Ingresso;
import eventos.trabalho1b.model.dao.IngressoDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteIngressoAction extends GenericController {

    public DeleteIngressoAction(boolean b) {
        super(b);
    }

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Ingresso ingresso = IngressoDao.getIngressoById(Integer.parseInt(request.getParameter("id")));
        
        IngressoDao.getCon().getTransaction().begin();
        IngressoDao.getCon().remove(ingresso);
        IngressoDao.getCon().getTransaction().commit();
        
        request.setAttribute("msg", "Ingresso deletado com sucesso!");
        
        new ViewListaEventosAction(true).executa(request, response);
        
    }
    
}
