package com.nicolas.api.service;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PrinterUtils {

    public static String obtenerImpresoraConectada(){


        /** funciona pero no se sabe bien cual esta conectada.
         *
         * PrintService[] servicios = PrintServiceLookup.lookupPrintServices(null,null);
        if (servicios.length > 0){
            return servicios[0].getName();
        }
        return null;*/

        try {
            Process process = Runtime.getRuntime().exec("lpstat -p");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("idle") || line.contains("enabled")) {
                    // Devuelve el nombre de la impresora
                    return line.split(" ")[1];
                }
            }
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;



    }
}
