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

import com.generation.gameShop.model.Categoria;
import com.generation.gameShop.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> getAll () {
		return ResponseEntity.ok().body(categoriaRepository.findAll());
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity <Categoria> getById (@PathVariable Long id) {
		return categoriaRepository.findById(id)
				.map (resposta -> ResponseEntity.ok(resposta))
				.orElse (ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity <List<Categoria>> getByName (@PathVariable String nome) {
		return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.findAllByGeneroContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<Categoria> post (@Valid @RequestBody Categoria genero) { 
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(genero));
	}
	
	@PutMapping
	public ResponseEntity<Categoria> put (@Valid @RequestBody Categoria categoria) {
		
		if (!categoriaRepository.existsById(categoria.getId()) || categoria.getId() == null) {
			return ResponseEntity.notFound().build();
		}
		
		//wil update any attribute (but id) specified by json.
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(categoriaRepository.save(categoria));
	}
	
	@DeleteMapping ("/{id}")
	public ResponseEntity<Categoria> delete (@PathVariable Long id) {
		
		if (!categoriaRepository.existsById(id) || id == null) {
			return ResponseEntity.notFound().build();
		}
		categoriaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
	

}
