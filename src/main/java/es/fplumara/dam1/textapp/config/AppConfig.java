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

    private StoreType storeType;
    private String messagesFile;
    private int maxLenght;


    public File getStoreType() {

        return File;
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
        } catch (IOException e) {
            throw new ConfigException("");
        }


    }
}
