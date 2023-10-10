/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.sp.padoka.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Nathan Ferreira
 */
public class ConverteData {

    public static String converteData(String dateStr, String tipo) {
        SimpleDateFormat sourceFormat;
        SimpleDateFormat destFormat;

        if (tipo.equals("US")) {
            sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
            destFormat = new SimpleDateFormat("yyyy-MM-dd");
        } else if (tipo.equals("BR")) {
            sourceFormat = new SimpleDateFormat("yyyy-MM-dd");
            destFormat = new SimpleDateFormat("dd/MM/yyyy");
        } else {
            return null;
        }

        try {
            Date date = sourceFormat.parse(dateStr);
            return destFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
