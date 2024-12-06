package pe.edu.vallegrande.vg_ms_casas.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pe.edu.vallegrande.vg_ms_casas.model.Casa;
import reactor.core.publisher.Flux;

public interface CasaRepository extends ReactiveCrudRepository<Casa, Integer> {
    Flux<Casa> findByEstado(String estado);
}
