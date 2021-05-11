package fr.aprr.formationjavaavance.services;

import fr.aprr.formationjavaavance.repositories.CsvRepository;
import fr.aprr.formationjavaavance.repositories.IRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class MainService {

    @Autowired
    private IRepository repository;
    private char sep = ';';
    private int sizeLimit = 60;
    private String outCharset = "UTF-8";

    public void workflow(String inPath, String outPath) throws IOException {
        this.repository.open(inPath, this.sep);
        this.repository.read();
        converter();
        this.repository.write(outPath, this.sep, this.outCharset);
    }

    public void converter() {
        for (String[] row : this.repository.getRows()) {
            numeroToUpper(row);
            denominationCrop(row);
        }
    }

    public void numeroToUpper(String[] row) {
        String numero = row[0];
        numero = numero.toUpperCase();
        row[0] = numero;
    }

    public void denominationCrop(String[] row) {
        String denomination = row[1];
        if (denomination.length() > this.sizeLimit) {
            denomination = denomination.substring(0, this.sizeLimit);
            row[1] = denomination;
        }
    }
}
