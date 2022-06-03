package com.generation.gameShop.controller;

import java.util.List;

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

import com.generation.gameShop.model.Produto;
import com.generation.gameShop.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public ResponseEntity <List<Produto>> getAll () {
		return ResponseEntity.ok().body(produtoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById (@PathVariable Long id) {
		
		return produtoRepository.findById(id)
			.map (resposta -> ResponseEntity.ok(resposta))
			.orElse (ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByName (@PathVariable String nome) {
	
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
		
	}
	
	@GetMapping("/menorPreco/{preco}")
	public ResponseEntity<List<Produto>> getByMenorPreco (@PathVariable Double preco) {
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findByPrecoLessThanOrderByPreco(preco));
	}
	
	@GetMapping("/maiorPreco/{preco}")
	public ResponseEntity<List<Produto>> getByMaiorPreco (@PathVariable Double preco) {
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findByPrecoGreaterThanOrderByPreco(preco));
	}
	
	
	@PostMapping
	public ResponseEntity<Produto> post (@Valid @RequestBody Produto produto) { 
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
	}
	

	//@RequestBody: Annotation indicating a method parameter should be bound to the body of the web request.
	@PutMapping
	public ResponseEntity<Produto> put (@Valid @RequestBody Produto produto) {
		if (produto.getId()==null || !produtoRepository.existsById(produto.getId())) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(produto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Produto> delete (@PathVariable Long id) {
		if (!produtoRepository.existsById(id) || id == null) {
			return ResponseEntity.notFound().build();
		}
		produtoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
}
