package fr.aprr.formationjavaavance;

public class Singleton {

    private static Singleton instance = null;

    private Singleton() {
        // Cost++
        // 1 et 1 seule instance
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
