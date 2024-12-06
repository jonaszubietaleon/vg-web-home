package pe.edu.vallegrande.vg_ms_casas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.vg_ms_casas.model.Casa;
import pe.edu.vallegrande.vg_ms_casas.repository.CasaRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class CasaService {

    @Autowired
    private CasaRepository casaRepository;

    public Flux<Casa> findAll() {
        return casaRepository.findAll();
    }

    public Mono<Casa> findById(Integer id) {
        return casaRepository.findById(id);
    }

    public Mono<Casa> save(Casa casa) {
        return casaRepository.save(casa);
    }

    public Mono<Casa> update(Integer id, Casa casa) {
        return casaRepository.findById(id)
                .flatMap(existingCasa -> {
                    existingCasa.setNombre(casa.getNombre());
                    existingCasa.setDireccion(casa.getDireccion());
                    // Nota: No se permite modificar el estado en una actualización regular
                    return casaRepository.save(existingCasa);
                });
    }

    public Mono<Void> delete(Integer id) {
        return casaRepository.findById(id)
                .flatMap(existingCasa -> {
                    existingCasa.setEstado("I"); // Cambiar el estado a inactivo
                    return casaRepository.save(existingCasa);
                }).then();
    }

    public Mono<Casa> restore(Integer id) {
        return casaRepository.findById(id)
                .flatMap(casa -> {
                    casa.setEstado("A"); // Cambiar el estado a activo
                    return casaRepository.save(casa);
                });
    }

    public Flux<Casa> findActivos() {
        return casaRepository.findByEstado("A");
    }

    public Flux<Casa> findInactivos() {
        return casaRepository.findByEstado("I");
    }
}
