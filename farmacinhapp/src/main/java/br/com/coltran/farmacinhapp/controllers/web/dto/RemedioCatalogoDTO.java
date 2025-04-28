package br.com.coltran.farmacinhapp.controllers.web.dto;
import br.com.coltran.farmacinhapp.domain.Gramatura;
import br.com.coltran.farmacinhapp.domain.Remedio;
import com.fasterxml.jackson.annotation.JsonFormat;


import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class RemedioCatalogoDTO {

    private long id;
    private String nome;
    private String farmaciaAlocado;
    private String tipoRemedio;
    private List<String> principiosAtivos;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
    private ZonedDateTime dataInicioTratamento;

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

    public String getTipoRemedio() {
        return tipoRemedio;
    }

    public void setTipoRemedio(String tipoRemedio) {
        this.tipoRemedio = tipoRemedio;
    }

    public String getFarmaciaAlocado() {
        return farmaciaAlocado;
    }

    public void setFarmaciaAlocado(String farmaciaAlocado) {
        this.farmaciaAlocado = farmaciaAlocado;
    }

    public ZonedDateTime getDataInicioTratamento() {
        return dataInicioTratamento;
    }

    public void setDataInicioTratamento(ZonedDateTime dataInicioTratamento) {
        this.dataInicioTratamento = dataInicioTratamento;
    }

    public List<String> getPrincipiosAtivos() {
        return principiosAtivos;
    }

    public void setPrincipiosAtivos(List<String> principiosAtivos) {
        this.principiosAtivos = principiosAtivos;
    }

    public static class Builder {

        private final RemedioCatalogoDTO dto;

        public Builder(Remedio remedio) {
            dto = new RemedioCatalogoDTO();
            dto.setId(remedio.getId());
            dto.setNome(remedio.getNome());
            dto.setFarmaciaAlocado(remedio.getFarmacia().getNome());
            dto.setDataInicioTratamento(remedio.getDataInicioTratamento());
            dto.setPrincipiosAtivos(remedio.getGramaturas().stream().map(Gramatura::getPrincipioAtivo).collect(Collectors.toList()));
            dto.setTipoRemedio(remedio.getTipoRemedio().getTipo());

        }

        public RemedioCatalogoDTO build(){
            return dto;
        }

    }
}
