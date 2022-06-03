package com.generation.gameShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.gameShop.model.Categoria;


public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
		
	public List<Categoria> findAllByGeneroContainingIgnoreCase (String genero);

}
