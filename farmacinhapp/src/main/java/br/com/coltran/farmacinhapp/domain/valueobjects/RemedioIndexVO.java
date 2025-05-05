package br.com.coltran.farmacinhapp.domain.valueobjects;

import br.com.coltran.farmacinhapp.domain.Remedio;
import br.com.coltran.farmacinhapp.utils.Datas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class RemedioIndexVO implements Serializable {

    private Long id;
    private String nome;
    private Long dosesRestantes;
    private String nomeFarmacia;
    private Long idFarmacia;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private ZonedDateTime dtInicioTratamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getDosesRestantes() {
        return dosesRestantes;
    }

    public void setDosesRestantes(Long dosesRestantes) {
        this.dosesRestantes = dosesRestantes;
    }

    public String getNomeFarmacia() { return nomeFarmacia; }

    public void setNomeFarmacia(String nomeFarmacia) { this.nomeFarmacia = nomeFarmacia;}

    public Long getIdFarmacia() { return idFarmacia; }

    public void setIdFarmacia(Long idFarmacia) { this.idFarmacia = idFarmacia; }

    public ZonedDateTime getDtInicioTratamento() {
        return dtInicioTratamento;
    }

    public void setDtInicioTratamento(ZonedDateTime dtInicioTratamento) {
        this.dtInicioTratamento = dtInicioTratamento;
    }

    public static class Builder {

        private RemedioIndexVO vo;



        public RemedioIndexVO buildFromModel(Remedio remedio){
            this.vo = new RemedioIndexVO();

            vo.setId(remedio.getId());
            vo.setNome(remedio.getNome());
            vo.setDtInicioTratamento(remedio.getDataInicioTratamento());

            ZonedDateTime now = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));

            if(now.isAfter(vo.getDtInicioTratamento())){
                Duration duration = Duration.between(remedio.getDataInicioTratamento().truncatedTo(ChronoUnit.DAYS), now.truncatedTo(ChronoUnit.DAYS));
                vo.setDosesRestantes(remedio.getDoses() - Math.abs(duration.toDays()));
            }

            vo.setIdFarmacia(remedio.getFarmacia().getId());
            vo.setNomeFarmacia(remedio.getFarmacia().getNome());

            return vo;
        }


    }

}
