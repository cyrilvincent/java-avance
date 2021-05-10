package fr.aprr.formationjavaavance.entities;

public interface IMedia {
    double getNetPrice();

    int getId();

    String getTitle();

    double getPrice();

    int getNbPage();

    void setId(int id);

    void setTitle(String title);

    void setPrice(double price);

    void setNbPage(int nbPage);
}
