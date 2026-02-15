package es.fplumara.dam1.textapp.files;

import es.fplumara.dam1.textapp.config.AppConfig;
import es.fplumara.dam1.textapp.exceptions.ConfigException;
import es.fplumara.dam1.textapp.exceptions.StoreException;

public class TextStoreFactory {
    public static TextStore createTextStore(AppConfig appConfig) {

        StoreType type;

        try {
            type = appConfig.getStoreType();
        } catch (ConfigException e) {
            throw new StoreException("Error obteniendo tipo de almacenamiento");
        }

        switch (type) {

            case FILE:
                return new FileTextStore(appConfig);

            case CSV:
                return new CsvTextStore(appConfig);

            default:
                throw new StoreException("Tipo de almacenamiento no válido");
        }
    }
}
