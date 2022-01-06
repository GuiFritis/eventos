package eventos.trabalho1b.model.dto;

import lombok.Data;

@Data
public class IngressoDTO {
    
    String tipoEntrada;
    double precoIngresso;
    int numero;

    public IngressoDTO(String tipoEntrada, double precoIngresso, int numero) {
        this.tipoEntrada = tipoEntrada;
        this.precoIngresso = precoIngresso;
        this.numero = numero;
    }

    public IngressoDTO() {
    }
    
    public double calculaPrecoIngresso(){
        if(numero >= 487){
            return precoIngresso * 1.25;
        } else {
            if(tipoEntrada.equalsIgnoreCase("doacao")){
                return 0;
            } else if (tipoEntrada.equalsIgnoreCase("meia")){
                return precoIngresso/2;
            } else {
                return precoIngresso;
            }
        }
    }  
}
