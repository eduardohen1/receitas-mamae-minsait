package br.com.ehmf.ReceitasMamae.repository;

import br.com.ehmf.ReceitasMamae.model.ModoPreparo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModoPreparoRepository extends JpaRepository<ModoPreparo, Long> {
}