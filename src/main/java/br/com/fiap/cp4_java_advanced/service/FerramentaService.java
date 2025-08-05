package br.com.fiap.cp4_java_advanced.service;

import br.com.fiap.cp4_java_advanced.modal.Ferramenta;
import br.com.fiap.cp4_java_advanced.repository.FerramentaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FerramentaService {

    @Autowired
    private FerramentaRepository repository;

    public List<Ferramenta> listarTodos() {
        return repository.findAll();
    }

    public Ferramenta obterPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ferramenta com id:" + id + " não encontrada"));
    }

    public Ferramenta salvar(Ferramenta ferramenta) {
        return repository.save(ferramenta);
    }

    public Ferramenta atualizar(Long id, Ferramenta ferramenta) {
        if (repository.existsById(id)) {
            ferramenta.setId(id);
            return repository.save(ferramenta);
        }
        throw new RuntimeException("Ferramenta com id:" + id + " não encontrada");
    }

    public String deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "Ferramente deletada com sucesso!";
        }
        return "Ferramenta com id:" + id + " não encontrada";
    }
}
