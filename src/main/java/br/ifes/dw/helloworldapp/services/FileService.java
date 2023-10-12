package br.ifes.dw.helloworldapp.services;

import br.ifes.dw.helloworldapp.models.ProdutoModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import java.io.*;
import java.util.List;
import java.util.Optional;

@Service
public class FileService<T extends ProdutoModel> implements ProdutoServiceInterface<T> {
    private static final String PRODUTOS_JSON_FILE = "src/main/java/br/ifes/dw/helloworldapp/files/produtos.json";
    private List<T> productList;
    private final Gson gson = new Gson();
    private long id = 1;

    public FileService() {loadProductsFromJson();}

    @Override
    public T save(T object) {
        object.setId(id);
        productList.add(object);
        saveProductsToJson();
        id++;
        return object;
    }

    @Override
    public void delete(int id) {
        productList.removeIf(product -> product.getId() == id);
        saveProductsToJson();
    }

    @Override
    public List<T> findAll() {return productList;}

    @Override
    public Optional<T> findById(int id) {return productList.stream().filter(product -> product.getId() == id).findFirst();}

    @Override
    public void update(int id, T object) {
        Optional<T> produtoOptional = findById(id);

        if (produtoOptional.isPresent()) {
            T produtoExistente = produtoOptional.get();
            produtoExistente.setNome(object.getNome());
            produtoExistente.setPreco(object.getPreco());
        }
    }

    private void loadProductsFromJson() {
        try (FileReader reader = new FileReader(PRODUTOS_JSON_FILE)) {
            this.productList = gson.fromJson(reader, new TypeToken<List<ProdutoModel>>() {}.getType());
        } catch (IOException e) {throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Desculpe, não foi possível encontrar os seus dados");}
    }

    private void saveProductsToJson() {
        try {FileWriter writer = new FileWriter(PRODUTOS_JSON_FILE);
            gson.toJson(productList, writer);
            writer.close();
        } catch (IOException e) {throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Produto não foi criado com sucesso");}
    }

}

