package br.com.coltran.farmacinhapp.domain;

import br.com.coltran.farmacinhapp.domain.interfaces.TableEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name = "remedios")
public class Remedio implements TableEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nome;

    @Nullable
    private String informacoesAdicionais;

    @NotNull(message = "Informe o número de doses")
    @Positive(message = "O número de doses deve ser um valor acima de 0")
    private Integer doses;

    @NotNull(message = "Informe o consumo diário das doses")
    @Positive(message = "O consumo diário deve ser um valor acima de 0")
    private Integer consumoDiario;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @PastOrPresent(message = "A data de início de tratamento deve ser anterior à data de hoje")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss z", timezone = "America/Sao_Paulo")
    private ZonedDateTime dataInicioTratamento;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss z", timezone = "America/Sao_Paulo")
    private ZonedDateTime dataCriacao;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss z", timezone = "America/Sao_Paulo")
    private ZonedDateTime dataAlteracao;

    @ManyToMany(targetEntity = Gramatura.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "remedios_gramaturas",
        joinColumns = @JoinColumn(name = "remedio_id"),
        inverseJoinColumns = @JoinColumn(name = "gramatura_id")
    )
    @JsonManagedReference
    private Set<Gramatura> gramaturas;

    @NotNull(message = "Informe um tipo válido")
    @ManyToOne(targetEntity = TipoRemedio.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private TipoRemedio tipoRemedio;

    @ManyToOne(targetEntity = Farmacia.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Farmacia farmacia;

    public Remedio() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getInformacoesAdicionais() {
        return informacoesAdicionais;
    }

    public void setInformacoesAdicionais(String informacoesAdicionais) {
        this.informacoesAdicionais = informacoesAdicionais;
    }

    public Integer getDoses() {
        return doses;
    }

    public void setDoses(Integer doses) {
        this.doses = doses;
    }

    public Integer getConsumoDiario() {
        return consumoDiario;
    }

    public void setConsumoDiario(Integer consumoDiario) {
        this.consumoDiario = consumoDiario;
    }

    public ZonedDateTime getDataInicioTratamento() {
        return dataInicioTratamento;
    }

    public void setDataInicioTratamento(ZonedDateTime dataInicioTratamento) {
        this.dataInicioTratamento = dataInicioTratamento;
    }

    public ZonedDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(ZonedDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public ZonedDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(ZonedDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public Set<Gramatura> getGramaturas() {
        return gramaturas;
    }

    public void setGramaturas(Set<Gramatura> gramaturas) {
        this.gramaturas = gramaturas;
    }

    public TipoRemedio getTipoRemedio() {
        return tipoRemedio;
    }

    public void setTipoRemedio(TipoRemedio tipoRemedio) {
        this.tipoRemedio = tipoRemedio;
    }

    public Farmacia getFarmacia() {
        return farmacia;
    }

    public void setFarmacia(Farmacia farmacia) {
        this.farmacia = farmacia;
    }
}
