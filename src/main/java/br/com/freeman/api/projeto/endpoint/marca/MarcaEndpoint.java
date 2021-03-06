package br.com.freeman.api.projeto.endpoint.marca;

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

import br.com.freeman.api.projeto.dao.marca.MarcaDAO;
import br.com.freeman.api.projeto.domain.marca.Marca;

@RestController
@RequestMapping(MarcaEndpoint.API_BASE_URI)
public class MarcaEndpoint {

	public static final String API_BASE_URI = "api/v1/marca";

	private static final Integer LIMIT_MAX = 10;
	
	@Autowired
	MarcaDAO dao;

	@RequestMapping(method = RequestMethod.GET)
	public List<Marca> findAll(@RequestParam Map<String, String> params) throws Exception {
		
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
	
	@RequestMapping(value = "/{idMarca}", method = RequestMethod.GET)
	public Marca getMarcaById(@PathVariable final Long idMarca) {
		return dao.findById(idMarca);
	}

	@PostMapping
	public HttpStatus insert(@RequestBody Marca marca) {
		dao.insert(marca);
		return HttpStatus.CREATED;
	}
	
	@PutMapping("/{id}")
	public HttpStatus update(@PathVariable Long id, @RequestBody Marca marca) {
		marca.setIdMarca(id);
		dao.update(marca);
		return HttpStatus.OK;
	}
	
	@DeleteMapping("/{idMarca}")
	public HttpStatus delete(@PathVariable Long idMarca) {
		dao.delete(idMarca);
		return HttpStatus.OK;
	}

}
