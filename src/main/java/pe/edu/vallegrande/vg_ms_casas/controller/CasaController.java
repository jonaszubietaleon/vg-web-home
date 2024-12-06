package pe.edu.vallegrande.vg_ms_casas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.vg_ms_casas.model.Casa;
import pe.edu.vallegrande.vg_ms_casas.service.CasaService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/casas")
public class CasaController {

    @Autowired
    private CasaService casaService;

    @GetMapping
    public Flux<Casa> getAll() {
        return casaService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Casa> getById(@PathVariable Integer id) {
        return casaService.findById(id);
    }

    @PostMapping
    public Mono<Casa> create(@RequestBody Casa casa) {
        return casaService.save(casa);
    }

    @PutMapping("/{id}")
    public Mono<Casa> update(@PathVariable Integer id, @RequestBody Casa casa) {
        return casaService.update(id, casa);
    }

    @PutMapping("/{id}/inactivar")
    public Mono<Void> delete(@PathVariable Integer id) {
        return casaService.delete(id);
    }

    @PutMapping("/{id}/restore")
    public Mono<Casa> restore(@PathVariable Integer id) {
        return casaService.restore(id);
    }

    @GetMapping("/ListaActivos")
    public Flux<Casa> listActivos() {
        return casaService.findActivos();
    }

    @GetMapping("/ListaInactivos")
    public Flux<Casa> listInactivos() {
        return casaService.findInactivos();
    }

}
