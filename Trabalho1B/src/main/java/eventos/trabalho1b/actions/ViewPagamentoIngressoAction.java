package eventos.trabalho1b.actions;

import eventos.trabalho1b.genericcontroller.GenericController;
import eventos.trabalho1b.model.Ingresso;
import eventos.trabalho1b.model.dao.EventoDao;
import eventos.trabalho1b.model.dto.EventoSemIngressoDTO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewPagamentoIngressoAction extends GenericController{

    public ViewPagamentoIngressoAction(boolean needLogin) {
        super(needLogin);
    }

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int id_evento= Integer.parseInt(request.getParameter("evento"));
        
        EventoSemIngressoDTO evento = EventoDao.getEventoSemIngressoById(id_evento);
        
        String tipo_entrada = request.getParameter("tipo_entrada");
        
        int qtdIngressos = 0;
        
        int[] assentos = new int[0];
        
        String lugar = request.getParameter("lugar")!=null?request.getParameter("lugar"):"";
        if(evento.getLocal().getNome().equalsIgnoreCase("Anfiteatro")){
            if(lugar.equalsIgnoreCase("plateia")){
                assentos = intParaStr(request.getParameterValues("assento"));
                qtdIngressos = assentos.length;
            }else{
                assentos = arrayCabines(request.getParameter("assento"));
                qtdIngressos = assentos.length;
            }
        } else {
            qtdIngressos = request.getParameter("qtd_ingressos") != null? Integer.parseInt(request.getParameter("qtd_ingressos")):0;
        }
        
        double preco = Ingresso.CalculaPrecoIngresso(tipo_entrada, lugar, evento.getPrecoIngresso());
       
        RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
        
        if(qtdIngressos > 0){
        
            request.setAttribute("page", "Pages/Ingresso/pagamentoIngresso.jsp");

            request.setAttribute("evento", evento);
            
            request.setAttribute("tipo_entrada", tipo_entrada);

            request.setAttribute("preco", preco);

            request.setAttribute("qtdIngressos", qtdIngressos);

            request.setAttribute("lugar", lugar);

            request.setAttribute("assentos", assentos);
            
        } else {
            
            request.getRequestDispatcher("control");
            
            request.setAttribute("action", "cadastraIngresso");
            
            request.setAttribute("msg", "Algo deu errado");
        }
        
        rd.forward(request, response);
        
    }
    
    //transforma um array de string em um array de inteiros
    private int[] intParaStr(String[] assentos){
        int[] assentosInt = new int[assentos.length];
        
        for(int i = 0; i < assentos.length; i++){
            assentosInt[i] = Integer.parseInt(assentos[i]);
        }
        
        return assentosInt;
    }
    
    //preenche um array de 10 espaços somando 1 a partir do primeiro valor
    private int[] arrayCabines(String cabine){
        int[] assentos = new int[10];
        
        assentos[0] = Integer.parseInt(cabine);
        
        for(int i = 1; i < 10; i++){
            assentos[i] = assentos[0] + i;
        }        
        
        return assentos;
    }
    
}
