package br.com.coltran.farmacinhapp.services;

import br.com.coltran.farmacinhapp.domain.Paciente;
import br.com.coltran.farmacinhapp.repositories.PacienteRepository;
import br.com.coltran.farmacinhapp.services.interfaces.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PacienteService extends ServiceWorker<Paciente> implements RepositoryService {


    @Autowired
    PacienteRepository pacienteRepository;

    @Override
    public Paciente findResourceById(int pacienteId) {
        return pacienteRepository.findById((long) pacienteId).orElse(null);
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
}
