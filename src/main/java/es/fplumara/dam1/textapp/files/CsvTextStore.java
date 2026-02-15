package es.fplumara.dam1.textapp.files;


import es.fplumara.dam1.textapp.config.AppConfig;
import es.fplumara.dam1.textapp.exceptions.StoreException;
import es.fplumara.dam1.textapp.model.Message;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.util.List;

public class CsvTextStore implements TextStore{

    private AppConfig appConfig;

    public CsvTextStore(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Override
    public void save(Message message) {
        File file = new File(appConfig.getMessagesFile());

        try {


            if (!file.exists()) {
                file.createNewFile();
            }

            boolean estaVacio = file.length() == 0;

            FileWriter writer = new FileWriter(file, true);

            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);


            if (estaVacio) {
                printer.printRecord("timestamp", "wordCount", "text");
            }


            String texto = message.getTexto();
            if (texto.length() > appConfig.getMaxLenght()) {
                texto = texto.substring(0, appConfig.getMaxLenght());
            }


            printer.printRecord(
                    message.getTimestamp(),
                    message.getNumeropalabras(),
                    texto
            );

            printer.close();

        } catch (IOException e) {
            throw new StoreException("Error guardando CSV");
        }
    }

    @Override
    public String readAll() {
        File file = new File(appConfig.getMessagesFile());

        if (!file.exists()) {
            return "";
        }

        try {

            List<String> lineas = Files.readAllLines(file.toPath());

            return String.join(System.lineSeparator(), lineas);

        } catch (IOException e) {
            throw new StoreException("Error leyendo CSV");
        }
    }

    @Override
    public String readLast(int n) {

        File file = new File(appConfig.getMessagesFile());

        if (!file.exists()) {
            return "";
        }

        try {

            List<String> lineas = Files.readAllLines(file.toPath());

            int total = lineas.size();

            if (n >= total) {
                return String.join(System.lineSeparator(), lineas);
            }

            List<String> ultimas = lineas.subList(total - n, total);

            return String.join(System.lineSeparator(), ultimas);

        } catch (IOException e) {
            throw new StoreException("Error leyendo últimas filas");
        }
    }
}
