package br.ifes.dw.helloworldapp.repositories;

import br.ifes.dw.helloworldapp.models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository<T> extends JpaRepository<ProdutoModel, Long> { }
