package fr.aprr.formationjavaavance.entities;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Book implements IMedia {

    private int id;
    private String title;
    private double price;
    private int nbPage;

    @Override
    public double getNetPrice() {
        return this.getPrice() * 1.055;
    }

}
