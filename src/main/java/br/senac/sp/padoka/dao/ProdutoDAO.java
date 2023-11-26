/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.sp.padoka.dao;

import br.senac.sp.padoka.model.Categoria;
import br.senac.sp.padoka.model.Produto;
import br.senac.sp.padoka.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nathan Ferreira
 */
public class ProdutoDAO {

    public void inserir(Produto produto) {
        String sql = "INSERT INTO produtos (nome, categoria_id, unidade_de_medida, estoque, valor, status_produto) VALUES (?, ?, ?, ?, ?, 1)";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getCategoria());
            stmt.setString(3, produto.getUnidade_de_medida());
            stmt.setInt(4, produto.getEstoque());
            stmt.setDouble(5, produto.getValor());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir produto.", e);
        }
    }

    public void atualizar(Produto produto) {
        String sql = "UPDATE produtos SET nome = ?, categoria_id = ?, unidade_de_medida = ?, estoque = ?, valor = ? WHERE ID = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getCategoria());
            stmt.setString(3, produto.getUnidade_de_medida());
            stmt.setInt(4, produto.getEstoque());
            stmt.setDouble(5, produto.getValor());
            stmt.setInt(6, produto.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir produto.", e);
        }
    }

    public void deletar(int id) {
        String sql = "UPDATE produtos SET status_produto = 0 WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente.", e);
        }
    }

    public List<Produto> listarProdutos() {

        List produtos = new ArrayList();

        String sql = """
                     SELECT 
                         produtos.ID,
                         produtos.nome AS nome_produto,
                         produtos.unidade_de_medida,
                         produtos.estoque,
                         produtos.valor,
                         produtos.status_produto,
                         categorias_produtos.nome AS nome_categoria
                     FROM 
                         produtos
                     INNER JOIN 
                         categorias_produtos ON produtos.categoria_id = categorias_produtos.ID
                     WHERE 
                         produtos.status_produto = 1;
                     """;

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto();
                Categoria categoria = new Categoria();

                produto.setId(rs.getInt("ID"));
                produto.setNome(rs.getString("nome_produto"));
                produto.setValor(rs.getInt("valor"));
                produto.setEstoque(rs.getInt("estoque"));
                produto.setUnidade_de_medida(rs.getString("unidade_de_medida"));
                produto.setNomeCategoria(rs.getString("nome_categoria"));

                produtos.add(produto);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar categorias.", e);
        }

        return produtos;
    }

    public List<Categoria> getCategorias() {

        List categorias = new ArrayList();

        String sql = "SELECT * FROM categorias_produtos";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Categoria categoria = new Categoria(rs.getInt("id"), rs.getString("nome"));

                categorias.add(categoria);

            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar categorias.", e);
        }

        return categorias;
    }
}
