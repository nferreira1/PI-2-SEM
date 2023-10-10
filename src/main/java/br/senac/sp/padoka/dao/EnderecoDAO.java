/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.sp.padoka.dao;

import br.senac.sp.padoka.model.Endereco;
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
public class EnderecoDAO {

    public int inserir(Endereco endereco) {
        String sql = "INSERT INTO enderecos (CEP, logradouro, numero, bairro, complemento, UF, cidade, cliente_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, endereco.getCep());
            stmt.setString(2, endereco.getLogradouro());
            stmt.setString(3, endereco.getNumero());
            stmt.setString(4, endereco.getBairro());
            stmt.setString(5, endereco.getComplemento());
            stmt.setString(6, endereco.getUf());
            stmt.setString(7, endereco.getLocalidade());
            stmt.setInt(8, endereco.getCliente_id());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException("Erro ao inserir endereço. Nenhum ID obtido.");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir cliente.", e);
        }
    }

    public void atualizar(Endereco endereco) {
        String sql = "UPDATE enderecos SET CEP = ?, logradouro = ?, numero = ?, bairro = ?, complemento = ?, UF = ?, cidade = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, endereco.getCep());
            stmt.setString(2, endereco.getLogradouro());
            stmt.setString(3, endereco.getNumero());
            stmt.setString(4, endereco.getBairro());
            stmt.setString(5, endereco.getComplemento());
            stmt.setString(6, endereco.getUf());
            stmt.setString(7, endereco.getLocalidade());
            stmt.setInt(8, endereco.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar endereco.", e);
        }
    }

    public List<Endereco> listarEndereco(int id) {
        List<Endereco> listaEnderecos = new ArrayList<>();

        String sql = "SELECT * FROM enderecos WHERE ID = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);  // Configurar o valor do parâmetro ANTES de executar a consulta
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Endereco endereco = new Endereco();

                    endereco.setId(rs.getInt("id"));
                    endereco.setLogradouro(rs.getString("logradouro"));
                    endereco.setCep(rs.getString("cep"));
                    endereco.setLocalidade(rs.getString("cidade"));
                    endereco.setUf(rs.getString("uf"));
                    endereco.setNumero(rs.getString("numero"));
                    endereco.setBairro(rs.getString("bairro"));
                    endereco.setComplemento(rs.getString("complemento"));

                    listaEnderecos.add(endereco);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar endereços.", e);
        }

        return listaEnderecos;
    }

    public Endereco getEndereco(int id) {
        Endereco endereco = new Endereco();

        String sql = "SELECT * FROM enderecos WHERE cliente_ID = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    endereco.setId(rs.getInt("ID"));
                    endereco.setLogradouro(rs.getString("logradouro"));
                    endereco.setCep(rs.getString("cep"));
                    endereco.setLocalidade(rs.getString("cidade"));
                    endereco.setUf(rs.getString("uf"));
                    endereco.setNumero(rs.getString("numero"));
                    endereco.setBairro(rs.getString("bairro"));
                    endereco.setComplemento(rs.getString("complemento"));

                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar endereços.", e);
        }

        return endereco;
    }
}
