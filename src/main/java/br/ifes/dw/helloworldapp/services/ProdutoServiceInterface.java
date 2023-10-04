package br.ifes.dw.helloworldapp.services;

import java.util.List;
import java.util.Optional;

interface ProdutoServiceInterface<T> {
    public T save(T object);
    public void delete(int id);
    public List<T> findAll();
    public Optional<T> findById(int id);
    public void update(int id, T object);

}
