package eventos.trabalho1b.model.dto;

import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EventoDTO {
    
    int idevento;
    String tipoEvento;
    String nomeLocal;
    String nomeEvento;
    
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date horario;
    
    double luz;
    double agua;
    double limpeza;
    double aluguel;
    
    List<IngressoDTO> ingressos;

    public EventoDTO(int idevento, String tipoEvento, String nomeLocal, String nomeEvento, Date horario, double luz, double agua, double limpeza, double aluguel) {
        this.idevento = idevento;
        this.tipoEvento = tipoEvento;
        this.nomeLocal = nomeLocal;
        this.nomeEvento = nomeEvento;
        this.horario = horario;
        this.luz = luz;
        this.agua = agua;
        this.limpeza = limpeza;
        this.aluguel = aluguel;
    }

    public EventoDTO() {
    }
       
    public double despesas(){
        return luz + agua + limpeza;       
    }
    
    public double ganhos(){
        double ganho = this.aluguel;
        
        for(IngressoDTO ing : ingressos){
            ganho += ing.calculaPrecoIngresso();
        }
        
        return ganho;
    }
    
    public double lucro(){
        return ganhos() - despesas();
    }
        
}
