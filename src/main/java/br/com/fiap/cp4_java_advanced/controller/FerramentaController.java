package br.com.fiap.cp4_java_advanced.controller;

import br.com.fiap.cp4_java_advanced.dto.FerramentaPatchDTO;
import br.com.fiap.cp4_java_advanced.dto.FerramentaRequestDTO;
import br.com.fiap.cp4_java_advanced.dto.FerramentaResponseDTO;
import br.com.fiap.cp4_java_advanced.mapper.FerramentaMapper;
import br.com.fiap.cp4_java_advanced.modal.Ferramenta;
import br.com.fiap.cp4_java_advanced.service.FerramentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ferramentas")
public class FerramentaController {

    @Autowired
    private FerramentaService service;

    @GetMapping
    public ResponseEntity<List<FerramentaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FerramentaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<FerramentaResponseDTO> salvar(@RequestBody @Valid FerramentaRequestDTO dto, UriComponentsBuilder uriBuilder) {
        var ferramenta = service.salvar(FerramentaMapper.toEntity(dto));

        var uri = uriBuilder.path("/ferramentas/{id}").buildAndExpand(ferramenta.getId()).toUri();

        return ResponseEntity.created(uri).body(ferramenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FerramentaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid FerramentaRequestDTO dto) {
        var ferramentaAtualizada = service.atualizar(id, FerramentaMapper.toEntity(dto));
        return ResponseEntity.ok(ferramentaAtualizada);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FerramentaResponseDTO> atualizarParcial(@PathVariable Long id, @RequestBody @Valid FerramentaPatchDTO dto) {
        var ferramentaAtualizada = FerramentaMapper.toDTO(service.atualizarParcial(id, dto));

        return ResponseEntity.ok(ferramentaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
