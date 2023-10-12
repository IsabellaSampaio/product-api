package br.ifes.dw.helloworldapp.services;

import br.ifes.dw.helloworldapp.models.ProdutoModel;
import br.ifes.dw.helloworldapp.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService<T extends ProdutoModel> implements ProdutoServiceInterface<T> {

    private final ProdutoRepository<T> genericRepository;
    public ProdutoService(ProdutoRepository<T> repository) {this.genericRepository = repository;}
    @Override @Transactional
    public T save(T object) {return genericRepository.save(object);}
    @Override @Transactional
    public void delete(int id) {genericRepository.deleteById((long) id);}
    @Override   
    public List<T> findAll() {return (List<T>) genericRepository.findAll();}
    @Override
    public Optional<T> findById(int id) {return (Optional<T>) genericRepository.findById((long) id);}
    @Override @Transactional
    public void update(int id, T object) {genericRepository.findById((long) id);}

}
