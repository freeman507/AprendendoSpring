package br.com.freeman.api.projeto.mapper.produto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.freeman.api.projeto.domain.produto.Produto;

public class ProdutoMapper implements RowMapper<Produto> {

	@Override
	public Produto mapRow(ResultSet rs, int arg1) throws SQLException {
		Produto produto = new Produto();
		produto.setIdProduto(rs.getLong("id_produto"));
		produto.setDescricaoProduto(rs.getString("descricao_produto"));
		produto.setCodigoBarras(rs.getString("codigo_barras"));
		return produto;
	}

}
