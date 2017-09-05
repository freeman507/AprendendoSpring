package br.com.freeman.api.projeto.mapper.marca;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.freeman.api.projeto.domain.marca.Marca;

public class MarcaMapper implements RowMapper<Marca> {

	@Override
	public Marca mapRow(ResultSet rs, int rowNum) throws SQLException {
		Marca marca = new Marca();
		marca.setIdMarca(rs.getLong("id_marca"));
		marca.setDsMarca(rs.getString("ds_marca"));
		return marca;
	}

}
