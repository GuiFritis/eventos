package eventos.trabalho1b.controle;

import eventos.trabalho1b.actions.SaveEventoAction;
import eventos.trabalho1b.actions.ViewLogoutAction;
import eventos.trabalho1b.actions.AjaxAssentosAction;
import eventos.trabalho1b.actions.AjaxCabinesAction;
import eventos.trabalho1b.actions.AjaxLocalAnfiteatroAction;
import eventos.trabalho1b.actions.AjaxQuantidadeAction;
import eventos.trabalho1b.actions.AjaxSetorAction;
import eventos.trabalho1b.actions.AjaxValidaData;
import eventos.trabalho1b.actions.DeleteEventoAction;
import eventos.trabalho1b.actions.DeleteIngressoAction;
import eventos.trabalho1b.actions.DeleteUsuarioAction;
import eventos.trabalho1b.actions.FinalizaPagamentoAction;
import eventos.trabalho1b.actions.RealizaLoginAction;
import eventos.trabalho1b.actions.SaveUserAction;
import eventos.trabalho1b.actions.ViewCadastraEventoAction;
import eventos.trabalho1b.actions.ViewCadastraIngressoAction;
import eventos.trabalho1b.actions.ViewCadastraUsuarioAction;
import eventos.trabalho1b.actions.ViewHomeAction;
import eventos.trabalho1b.actions.ViewListaEventosAction;
import eventos.trabalho1b.actions.ViewListaIngressosAction;
import eventos.trabalho1b.actions.ViewListaUsuariosAction;
import eventos.trabalho1b.actions.ViewLoginAction;
import eventos.trabalho1b.actions.ViewPagamentoIngressoAction;
import eventos.trabalho1b.actions.ViewRelatorioAction;
import eventos.trabalho1b.genericcontroller.GenericController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ControleCentral", urlPatterns = {"/control"})
public class ControleCentral extends HttpServlet {
    
    
    
    static HashMap<String, GenericController> command;
    
    static{
        command = new HashMap<>();
        
        command.put(null, new ViewLoginAction(false) );
        command.put("logar", new RealizaLoginAction(false));
        command.put("logout", new ViewLogoutAction(false));
        command.put("home", new ViewHomeAction(true));
        
        command.put("listaEventos", new ViewListaEventosAction(true));
        command.put("cadastraEvento", new ViewCadastraEventoAction(true));
        command.put("insertEvento", new SaveEventoAction(true));
        command.put("updateEvento", new SaveEventoAction(true));
        command.put("cancelaEvento", new DeleteEventoAction(true));
        
        command.put("listaIngressos", new ViewListaIngressosAction(true));
        command.put("cadastraIngresso", new ViewCadastraIngressoAction(true));
        command.put("pagaIngresso", new ViewPagamentoIngressoAction(true));
        command.put("finalizaCompra", new FinalizaPagamentoAction(true));
        command.put("cancelaIngresso", new DeleteIngressoAction(true));
        
        command.put("listaRelatorios", new ViewRelatorioAction(true));
        
        command.put("listaUsuarios", new ViewListaUsuariosAction(true));
        command.put("cadastraUsuario", new ViewCadastraUsuarioAction(true));
        command.put("insertUsuario", new SaveUserAction(true));        
        command.put("updateUsuario", new SaveUserAction(true));  
        command.put("excluiUsuario", new DeleteUsuarioAction(true));
        
        command.put("ajaxCarregaSetor", new AjaxSetorAction(true));
        command.put("ajaxCarregaQuantidade", new AjaxQuantidadeAction(true));
        command.put("ajaxCarregaAssentos", new AjaxAssentosAction(true));
        command.put("ajaxCarregaCabines", new AjaxCabinesAction(true));
        command.put("ajaxCarregaLocalAnfiteatro", new AjaxLocalAnfiteatroAction(true));
        command.put("ajaxValidaData", new AjaxValidaData(true));
       
        
    }        
           

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String action = request.getParameter("action");
            
            try {
                
                if (!command.get(action).isNeedLogin() || request.getSession().getAttribute("user") != null){
                    
                    command.get(action).executa(request, response);                
                }else{
                    request.setAttribute("msg", "acesso não autorizado!!!");
                    new ViewLoginAction(false).executa(request, response);
                }
                
            } catch (Exception e) {
                RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
                
                request.setAttribute("page", "Pages/error.jsp");
                request.setAttribute("error", e.getMessage());
                
                rd.forward(request, response);
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
