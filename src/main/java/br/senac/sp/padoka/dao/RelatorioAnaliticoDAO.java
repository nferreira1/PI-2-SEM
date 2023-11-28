package br.senac.sp.padoka.dao;

import br.senac.sp.padoka.model.Cliente;
import br.senac.sp.padoka.model.Produto;
import br.senac.sp.padoka.model.RelatorioAnalitico;
import br.senac.sp.padoka.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Nathan Ferreira
 */
public class RelatorioAnaliticoDAO {

    public List<RelatorioAnalitico> buscaVendas() {
        List<RelatorioAnalitico> listaVendas = new ArrayList<>();

        String sql = """
                     SELECT v.valor, v.cliente_id, v.data_venda, i.produto_id, i.quantidade_produto
                     FROM vendas v
                     INNER JOIN itensVendas i ON v.ID = i.vendas_id
                     """;

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                RelatorioAnalitico venda = new RelatorioAnalitico();

                venda.setValor(rs.getDouble("valor"));
                venda.setClienteId(rs.getInt("cliente_id"));
                venda.setData(rs.getDate("data_venda"));
                venda.setProdutoId(rs.getInt("produto_id"));
                venda.setQuantidadeProduto(rs.getInt("quantidade_produto"));

                listaVendas.add(venda);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar vendas.", e);
        }

        return listaVendas;
    }

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

}
