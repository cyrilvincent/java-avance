package fr.aprr.formationjavaavance.repositories;

public interface IRepository {

    void open(String path, String sep);

    void save(String path);

    void read();

    void write();

}
