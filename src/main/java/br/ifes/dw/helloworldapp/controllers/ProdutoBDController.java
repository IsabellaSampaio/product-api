package br.ifes.dw.helloworldapp.controllers;

import br.ifes.dw.helloworldapp.codes.StatusCodes;
import br.ifes.dw.helloworldapp.dtos.ProdutoInputDTO;
import br.ifes.dw.helloworldapp.models.ProdutoModel;
import br.ifes.dw.helloworldapp.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto/bd")
public class ProdutoBDController {
    @Autowired
    private ProdutoService<ProdutoModel> produtoService;

    @PostMapping
    public ResponseEntity<Object> createProduto(@RequestBody @Valid ProdutoInputDTO produtoInputDTO){
        var produtoModel = new ProdutoModel();
        BeanUtils.copyProperties(produtoInputDTO, produtoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(produtoModel));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoModel>> getAllProducts(){return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll());}

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable int id){
        Optional<ProdutoModel> produtoModelOptional = (produtoService.findById(id));
        return produtoModelOptional.<ResponseEntity<Object>>map(produtoModel ->
                ResponseEntity.status(HttpStatus.OK).body(produtoModel)).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.PRODUCT_NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProductById(@PathVariable int id){
        Optional<ProdutoModel> produtoModelOptional = produtoService.findById(id);
        if(produtoModelOptional.isEmpty()){return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.PRODUCT_NOT_FOUND);}
        produtoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(StatusCodes.PRODUCT_REMOVED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProductById(@PathVariable int id, @RequestBody @Valid ProdutoInputDTO produtoInputDTO){
        Optional<ProdutoModel> produtoModelOptional = produtoService.findById(id);
        if(produtoModelOptional.isEmpty()){return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.PRODUCT_NOT_FOUND);}

        var produtoModel = produtoModelOptional.get();
        BeanUtils.copyProperties(produtoInputDTO, produtoModel);
        produtoModel.setId(produtoModelOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(produtoService.save(produtoModel));
    }

}
