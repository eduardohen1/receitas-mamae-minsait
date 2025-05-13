package br.com.ehmf.ReceitasMamae.controller;

import br.com.ehmf.ReceitasMamae.model.Receita;
import br.com.ehmf.ReceitasMamae.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    private final ReceitaService receitaService;

    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @PostMapping
    public ResponseEntity<Receita> salvarReceita(@RequestBody Receita receita) {
        Receita receitaSalva = receitaService.salvarReceita(receita);
        return ResponseEntity.ok(receitaSalva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receita> buscarReceitaPorId(@PathVariable Long id) {
        Optional<Receita> receita = receitaService.buscarReceitaPorId(id);
        return receita.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}