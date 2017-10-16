package br.com.freeman.api.projeto.endpoint.produto;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.freeman.api.projeto.dao.produto.ProdutoDAO;
import br.com.freeman.api.projeto.domain.produto.Produto;

@RestController
@RequestMapping(ProdutoEndpoint.API_BASE_URI)
public class ProdutoEndpoint {

	public static final String API_BASE_URI = "api/v1/produto";

	private static final Integer LIMIT_MAX = 10;
	
	@Autowired
	private ProdutoDAO dao;

	@RequestMapping(method = RequestMethod.GET)
	public List<Produto> findAll(@RequestParam Map<String, String> params) throws Exception {
		
		String offSet = params.get("offset");
		String limit = params.get("limit");
		
		if(offSet == null) {
			throw new Exception("Offset errado.");
		}
		
		Integer limitAux = LIMIT_MAX;
		
		if(limit != null) {
			limitAux = Integer.valueOf(limit);
			
			if(limitAux > LIMIT_MAX) {
				limitAux = LIMIT_MAX;
			}
			
		}
		
		return dao.findAll(Integer.valueOf(offSet), limitAux);
	}
	
	@RequestMapping(value = "/{idProduto}", method = RequestMethod.GET)
	public Produto getProdutoById(@PathVariable final Long idProduto) {
		return dao.findById(idProduto);
	}

	@PostMapping
	public HttpStatus insert(@RequestBody Produto produto) {
		dao.insert(produto);
		return HttpStatus.CREATED;
	}
	
	@PutMapping("/{id}")
	public HttpStatus update(@PathVariable Long id, @RequestBody Produto produto) {
		produto.setIdProduto(id);;
		dao.update(produto);
		return HttpStatus.OK;
	}
	
	@DeleteMapping("/{idProduto}")
	public HttpStatus delete(@PathVariable Long idProduto) {
		dao.delete(idProduto);
		return HttpStatus.OK;
	}
}
