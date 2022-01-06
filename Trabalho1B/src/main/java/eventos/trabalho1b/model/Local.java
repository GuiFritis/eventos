package eventos.trabalho1b.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Entity
@Data
@Table(catalog = "eventos", schema = "", name = "local")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedQueries({
    @NamedQuery(name = "Local.findAll", query = "SELECT l FROM Local l"),
    @NamedQuery(name = "Local.findByIdLocal", query = "SELECT l FROM Local l WHERE l.idlocal = :id"),
    @NamedQuery(name = "Local.findByCapacidadeMax", query = "SELECT l FROM Local l WHERE l.capacidadeMax = :max"),
    @NamedQuery(name = "Local.findByNome", query = "SELECT l FROM Local l WHERE l.nome = :nome"),
})
public class Local implements Serializable{
       
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false) 
    private Integer idlocal;
    
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int capacidadeMax;
    
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private double aluguel;
    
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private double limpeza;
    
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private double luz;
    
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private double agua;
    
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private String nome;
        
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "local", fetch = FetchType.LAZY)
    private List<Evento> eventos;
    
}
