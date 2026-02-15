package es.fplumara.dam1.textapp.files;

import es.fplumara.dam1.textapp.config.AppConfig;
import es.fplumara.dam1.textapp.exceptions.StoreException;
import es.fplumara.dam1.textapp.model.Message;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class FileTextStore implements TextStore{
    private AppConfig appConfig;

    public FileTextStore(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Override
    public void save(Message message) {
        String ficheroSalida = appConfig.getMessagesFile();
        File file = new File(ficheroSalida);

        if (!file.exists()){
            try{
                file.createNewFile();
            } catch (IOException e){
                throw new StoreException("No se pudo crear fichero");
            }
        }

        String texto = message.getTexto();
        if (texto.length() > appConfig.getMaxLenght()){
            texto = texto.substring(0, appConfig.getMaxLenght());
        }

        try {
            String linea = message.getTimestamp() + "|" + message.getNumeropalabras() + "|" + texto;

            FileWriter fw = new FileWriter(file, true);
            fw.write(linea + System.lineSeparator());
            fw.close();
        } catch (StoreException e) {
            throw new StoreException("");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String readAll() {
        File file = new File(appConfig.getMessagesFile());
        try {
            return Files.readString(file.toPath()); //no entiendo esto
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String readLast(int n) {
        File file = new File(appConfig.getMessagesFile());
        try {
            List<String> lineas = Files.readAllLines(file.toPath());

            int total = lineas.size();


            List<String> ultimaslineas = lineas.subList(total - n, total);


        return String.join(System.lineSeparator(), ultimaslineas);

        } catch (IOException e){
            throw new StoreException("");
        }
    }
}
