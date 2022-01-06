package eventos.trabalho1b.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(catalog = "eventos", schema = "", name = "evento")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedQueries({
    @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e"),
    @NamedQuery(name = "Evento.findByIdEvento", query = "SELECT e FROM Evento e WHERE e.idevento = :id"),
    @NamedQuery(name = "Evento.findByNome", query = "SELECT e FROM Evento e WHERE e.nome = :nome"),
    @NamedQuery(name = "Evento.findByQuantIngressos", query = "SELECT e FROM Evento e WHERE e.quantIngressos = :quant"),
    @NamedQuery(name = "Evento.findByTipoEvento", query = "SELECT e FROM Evento e WHERE e.tipoEvento = :tipo"),
    @NamedQuery(name = "Evento.findByPrecoIngresso", query = "SELECT e FROM Evento e WHERE e.precoIngresso = :preco")
})
public class Evento implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idevento;
    
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private String nome;
    
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int quantIngressos;
    
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private String tipoEvento;
    
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private double precoIngresso;
    
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date horario;
    
    @JoinColumn(name = "local", referencedColumnName = "idlocal")
    @ManyToOne(optional = false)
    private Local local;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evento", fetch = FetchType.LAZY)
    private List<Ingresso> ingressos;

    public String getStringData(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return sdf.format(horario);
    }
    
}
