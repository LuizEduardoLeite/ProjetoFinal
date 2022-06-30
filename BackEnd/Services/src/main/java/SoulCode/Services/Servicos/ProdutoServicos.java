package SoulCode.Services.Servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoulCode.Services.Models.Produto;
import SoulCode.Services.Repository.ProdutoRepository;

@Service
public class ProdutoServicos {

	@Autowired
	ProdutoRepository produtoRepository;
	
	public List<Produto> mostrarProdutos(){
		return produtoRepository.findAll();
	}
	public Produto mostrarProduto(Integer IdProduto) {
		Optional<Produto> produto = produtoRepository.findById(IdProduto);
		return produto.orElseThrow();
	}
	public Produto mostrarNomeProduto(String nome) {
		Optional<Produto> produto = produtoRepository.findByNome(nome);
		return produto.orElseThrow();
	}
	public Produto inserirProduto(Produto produto) {
		produto.setIdProduto(null);
		return produtoRepository.save(produto);
	}
	public Produto editarProduto(Produto produto) {
		mostrarProduto(produto.getIdProduto());
		return produtoRepository.save(produto);
	}
	public void excluirProduto(Integer IdProduto) {
		mostrarProduto(IdProduto);
		produtoRepository.deleteById(IdProduto);
	}
	
}
