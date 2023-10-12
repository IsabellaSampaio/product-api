package br.ifes.dw.helloworldapp.controllers;

import br.ifes.dw.helloworldapp.codes.StatusCodes;
import br.ifes.dw.helloworldapp.dtos.ProdutoInputDTO;
import br.ifes.dw.helloworldapp.models.ProdutoModel;
import br.ifes.dw.helloworldapp.services.FileService;
import jakarta.validation.Valid;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto/file")
public class ProdutoFileController {

    @Autowired
    private FileService<ProdutoModel> fileService;

    @PostMapping
    public ResponseEntity<Object> createFileProduct(@RequestBody @Valid ProdutoInputDTO produtoInputDTO){
        var produtoModel = new ProdutoModel();
        BeanUtils.copyProperties(produtoInputDTO, produtoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(fileService.save(produtoModel));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoModel>> getAllFileProducts(){return ResponseEntity.status(HttpStatus.OK).body(fileService.findAll());}

    @GetMapping("/{id}")
    public ResponseEntity<Object> getFileProductById(@PathVariable int id){
        Optional<ProdutoModel> produtoModelOptional = (fileService.findById(id));
        return produtoModelOptional.<ResponseEntity<Object>>map(produtoModel ->
                ResponseEntity.status(HttpStatus.OK).body(produtoModel)).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.PRODUCT_NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFileProductById(@PathVariable int id){
        Optional<ProdutoModel> produtoModelOptional = fileService.findById(id);
        if(produtoModelOptional.isEmpty()){return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.PRODUCT_NOT_FOUND);}
        fileService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(StatusCodes.PRODUCT_REMOVED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateFileProductById(@PathVariable int id, @RequestBody @Valid ProdutoInputDTO produtoInputDTO){
        Optional<ProdutoModel> produtoModelOptional = fileService.findById(id);
        if(produtoModelOptional.isEmpty()){return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.PRODUCT_NOT_FOUND);}

        var produtoModel = produtoModelOptional.get();
        BeanUtils.copyProperties(produtoInputDTO, produtoModel);

        fileService.update(id, produtoModel);

        return ResponseEntity.status(HttpStatus.OK).body(fileService.findById(id));
    }

}
