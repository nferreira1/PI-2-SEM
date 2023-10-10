/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.sp.padoka.dao;

import br.senac.sp.padoka.model.Produto;
import br.senac.sp.padoka.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Nathan Ferreira
 */
public class ProdutoDAO {

    public void inserir(Produto produto) {
        String sql = "INSERT INTO produtos (nome, categoria, unidade_de_medida, estoque, valor) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getCategoria());
            stmt.setString(3, produto.getUnidade_de_medida());
            stmt.setInt(4, produto.getEstoque());
            stmt.setInt(5, produto.getValor());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir cliente.", e);
        }
    }

    public void deletar() {
        String sql = "";
        
        

    }

}
