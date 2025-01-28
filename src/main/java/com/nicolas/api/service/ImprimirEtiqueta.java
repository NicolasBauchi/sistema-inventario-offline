package com.nicolas.api.service;

import com.nicolas.api.models.Equipo;
import org.springframework.stereotype.Service;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class ImprimirEtiqueta {

    private String printerName;


    public void imprimirTicket(Equipo eq){

        this.printerName = PrinterUtils.obtenerImpresoraConectada();

        if (printerName == null){
            System.out.println("No se encontró ninguna impresora conectada.");
        }else{
            System.out.println("Impresora conectada: " + printerName);
        }

        //Creación de impresión:

        String fileName = "/opt/tomcat/archivos/" + eq.getSerie() + ".zpl";
        String data = eq.generarTicket();


        try {
            FileWriter writer = new FileWriter(fileName);
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write(data);
            buffer.close();
            System.out.println("El archivo " + fileName + " ha sido creado correctamente.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //Enviar archivo a imprimir:
       // String printerName = "Zebra_Technologies_ZTC_GC420t_";
        //String printerName = "Zebra_Technologies_ZTC_ZD220-203dpi_ZPL";
        ProcessBuilder builder = new ProcessBuilder("lp", "-d", printerName, fileName);
        try {
            Process process = builder.start();
            process.waitFor();
            System.out.println("El ticket " + fileName + " ha sido enviado a imprimir.");
            System.out.println("builder: " + builder.toString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


        //Borrar archivo ya impreso:

        File file = new File(fileName);
        if (file.delete()) {
            System.out.println("El archivo " + fileName + " ha sido eliminado correctamente.");
        } else {
            System.out.println("No se pudo eliminar el archivo " + fileName + ".");
        }





    }

}
