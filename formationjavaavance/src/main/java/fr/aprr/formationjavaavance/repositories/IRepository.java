package fr.aprr.formationjavaavance.repositories;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IRepository {

    void open(String path, char sep) throws FileNotFoundException;

    void read() throws IOException;

    void write(String path, char sep) throws IOException;

}
