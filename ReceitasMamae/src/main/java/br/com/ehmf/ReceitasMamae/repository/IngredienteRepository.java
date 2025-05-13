package br.com.ehmf.ReceitasMamae.repository;

import br.com.ehmf.ReceitasMamae.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
}