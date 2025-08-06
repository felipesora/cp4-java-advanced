package br.com.fiap.cp4_java_advanced.controller;

import br.com.fiap.cp4_java_advanced.modal.Ferramenta;
import br.com.fiap.cp4_java_advanced.service.FerramentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/ferramentas")
public class FerramentaController {

    @Autowired
    private FerramentaService service;

    @GetMapping
    public ResponseEntity<List<Ferramenta>> listarTodos() {
        var ferramentas = service.listarTodos();
        return ResponseEntity.ok(ferramentas);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id) {
        var ferramenta = service.buscarPorId(id);
        return ResponseEntity.ok(ferramenta);
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody Ferramenta dto, UriComponentsBuilder uriBuilder) {
        var ferramenta = service.salvar(dto);

        var uri = uriBuilder.path("/ferramentas/{id}").buildAndExpand(ferramenta.getId()).toUri();

        return ResponseEntity.created(uri).body(ferramenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody Ferramenta ferramenta) {
        var ferramentaAtualizada = service.atualizar(id, ferramenta);

        return ResponseEntity.ok(ferramentaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
