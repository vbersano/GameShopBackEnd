package com.generation.gameShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.gameShop.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	//Lista de produtos por (input "id")
	public List<Produto> findAllById (Long id);
	
	//Lista de produtos por (input "nome")
	public List<Produto> findAllByNomeContainingIgnoreCase (@Param("nome") String nome);
	
	//Lista de produtos cujo preco > (input "preco")
	public List<Produto> findByPrecoGreaterThanOrderByPreco (@Param("preco") Double preco);
	
	//Lista de produtos cujo preco < (input "preco")
	public List<Produto> findByPrecoLessThanOrderByPreco (@Param("preco")Double preco);
	
	
}
