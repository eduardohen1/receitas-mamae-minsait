package br.com.ehmf.ReceitasMamae.repository;

import br.com.ehmf.ReceitasMamae.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
}