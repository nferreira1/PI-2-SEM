/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.sp.padoka.model;

/**
 *
 * @author Nathan Ferreira
 */
public class Cliente {

    private int id;
    private String nome;
    private String CPF;
    private String data_de_nascimento;
    private Endereco endereco;
    private String celular;
    private String telefone;
    private String email;
    private String sexo;
    private String estado_civil;
    private String observacoes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        String cpfEdit = CPF.replaceAll("[-.]", "");
        if (cpfEdit.isBlank() || cpfEdit.isEmpty()) {
            cpfEdit = null;
        }
        this.CPF = cpfEdit;
    }

    public String getData_de_nascimento() {
        return data_de_nascimento;
    }

    public void setData_de_nascimento(String data_de_nascimento) {
        this.data_de_nascimento = data_de_nascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        String celularEdit = "";
        if (celular != null) {
            celularEdit = celular.replaceAll("[-()]", "");
            if (celularEdit.isBlank() || celularEdit.isEmpty()) {
                celularEdit = null;
            }
        }

        this.celular = celularEdit;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        String telefoneEdit = "";
        if (telefone != null) {
            telefoneEdit = telefone.replaceAll("[-()]", "");
            if (telefoneEdit.isBlank() || telefoneEdit.isEmpty()) {
                telefoneEdit = null;
            }
        }
        this.telefone = telefoneEdit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null) {
            if (email.isBlank() || email.isEmpty()) {
                email = null;
            }
        }

        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        if (observacoes != null) {
            if (observacoes.isBlank() || observacoes.isEmpty()) {
                observacoes = null;
            }
        }
        this.observacoes = observacoes;
    }
}
