package eventos.trabalho1b.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(catalog = "eventos", schema = "", name = "ingresso")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedQueries({
    @NamedQuery(name = "Ingresso.findAll", query = "SELECT i FROM Ingresso i"),
    @NamedQuery(name = "Ingresso.findByIdIngresso", query = "SELECT i FROM Ingresso i WHERE i.idingresso = :id"),
    @NamedQuery(name = "Ingresso.findByTipoEntrada", query = "SELECT i FROM Ingresso i WHERE i.tipoEntrada = :tipo"),
    @NamedQuery(name = "Ingresso.findByNumero", query = "SELECT i FROM Ingresso i WHERE i.numero = :numero")})
public class Ingresso implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false) 
    private Integer idingresso;
    
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private String tipoEntrada;
    
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int numero;

    @JoinColumn(name = "evento", referencedColumnName = "idevento")
    @ManyToOne(optional = false)
    private Evento evento;
       
    public double CalculaPreco(){
        if(numero >= 487){
            return evento.getPrecoIngresso() * 1.25;
        } else {
            if(tipoEntrada.equalsIgnoreCase("doacao")){
                return 0;
            } else if (tipoEntrada.equalsIgnoreCase("meia")){
                return evento.getPrecoIngresso()/2;
            } else {
                return evento.getPrecoIngresso();
            }
        }
    }
    
    public static double CalculaPrecoIngresso(String tipoEntrada, String lugar, double precoEvento){
        
        if(lugar.equalsIgnoreCase("cabine")){
            return precoEvento * 1.25;
        } else {
            if(tipoEntrada.equalsIgnoreCase("doacao")){
                return 0;
            } else if (tipoEntrada.equalsIgnoreCase("meia")){
                return precoEvento/2;
            } else {
                return precoEvento;
            }
        }
        
    }
    
}
