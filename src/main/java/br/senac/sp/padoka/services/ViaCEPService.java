/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.sp.padoka.services;

import br.senac.sp.padoka.interfaces.cliente.TelaCadastroCliente;
import br.senac.sp.padoka.util.ConverteJsonEmString;
import br.senac.sp.padoka.model.Endereco;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nathan.ferreira
 */
public class ViaCEPService {

    static String webService = "http://viacep.com.br/ws/";

    public static Endereco buscaEndereco(String cep) throws Exception {
        Endereco enderecoAux = new Endereco();
        String urlParaChamada = webService + cep + "/json";
        try {
            URL url = new URL(urlParaChamada);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            if (conexao.getResponseCode() != 200) {
                throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());
            }
            BufferedReader retorno = new BufferedReader(new InputStreamReader((conexao.getInputStream()), "UTF-8"));
            String jsonEmString = ConverteJsonEmString.converteJsonEmString(retorno);

            if (jsonEmString.trim().equals("{  \"erro\": true}")) {
                return enderecoAux;
            }
            Gson gson = new Gson();
            enderecoAux = gson.fromJson(jsonEmString, Endereco.class);
            return enderecoAux;
        } catch (Exception e) {
            Logger.getLogger(TelaCadastroCliente.class.getName()).log(Level.SEVERE, null, e);
            return enderecoAux;
        }
    }

}
