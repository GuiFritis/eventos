package eventos.trabalho1b.actions;

import eventos.trabalho1b.genericcontroller.GenericController;
import eventos.trabalho1b.model.Local;
import eventos.trabalho1b.model.dao.EventoDao;
import eventos.trabalho1b.model.dao.IngressoDao;
import eventos.trabalho1b.model.dao.LocalDao;
import eventos.trabalho1b.model.dto.EventoDTO;
import eventos.trabalho1b.model.dto.IngressoDTO;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewRelatorioAction extends GenericController {

    public ViewRelatorioAction(boolean b) {
        super(b);
    }

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
                               
        request.setAttribute("page", "/Pages/Relatorios/listaRelatorio.jsp");
     
        List<EventoDTO> eventos = null;
        List<IngressoDTO> ingressos = null;
        
        double despesaTotal = 0;
        double ganhoTotal = 0;
        double lucroTotal = 0;
        
        String filtro_tipo = null;
        Local filtro_local = null;
        Date filtro_dataInic = null;
        Date filtro_dataFim = null;
                
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        boolean action = Boolean.valueOf(request.getParameter("filtrar"));
        
        if(action){

            if(request.getParameter("filtro_tipo") != null && !request.getParameter("filtro_tipo").equals("Todos")) {
                filtro_tipo = request.getParameter("filtro_tipo");
                request.setAttribute("filtro_tipo", filtro_tipo);
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

        }
              
        eventos =  EventoDao.getEventosRelatorio( filtro_tipo, filtro_local, filtro_dataInic, filtro_dataFim ) ;
         
        for(EventoDTO ev : eventos){
            ingressos = IngressoDao.getIngressosRelatorio(ev.getIdevento());
            ev.setIngressos(ingressos);
            
            despesaTotal += ev.despesas();
            ganhoTotal += ev.ganhos();
            lucroTotal += ev.lucro();
        }
        
        request.setAttribute("eventos", eventos);
        
        request.setAttribute("despesaTotal", despesaTotal);
        request.setAttribute("ganhoTotal", ganhoTotal);
        request.setAttribute("lucroTotal", lucroTotal);
                   
        rd.forward(request, response);
        
    }
      
}
