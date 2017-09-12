package br.com.freeman.api.projeto.dao.marca;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.freeman.api.projeto.domain.marca.Marca;
import br.com.freeman.api.projeto.mapper.marca.MarcaMapper;

@Repository
public class MarcaDAO {

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	JdbcTemplate jdbcTemplate;

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public Marca findById(Long idMarca) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id_marca", idMarca);

		String sql = "SELECT ID_MARCA, DS_MARCA FROM MARCA WHERE ID_MARCA = :id_marca";

		Marca marca = namedParameterJdbcTemplate.queryForObject(sql, params, new MarcaMapper());

		return marca;
	}

	public Marca insert(Marca marca) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ds_marca", marca.getDsMarca());

		String sql = "INSERT INTO Marca (DS_MARCA) VALUES (:ds_marca)";
		namedParameterJdbcTemplate.update(sql, params);

		return marca;
	}
}
