package br.com.ehmf.ReceitasMamae.service;

import br.com.ehmf.ReceitasMamae.model.Receita;
import br.com.ehmf.ReceitasMamae.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceitaService {

    private final ReceitaRepository receitaRepository;

    public ReceitaService(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    public Receita salvarReceita(Receita receita) {
        return receitaRepository.save(receita);
    }

    public Optional<Receita> buscarReceitaPorId(Long id) {
        return receitaRepository.findById(id);
    }

    //função para buscar todas as receitas
    public List<Receita> buscarTodasReceitas() {
        return receitaRepository.findAll();
    }
}