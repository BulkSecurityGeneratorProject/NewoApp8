package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.EntradaMiembros;
import io.github.jhipster.application.repository.EntradaMiembrosRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link io.github.jhipster.application.domain.EntradaMiembros}.
 */
@RestController
@RequestMapping("/api")
public class EntradaMiembrosResource {

    private final Logger log = LoggerFactory.getLogger(EntradaMiembrosResource.class);

    private static final String ENTITY_NAME = "entradaMiembros";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EntradaMiembrosRepository entradaMiembrosRepository;

    public EntradaMiembrosResource(EntradaMiembrosRepository entradaMiembrosRepository) {
        this.entradaMiembrosRepository = entradaMiembrosRepository;
    }

    /**
     * {@code POST  /entrada-miembros} : Create a new entradaMiembros.
     *
     * @param entradaMiembros the entradaMiembros to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entradaMiembros, or with status {@code 400 (Bad Request)} if the entradaMiembros has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/entrada-miembros")
    public ResponseEntity<EntradaMiembros> createEntradaMiembros(@RequestBody EntradaMiembros entradaMiembros) throws URISyntaxException {
        log.debug("REST request to save EntradaMiembros : {}", entradaMiembros);
        if (entradaMiembros.getId() != null) {
            throw new BadRequestAlertException("A new entradaMiembros cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EntradaMiembros result = entradaMiembrosRepository.save(entradaMiembros);
        return ResponseEntity.created(new URI("/api/entrada-miembros/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /entrada-miembros} : Updates an existing entradaMiembros.
     *
     * @param entradaMiembros the entradaMiembros to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entradaMiembros,
     * or with status {@code 400 (Bad Request)} if the entradaMiembros is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entradaMiembros couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/entrada-miembros")
    public ResponseEntity<EntradaMiembros> updateEntradaMiembros(@RequestBody EntradaMiembros entradaMiembros) throws URISyntaxException {
        log.debug("REST request to update EntradaMiembros : {}", entradaMiembros);
        if (entradaMiembros.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EntradaMiembros result = entradaMiembrosRepository.save(entradaMiembros);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entradaMiembros.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /entrada-miembros} : get all the entradaMiembros.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entradaMiembros in body.
     */
    @GetMapping("/entrada-miembros")
    public List<EntradaMiembros> getAllEntradaMiembros() {
        log.debug("REST request to get all EntradaMiembros");
        return entradaMiembrosRepository.findAll();
    }

    /**
     * {@code GET  /entrada-miembros/:id} : get the "id" entradaMiembros.
     *
     * @param id the id of the entradaMiembros to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entradaMiembros, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/entrada-miembros/{id}")
    public ResponseEntity<EntradaMiembros> getEntradaMiembros(@PathVariable Long id) {
        log.debug("REST request to get EntradaMiembros : {}", id);
        Optional<EntradaMiembros> entradaMiembros = entradaMiembrosRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(entradaMiembros);
    }

    /**
     * {@code DELETE  /entrada-miembros/:id} : delete the "id" entradaMiembros.
     *
     * @param id the id of the entradaMiembros to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/entrada-miembros/{id}")
    public ResponseEntity<Void> deleteEntradaMiembros(@PathVariable Long id) {
        log.debug("REST request to delete EntradaMiembros : {}", id);
        entradaMiembrosRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}