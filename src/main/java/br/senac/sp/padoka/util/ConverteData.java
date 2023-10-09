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

    public static String converteData(String dateStr) {
        SimpleDateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat destFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = sourceFormat.parse(dateStr);
            return destFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
