package eventos.trabalho1b.actions;

import eventos.trabalho1b.genericcontroller.GenericController;
import eventos.trabalho1b.model.Evento;
import eventos.trabalho1b.model.Ingresso;
import eventos.trabalho1b.model.Local;
import eventos.trabalho1b.model.dao.EventoDao;
import eventos.trabalho1b.model.dao.LocalDao;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewCadastraEventoAction extends GenericController{

    public ViewCadastraEventoAction(boolean needLogin) {
        super(needLogin);
    }

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
        
        request.setAttribute("page", "Pages/Evento/cadastraEvento.jsp");
        
        String titulo;
        Evento eventoBusca = new Evento();
        eventoBusca.setPrecoIngresso(1);
        
        eventoBusca.setIngressos(new ArrayList<Ingresso>());

        if (request.getParameter("id") != null){
            titulo = "Alterar Evento";
            eventoBusca = EventoDao.getEventoById( Integer.parseInt(request.getParameter("id")) );
        }else{
            titulo = "Novo Evento";
        }                
        
        Local salao = LocalDao.getLocalByNome("Salão");
        Local anfiteatro = LocalDao.getLocalByNome("Anfiteatro");
        
        request.setAttribute("salao", salao);
        request.setAttribute("anfiteatro", anfiteatro);
        
        request.setAttribute("evento", eventoBusca);
        request.setAttribute("titulo", titulo);
        
        rd.forward(request, response);
        
    }  
}
