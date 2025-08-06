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

    public Ferramenta buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ferramenta com id:" + id + " não encontrada"));
    }

    public Ferramenta salvar(Ferramenta ferramenta) {
        return repository.save(ferramenta);
    }

    public Ferramenta atualizar(Long id, Ferramenta ferramentaNova) {
            var ferramentaAtual = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Ferramenta com id:" + id + " não encontrada"));

            ferramentaAtual.setNome(ferramentaNova.getNome());
            ferramentaAtual.setTipo(ferramentaAtual.getTipo());
            ferramentaAtual.setClassificacao(ferramentaAtual.getClassificacao());
            ferramentaAtual.setTamanho(ferramentaAtual.getTamanho());
            ferramentaAtual.setPreco(ferramentaAtual.getPreco());
            ferramentaAtual.setQuantidade(ferramentaAtual.getQuantidade());

            return repository.save(ferramentaAtual);
    }

    public String deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "Ferramente deletada com sucesso!";
        }
        return "Ferramenta com id:" + id + " não encontrada";
    }
}
