package br.com.freeman.api.projeto.endpoint.marca;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.freeman.api.projeto.domain.marca.Marca;

@RestController
@RequestMapping(MarcaEndpoint.API_BASE_URI)
public class MarcaEndpoint {

	public static final String API_BASE_URI = "api/v1/marca";

	@RequestMapping(value = "/{idMarca}", method = RequestMethod.GET)
	public Marca getMarcaById(@PathVariable final Long idMarca) {

		Marca marca = new Marca();
		marca.setDsMarca("Marca");
		return marca;
	}

}
