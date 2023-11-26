/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.sp.padoka.dao;

import br.senac.sp.padoka.model.Cliente;
import br.senac.sp.padoka.model.Produto;
import br.senac.sp.padoka.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Nathan Ferreira
 */
public class VendaDAO {

    public Produto buscaProduto(int idProduto) {

        String sql = "SELECT * FROM produtos WHERE ID = ?";
        Produto produto = null;

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    produto = new Produto();
                    produto.setNome(rs.getString("nome"));
                    produto.setValor(rs.getDouble("valor"));
                    produto.setEstoque(rs.getInt("estoque"));

                }
            }

        } catch (SQLException | NullPointerException e) {
            throw new RuntimeException("Erro ao listar produto.", e);
        }

        return produto;
    }

    public Cliente buscaCliente(int idCliente) {

        String sql = "SELECT * FROM clientes WHERE ID = ?";
        Cliente cliente = null;

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setNome(rs.getString("nome"));
                }
            }

        } catch (SQLException | NullPointerException e) {
            throw new RuntimeException("Erro ao listar produto.", e);
        }

        return cliente;
    }

    public void realizaVenda(List<Integer> idsProdutos, double valorTotal, List<Integer> quantidadesProdutos, int idCliente) {
        String sqlVenda = "INSERT INTO vendas (valor, cliente_id) VALUES (?, ?)";
        String sqlItensVenda = "INSERT INTO itensVendas (vendas_id, produto_id, quantidade_produto) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmtVenda = conn.prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS)) {

            // Inserir na tabela vendas
            stmtVenda.setDouble(1, valorTotal);
            stmtVenda.setInt(2, idCliente);
            stmtVenda.executeUpdate();

            int idVenda = 0;
            try (ResultSet generatedKeys = stmtVenda.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    idVenda = generatedKeys.getInt(1);
                }
            }

            // Inserir itens na tabela itensVendas
            try (PreparedStatement stmtItensVenda = conn.prepareStatement(sqlItensVenda)) {
                for (int i = 0; i < idsProdutos.size(); i++) {
                    stmtItensVenda.setInt(1, idVenda);
                    stmtItensVenda.setInt(2, idsProdutos.get(i));
                    stmtItensVenda.setInt(3, quantidadesProdutos.get(i));

                    stmtItensVenda.executeUpdate();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao realizar venda.", e);
        }
    }

}
