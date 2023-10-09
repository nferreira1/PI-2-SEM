/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.sp.padoka.dao;

import br.senac.sp.padoka.model.Cliente;
import br.senac.sp.padoka.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    
}
