package fr.aprr.formationjavaavance.repositories;

import com.opencsv.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CsvRepository implements IRepository {

    private CSVReader csvReader;
    private CSVWriter csvWriter;
    private List<String[]> rows;

    @Override
    public void open(String uri, char sep) throws IOException {
        BufferedReader reader;
        if(uri.startsWith("http")) {
            URL url = new URL(uri);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
        }
        else {
            reader = new BufferedReader(new FileReader("data/export.csv"));
        }
        CSVParser parser = new CSVParserBuilder().withSeparator(sep).build();
        this.csvReader = new CSVReaderBuilder(reader).withSkipLines(1).withCSVParser(parser).build();
    }

    @Override
    public void read() throws IOException {
        rows = csvReader.readAll();
        csvReader.close();
    }

    @Override
    public void write(String path, char sep, String charset) throws IOException {
        csvWriter = new CSVWriter(new OutputStreamWriter(new FileOutputStream("data/output.csv"), charset), sep, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
        csvWriter.writeAll(this.rows);
        csvWriter.close();
    }
}
