package eventos.trabalho1b.actions;

import eventos.trabalho1b.genericcontroller.GenericController;
import eventos.trabalho1b.model.Evento;
import eventos.trabalho1b.model.Ingresso;
import eventos.trabalho1b.model.dao.EventoDao;
import eventos.trabalho1b.model.dao.IngressoDao;
import eventos.trabalho1b.model.dto.EventoSemIngressoDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewListaIngressosAction extends GenericController{

    public ViewListaIngressosAction(boolean needLogin) {
        super(needLogin);
    }

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Boolean filtrar = request.getParameter("filtrar") != null?request.getParameter("filtrar").equals("1"):false;
        
        String str_numero = request.getParameter("filtro_numero")!=null?request.getParameter("filtro_numero"):"";
        
        String filtro_entrada = request.getParameter("filtro_entrada")!=null?request.getParameter("filtro_entrada"):"";
        
        String str_setor = request.getParameter("filtro_setor")!=null?request.getParameter("filtro_setor"):"";
        
        int filtro_numero = -1;
        try{
            filtro_numero = Integer.parseInt(str_numero);
        } catch(Exception e){
            filtro_numero = -1;
        }
        
        int filtro_setor = -1;
        try{
            filtro_setor = Integer.parseInt(str_setor);
        } catch(Exception e){
            filtro_setor = -1;
        }
       
        int id_evento = Integer.parseInt(request.getParameter("evento")!=null?request.getParameter("evento"):"0");
        
        EventoSemIngressoDTO evento = EventoDao.getEventoSemIngressoById(id_evento);
        
        List<Ingresso> ingressos = new ArrayList<Ingresso>();
        
        if(filtrar){
            ingressos = IngressoDao.getListaIngressosFiltro(evento.getIdevento(), filtro_entrada, filtro_numero, filtro_setor);
        } else {
            ingressos = IngressoDao.getIngressosByEvento(id_evento);
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
        
        if(evento.getIdevento() > 0){
            request.setAttribute("page", "Pages/Ingresso/listaIngresso.jsp");
            
            request.setAttribute("filtrar", filtrar);
            
            request.setAttribute("filtro_numero", str_numero);
            
            request.setAttribute("filtro_entrada", filtro_entrada);
            
            request.setAttribute("filtro_setor", filtro_setor);

            request.setAttribute("evento", evento);
            
            request.setAttribute("ingressos", ingressos);
            
        } else {
            request.setAttribute("page", "Pages/error.jsp");
        }
        
        rd.forward(request, response);
        
    }
    
}
