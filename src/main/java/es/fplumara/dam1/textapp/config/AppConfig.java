package es.fplumara.dam1.textapp.config;

import es.fplumara.dam1.textapp.exceptions.ConfigException;
import es.fplumara.dam1.textapp.files.StoreType;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class AppConfig {

    private String storeType;
    private String messagesFile;
    private int maxLenght;


    public StoreType getStoreType() {
        try {
            return StoreType.valueOf(storeType);
        }catch (IllegalArgumentException e){
            throw new ConfigException("Tipo no valido" + storeType);
        }

    }

    public int getMaxLenght() {
        return maxLenght;
    }

    public String getMessagesFile() {
        return messagesFile;
    }

    public AppConfig() {
        Path path = Path.of("C:\\Users\\AndrésJaimeEduardoDí\\OneDrive - SUMMA Formación Profesional\\Documentos\\GitHub\\Mini-aplicacion\\data\\config.properties");
        Properties properties = new Properties();


        try (InputStream in = Files.newInputStream(path)) {
            properties.load(in);
            storeType = properties.getProperty("store.type");
            messagesFile = properties.getProperty("messages.file");
            String maxLengthStr = properties.getProperty("messages.maxLength");
        } catch (IOException e) {
            throw new ConfigException("");
        }


    }
}
