package eventos.trabalho1b.actions;

import eventos.trabalho1b.genericcontroller.GenericController;
import eventos.trabalho1b.model.Evento;
import eventos.trabalho1b.model.Ingresso;
import eventos.trabalho1b.model.dao.EventoDao;
import eventos.trabalho1b.model.dao.IngressoDao;
import eventos.trabalho1b.model.dto.EventoSemIngressoDTO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FinalizaPagamentoAction extends GenericController{

    public FinalizaPagamentoAction(boolean needLogin) {
        super(needLogin);
    }

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int id_evento= Integer.parseInt(request.getParameter("evento"));
        
        Evento evento = EventoDao.getEventoById(id_evento);
        
        int ultimoIngresso = IngressoDao.getMaiorNumeroEvento(id_evento);
        
        String tipo_entrada = request.getParameter("tipo_entrada");
        
        int[] assentos;
        
        if(evento.getLocal().getNome().equalsIgnoreCase("anfiteatro")){
            assentos = intParaStr(request.getParameterValues("assento"));
        } else {
            assentos = arraySalao(ultimoIngresso + 1, Integer.parseInt(request.getParameter("qtd_ingressos")));
        }
        
        IngressoDao.getCon().getTransaction().begin();
        for(int assento : assentos){
            IngressoDao.getCon().persist(new Ingresso(0, tipo_entrada, assento, evento));            
        }
        IngressoDao.getCon().getTransaction().commit();
        
        
        request.setAttribute("msg", "Ingressos inseridos com sucesso!");

        new ViewCadastraIngressoAction(true).executa(request, response);
        
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
    private int[] arraySalao(int inicio, int tamanho){
        
        int[] assentos = new int[tamanho];
        
        for(int i = 0; i < tamanho; i++){
            assentos[i] = inicio + i;
        }        
        
        return assentos;
    }
    
}
