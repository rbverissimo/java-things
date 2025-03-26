package br.com.coltran.farmacinhapp.services;

import br.com.coltran.farmacinhapp.domain.Paciente;
import br.com.coltran.farmacinhapp.domain.interfaces.TableEntity;
import br.com.coltran.farmacinhapp.repositories.PacienteRepository;
import br.com.coltran.farmacinhapp.services.interfaces.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@Transactional
public class PacienteService extends ServiceWorker implements RepositoryService<Paciente> {


    @Autowired
    PacienteRepository pacienteRepository;

    @Override
    public Paciente findResourceById(long pacienteId) {
        return pacienteRepository.findById(pacienteId).orElse(null);
    }

    @Override
    public boolean isResourceOwner(long pacienteId) {
        return usuario().getFarmacias().stream()
                .anyMatch(f -> f.getPaciente().getId() == pacienteId);
    }

    public Paciente save(Paciente paciente){
        timestamps(paciente);
        return pacienteRepository.save(paciente);
    }

    public Paciente update(Paciente managed, Paciente formUpdated){
        updateTimestamp(managed);
        managed.setNome(formUpdated.getNome());
        managed.setSobrenome(formUpdated.getSobrenome());
        managed.setDataNascimento(formUpdated.getDataNascimento());
        managed.setDescTratamento(formUpdated.getDescTratamento());
        return pacienteRepository.save(managed);
    }
}
