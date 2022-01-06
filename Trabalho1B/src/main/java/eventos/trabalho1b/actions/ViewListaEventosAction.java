package eventos.trabalho1b.actions;

import eventos.trabalho1b.genericcontroller.GenericController;
import eventos.trabalho1b.model.Evento;
import eventos.trabalho1b.model.Local;
import eventos.trabalho1b.model.dao.EventoDao;
import eventos.trabalho1b.model.dao.IngressoDao;
import eventos.trabalho1b.model.dao.LocalDao;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewListaEventosAction extends GenericController{

    public ViewListaEventosAction(boolean needLogin) {
        super(needLogin);
    }

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
                               
        request.setAttribute("page", "/Pages/Evento/listaEvento.jsp");
     
        List<Evento> eventos = null;
        
        String filtro_tipo = null;
        String filtro_nome = null;
        Local filtro_local = null;
        Date filtro_dataInic = null;
        Date filtro_dataFim = null;
        String filtro_preco_1 = null;
        String filtro_preco_2 = null;
        String filtro_ingressos_1 = null;
        String filtro_ingressos_2 = null;
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        boolean action = Boolean.valueOf(request.getParameter("filtrar"));
        
        if(action){
               
            if(request.getParameter("filtro_tipo") != null && !request.getParameter("filtro_tipo").equals("Todos")) {
                filtro_tipo = request.getParameter("filtro_tipo");
                request.setAttribute("filtro_tipo", filtro_tipo);
            }    

            if(request.getParameter("filtro_nome") != null && !request.getParameter("filtro_nome").isEmpty()){
                filtro_nome = request.getParameter("filtro_nome");
                request.setAttribute("filtro_nome", filtro_nome);
            }  

            if(request.getParameter("filtro_local") != null && !request.getParameter("filtro_local").isEmpty()){
                int idlocal = Integer.parseInt(request.getParameter("filtro_local"));           
                filtro_local = LocalDao.getLocalById(idlocal);                    
                request.setAttribute("filtro_local", filtro_local);
            }

            if(request.getParameter("data") != null && !request.getParameter("data").isEmpty()){
                String data = request.getParameter("data");

                String[] dataformatada = data.split("-");

                try {
                    filtro_dataInic = sdf.parse(dataformatada[0]);
                    filtro_dataFim =  sdf.parse(dataformatada[1]);
                } catch (ParseException ex) {
                    Logger.getLogger(ViewListaEventosAction.class.getName()).log(Level.SEVERE, null, ex);
                }

                request.setAttribute("data", data);              
            }

            if(request.getParameter("filtro_preco_1") != null && !request.getParameter("filtro_preco_1").isEmpty()){
                filtro_preco_1 = request.getParameter("filtro_preco_1");
                request.setAttribute("filtro_preco_1", filtro_preco_1);
            }

            if(request.getParameter("filtro_preco_2") != null && !request.getParameter("filtro_preco_2").isEmpty()){
                filtro_preco_2 = request.getParameter("filtro_preco_2");
                request.setAttribute("filtro_preco_2", filtro_preco_2);
            }

            if(request.getParameter("filtro_ingressos_1") != null && !request.getParameter("filtro_ingressos_1").isEmpty()){
                filtro_ingressos_1 = request.getParameter("filtro_ingressos_1");
                request.setAttribute("filtro_ingressos_1", filtro_ingressos_1);
            }

            if(request.getParameter("filtro_ingressos_2") != null && !request.getParameter("filtro_ingressos_2").isEmpty()){
                filtro_ingressos_2 = request.getParameter("filtro_ingressos_2");
                request.setAttribute("filtro_ingressos_2", filtro_ingressos_2);
            }
        }
                
        eventos =  EventoDao.getEventosFiltro( filtro_tipo, filtro_nome, filtro_local, filtro_dataInic, filtro_dataFim, filtro_preco_1, filtro_preco_2, filtro_ingressos_1, filtro_ingressos_2 ) ;

        for(Evento ev : eventos){
            ev.setIngressos(IngressoDao.getIngressosByEvento(ev.getIdevento()));
        }
        
        request.setAttribute("eventos", eventos);
         
        rd.forward(request, response);
        
    }
    
}
