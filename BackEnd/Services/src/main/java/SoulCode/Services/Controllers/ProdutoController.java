package SoulCode.Services.Controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import SoulCode.Services.Models.Produto;
import SoulCode.Services.Servicos.ProdutoServicos;

@CrossOrigin
@RestController
@RequestMapping("servico")
public class ProdutoController {

	@Autowired
	ProdutoServicos produtoservicos;
	
	@GetMapping("/medicamento")
	public List<Produto>mostrarProdutos(){
		List<Produto> produto = produtoservicos.mostrarProdutos();
		return produto;
	}
	
	@GetMapping("/medicamento/{IdProduto}")
	public ResponseEntity<Produto>mostrarProduto(@PathVariable Integer IdProduto){
		Produto produto = produtoservicos.mostrarProduto(IdProduto);
				return ResponseEntity.ok().body(produto);
	}
	@GetMapping("/medicamentonome/{nome}")
	public ResponseEntity<Produto>mostrarNomeProduto(@PathVariable String nome){
		Produto produto = produtoservicos.mostrarNomeProduto(nome);
		return ResponseEntity.ok().body(produto);
	}
	
	@PostMapping("/medicamento")
	public ResponseEntity<Produto>inserirProduto(@RequestBody Produto produto){
		produto = produtoservicos.inserirProduto(produto);
		URI uriProduto = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(produto.getIdProduto()).toUri();
		return ResponseEntity.created(uriProduto).body(produto);
	}
	@PutMapping("/medicamento/{IdProduto}")
	public ResponseEntity<Produto>editarProduto(@PathVariable Integer IdProduto,@RequestBody Produto produto){
		produto.setIdProduto(IdProduto);
		produto = produtoservicos.editarProduto(produto);
		return ResponseEntity.noContent().build();
	}
	@DeleteMapping("/medicamento/{IdProduto}")
	public ResponseEntity<Void>excluirProduto(@PathVariable Integer IdProduto){
		produtoservicos.excluirProduto(IdProduto);
		return ResponseEntity.noContent().build();
	}
}
