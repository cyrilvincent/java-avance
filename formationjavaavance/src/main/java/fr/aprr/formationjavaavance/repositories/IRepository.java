package fr.aprr.formationjavaavance.repositories;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface IRepository {

    void open(String path, char sep) throws IOException;

    void read() throws IOException;

    void write(String path, char sep, String charset) throws IOException;

    List<String[]> getRows();

}
