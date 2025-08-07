package br.com.fiap.cp4_java_advanced.controller;

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
        var ferramentas = service.listarTodos()
                .stream()
                .map(FerramentaMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ferramentas);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id) {
        var ferramenta = FerramentaMapper.toDTO(service.buscarPorId(id));
        return ResponseEntity.ok(ferramenta);
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody @Valid FerramentaRequestDTO dto, UriComponentsBuilder uriBuilder) {
        var ferramenta = FerramentaMapper.toDTO(service.salvar(FerramentaMapper.toEntity(dto)));

        var uri = uriBuilder.path("/ferramentas/{id}").buildAndExpand(ferramenta.getId()).toUri();

        return ResponseEntity.created(uri).body(ferramenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid FerramentaRequestDTO dto) {
        var ferramentaAtualizada = FerramentaMapper.toDTO(service.atualizar(id, FerramentaMapper.toEntity(dto)));

        return ResponseEntity.ok(ferramentaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
