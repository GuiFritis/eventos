package eventos.trabalho1b.model.dto;

import eventos.trabalho1b.model.Local;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoSemIngressoDTO {
    
    int idevento;
    String nome;
    int quantIngressos;
    String tipoEvento;
    double precoIngresso;
    Date horario;
    Local local;
    
    public String getStringData(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return sdf.format(horario);
    }

}
