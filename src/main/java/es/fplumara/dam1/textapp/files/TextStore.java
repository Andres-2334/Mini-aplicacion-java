package es.fplumara.dam1.textapp.files;

import es.fplumara.dam1.textapp.model.Message;

public interface TextStore {

    public void save(Message message);

    public String readAll();

    public String readLast(int n);
}
