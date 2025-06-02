package br.com.coltran.farmacinhapp.services;

import br.com.coltran.farmacinhapp.config.constants.Business;
import br.com.coltran.farmacinhapp.controllers.web.dto.RemedioCatalogoDTO;
import br.com.coltran.farmacinhapp.domain.Gramatura;
import br.com.coltran.farmacinhapp.domain.Remedio;
import br.com.coltran.farmacinhapp.domain.valueobjects.RemedioIndexVO;
import br.com.coltran.farmacinhapp.repositories.RemedioRepository;
import br.com.coltran.farmacinhapp.security.domain.User;
import br.com.coltran.farmacinhapp.services.interfaces.RepositoryService;
import br.com.coltran.farmacinhapp.utils.Colecoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class RemedioService extends ServiceWorker implements RepositoryService<Remedio> {

    @Autowired
    private RemedioRepository remedioRepository;

    @Autowired
    private Colecoes.SET<Gramatura> colecoes;

    public Remedio findResourceById(long remedioId){
        return remedioRepository.findById(remedioId).orElse(null);
    }

    public void deleteResourceById(long id){
        try{
            remedioRepository.delete(id);
        } catch (Exception ex){
            ex.printStackTrace(System.err);
        }
    }

    public boolean isResourceOwner(long remedioId){
        User usuario = authService.usuarioLogado();
        return usuario.getFarmacias().stream()
                .flatMap(f -> f.getRemedios().stream())
                .anyMatch(r -> r.getId() == remedioId);
    }

    public Remedio save(Remedio remedio){
        timestamps(remedio);
        return remedioRepository.save(remedio);
    }

    public Remedio update(Remedio managedRemedio, Remedio formRemedio){
        updateTimestamp(managedRemedio);
        managedRemedio.setNome(formRemedio.getNome());
        managedRemedio.setDataInicioTratamento(formRemedio.getDataInicioTratamento());
        managedRemedio.setDoses(formRemedio.getDoses());
        managedRemedio.setConsumoDiario(formRemedio.getConsumoDiario());
        managedRemedio.setTipoRemedio(formRemedio.getTipoRemedio());
        return remedioRepository.save(managedRemedio);
    }

    public Page<Remedio> getRemediosByFarmacia(Long farmaciaId, Pageable pageable){
        return remedioRepository.findAllByFarmacia(farmaciaId, pageable);
    }

    public Page<Remedio> getRemediosByFarmaciaAndNome(Long farmaciaId, String nome, Pageable pageable){
        return remedioRepository.findByFarmaciaAndNome(farmaciaId, nome, pageable);
    }

    public Page<RemedioCatalogoDTO> getCatalogo(String nome, Pageable pageable){
        if(nome.toLowerCase().startsWith("pa:")){
            String principioAtivo = nome.substring(nome.indexOf(":")+1).trim();
            return getRemediosCatalogoByPrincipioAtivo(principioAtivo, pageable);
        }
        return getRemediosCatalogoByNome(nome, pageable);
    }

    private Page<RemedioCatalogoDTO> getRemediosCatalogoByNome(String nome, Pageable pageable){
        Page<Remedio> queryPage = remedioRepository.findByNome(nome, pageable);
        return buildFromRemedioPage(queryPage);
    }

    private Page<RemedioCatalogoDTO> getRemediosCatalogoByPrincipioAtivo(String principioAtivo, Pageable pageable){
        Page<Remedio> queryPage = remedioRepository.findRemedioByPrincipioAtivo(principioAtivo, pageable);
        return buildFromRemedioPage(queryPage);
    }

    private Page<RemedioCatalogoDTO> buildFromRemedioPage(Page<Remedio> page){
        return page.map(remedio -> { return new RemedioCatalogoDTO.Builder(remedio).build();});
    }

    public Set<Remedio> getAllRemedioByUser(){
        return remedioRepository.findAllRemedioByUser(authService.usuarioLogado().getId());
    }

    public Page<Remedio> getAllRemedioPagedByUser(Pageable pageable){
        return remedioRepository.findAllRemedioPageByUser(authService.usuarioLogado().getId(), pageable);
    }

    public boolean canSaveRemedio(){
        long remediosTotal = authService.usuarioLogado().getFarmacias()
                .stream()
                .mapToLong(f -> { return f.getRemedios().size(); })
                .sum();
        return remediosTotal < Business.MAX_FREE_REMEDIOS;
    }

    public Remedio addGramatura(Remedio remedio, Gramatura gramatura){
        remedio.setGramaturas(colecoes.addIfNull(remedio.getGramaturas(), gramatura));
        return remedioRepository.save(remedio);
    }

    public Set<RemedioIndexVO> getRemediosHaUmaSemanaDeAcabar(){
        return getRemediosInferioresNdoses(8);
    }

    public Set<RemedioIndexVO> getRemediosInferioresNdoses(int n){
        return authService.usuarioLogado()
                .getFarmacias().stream()
                .flatMap(f -> f.getRemedios().stream()
                        .map(r -> { return new RemedioIndexVO.Builder().buildFromModel(r);})
                        .filter(vo -> vo.getDosesRestantes() < n && vo.getDosesRestantes() > -1))
                .collect(Collectors.toSet());
    }

}
