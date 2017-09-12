package br.com.freeman.api.projeto.endpoint.marca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.freeman.api.projeto.dao.marca.MarcaDAO;
import br.com.freeman.api.projeto.domain.marca.Marca;

@RestController
@RequestMapping(MarcaEndpoint.API_BASE_URI)
public class MarcaEndpoint {

	public static final String API_BASE_URI = "api/v1/marca";

	@Autowired
	MarcaDAO dao;

	@RequestMapping(value = "/{idMarca}", method = RequestMethod.GET)
	public Marca getMarcaById(@PathVariable final Long idMarca) {
		return dao.findById(idMarca);
	}

	@PostMapping
	public HttpStatus insert(@RequestBody Marca marca) {
		dao.insert(marca);
		return HttpStatus.CREATED;
	}

}
