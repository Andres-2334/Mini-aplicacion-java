package es.fplumara.dam1.textapp;

import es.fplumara.dam1.textapp.config.AppConfig;
import es.fplumara.dam1.textapp.exceptions.ConfigException;
import es.fplumara.dam1.textapp.exceptions.StoreException;
import es.fplumara.dam1.textapp.files.StoreType;
import es.fplumara.dam1.textapp.files.TextStore;
import es.fplumara.dam1.textapp.files.TextStoreFactory;
import es.fplumara.dam1.textapp.model.Message;

public class App {
    public static void main(String[] args) {

        try {


            AppConfig config = new AppConfig();


            StoreType type = config.getStoreType();
            System.out.println("Tipo de almacenamiento: " + type);


            TextStore store = TextStoreFactory.createTextStore(config);




            Message m1 = new Message("Hola mundo desde Java");
            Message m2 = new Message("Este es el segundo mensaje");
            Message m3 = new Message("Aprendiendo ficheros en Java");
            Message m4 = new Message("Probando almacenamiento TXT o CSV");
            Message m5 = new Message("Ultimo mensaje de prueba");


            store.save(m1);
            store.save(m2);
            store.save(m3);
            store.save(m4);
            store.save(m5);


            System.out.println("\n--- CONTENIDO COMPLETO ---");
            System.out.println(store.readAll());


            System.out.println("\n--- ÚLTIMOS 3 MENSAJES ---");
            System.out.println(store.readLast(3));

        } catch (ConfigException e) {
            System.out.println("Error de configuración: " + e.getMessage());
        } catch (StoreException e) {
            System.out.println("Error de almacenamiento: " + e.getMessage());
        }

    }
}
