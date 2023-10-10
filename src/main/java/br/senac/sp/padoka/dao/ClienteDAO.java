/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.sp.padoka.dao;

import br.senac.sp.padoka.model.Cliente;
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
public class ClienteDAO {

    public void inserir(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, CPF, data_de_nascimento, endereco, celular, telefone, email, sexo, estado_civil, observacoes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCPF());
            stmt.setString(3, cliente.getData_de_nascimento());
            stmt.setInt(4, cliente.getEndereco());
            stmt.setString(5, cliente.getCelular());
            stmt.setString(6, cliente.getTelefone());
            stmt.setString(7, cliente.getEmail());
            stmt.setString(8, cliente.getSexo());
            stmt.setString(9, cliente.getEstado_civil());
            stmt.setString(10, cliente.getObservacoes());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir cliente.", e);
        }
    }

    public void atualizar(Cliente cliente) {
        String sql = "UPDATE clientes SET nome = ?, CPF = ?, data_de_nascimento = ?, endereco = ?, celular = ?, telefone = ?, email = ?, sexo = ?, estado_civil = ?, observacoes = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCPF());
            stmt.setString(3, cliente.getData_de_nascimento());
            stmt.setInt(4, cliente.getEndereco());
            stmt.setString(5, cliente.getCelular());
            stmt.setString(6, cliente.getTelefone());
            stmt.setString(7, cliente.getEmail());
            stmt.setString(8, cliente.getSexo());
            stmt.setString(9, cliente.getEstado_civil());
            stmt.setString(10, cliente.getObservacoes());
            stmt.setInt(11, cliente.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente.", e);
        }
    }

    public List<Cliente> listarClientes() {
        List<Cliente> listaClientes = new ArrayList<>();

        String sql = "SELECT * FROM clientes";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();

                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCPF(rs.getString("CPF"));
                cliente.setData_de_nascimento(rs.getString("data_de_nascimento"));
                cliente.setEndereco(rs.getInt("endereco"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setEstado_civil(rs.getString("estado_civil"));
                cliente.setObservacoes(rs.getString("observacoes"));

                listaClientes.add(cliente);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes.", e);
        }

        return listaClientes;
    }

}
