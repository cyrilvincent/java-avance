package fr.aprr.formationjavaavance.repositories;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PgRepository implements IRepository {

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.drv}")
    private String jdbcDrv;

    private List<String[]> rows;

    @Override
    public void open(String path, char sep) throws FileNotFoundException {
        // Jusqu'à getConnection
    }

    @Override
    public void read() throws IOException {
        // La requête et stocke le résulat dans rows
        // + Test
    }

    @Override
    public void write(String path, char sep, String charset) throws IOException {

    }
}
