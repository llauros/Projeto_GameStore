package com.games.store.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.games.store.model.Categoria;
import com.games.store.model.Produto;
import com.games.store.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
@CrossOrigin( origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> findAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id_produto}")
	public ResponseEntity<Produto> findById(@PathVariable Long id_produto ){
		return ResponseEntity.status(HttpStatus.OK).body(repository.findById(id_produto).get());
	}
	
	@GetMapping("/nome/{nome_produto}")
	public ResponseEntity<List<Produto>> findByNome (@PathVariable String nome_produto ){
		return ResponseEntity.status(HttpStatus.OK).body(repository.findAllByNomeContainingIgnoreCase(nome_produto));
	}

	@PostMapping("/criar")
	public ResponseEntity<Produto> saveProduto(@Valid @RequestBody Produto produto){
		return ResponseEntity.status(200).body(repository.save(produto));
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Produto> updateCategoria (@Valid @RequestBody Produto produto){
		return ResponseEntity.status(200).body(repository.save(produto));
	}
	
	@DeleteMapping("/deletar/{id_produto}")
	public ResponseEntity<String> deleteById (@PathVariable Long id_produto ) {

		Optional<Produto> produtoExistente = repository.findById(id_produto);
		
		if (produtoExistente.isPresent()) {
			repository.deleteById(id_produto);
			return ResponseEntity.status(200).body("Produto Deletado");
		} else {
			return ResponseEntity.status(200).body("Produto não encontrado");
		}
	}	
}
