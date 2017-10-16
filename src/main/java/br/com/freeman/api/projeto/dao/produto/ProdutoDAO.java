package br.com.freeman.api.projeto.dao.produto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.freeman.api.projeto.domain.produto.Produto;
import br.com.freeman.api.projeto.mapper.produto.ProdutoMapper;

@Repository
public class ProdutoDAO {

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	JdbcTemplate jdbcTemplate;

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public Produto findById(Long idProduto) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id_produto", idProduto);

		String sql = "SELECT ID_PRODUTO, DESCRICAO_PRODUTO, CODIGO_BARRAS FROM PRODUTO WHERE ID_PRODUTO = :id_produto";

		Produto produto = namedParameterJdbcTemplate.queryForObject(sql, params, new ProdutoMapper());

		return produto;
	}

	public Produto insert(Produto produto) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("descricao_produto", produto.getDescricaoProduto());
		params.put("codigo_barras", produto.getCodigoBarras());

		String sql = "INSERT INTO PRODUTO (DESCRICAO_PRODUTO, CODIGO_BARRAS) VALUES (:descricao_produto, :codigo_barras)";
		namedParameterJdbcTemplate.update(sql, params);

		return produto;
	}

	public Produto update(Produto produto) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id_produto", produto.getIdProduto());
		params.put("descricao_produto", produto.getDescricaoProduto());
		params.put("codigo_barras", produto.getCodigoBarras());

		String sql = "UPDATE PRODUTO SET ID_PRODUTO = :id_produto, DESCRICAO_PRODUTO = :descricao_produto, CODIGO_BARRAS = :codigo_barras WHERE ID_PRODUTO = :id_produto";
		namedParameterJdbcTemplate.update(sql, params);

		return produto;
	}

	public void delete(Long idProduto) {

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("id_produto", idProduto);

		String sql = "DELETE FROM PRODUTO WHERE ID_PRODUTO = :id_produto";

		namedParameterJdbcTemplate.update(sql, params);
	}

	public List<Produto> findAll(Integer offSet, Integer limit) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("offSet", offSet);
		params.put("limit", limit);

		String sql = "SELECT ID_PRODUTO, DESCRICAO_PRODUTO, CODIGO_BARRAS FROM PRODUTO LIMIT :offSet, :limit";

		List<Produto> produtos = namedParameterJdbcTemplate.query(sql, params, new ProdutoMapper());
		return produtos;
	}

}
