package eventos.trabalho1b.actions;

import eventos.trabalho1b.genericcontroller.GenericController;
import eventos.trabalho1b.model.dao.EventoDao;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxValidaData extends GenericController{

    public AjaxValidaData(boolean needLogin) {
        super(needLogin);
    }

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                
        // Permite apenas datas com intervalo de 24h antes e depois //
        try (PrintWriter out = response.getWriter()) {
                        
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

            Date data = sdf.parse(request.getParameter("data"));
            
            boolean dataDisponivel = EventoDao.getEventoByHorario(data);
        
            if (dataDisponivel) {                   
                out.println("1");
            } else {
                out.println("0");
            }  
                      
        } catch (Exception ex) {
            Logger.getLogger(AjaxValidaData.class.getName()).log(Level.SEVERE, null, ex);
        }                
  
    }
    
}
