package fr.aprr.formationjavaavance.repositories;

public interface IRepository {

    void open(String path);

    void save(String path);

    void read();

    void write();

}
