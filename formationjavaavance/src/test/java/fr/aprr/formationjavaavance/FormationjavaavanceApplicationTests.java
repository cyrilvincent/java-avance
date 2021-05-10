package fr.aprr.formationjavaavance;

import com.opencsv.*;
import com.sun.tools.javac.Main;
import fr.aprr.formationjavaavance.entities.Book;
import fr.aprr.formationjavaavance.entities.IMedia;
import fr.aprr.formationjavaavance.repositories.CsvRepository;
import fr.aprr.formationjavaavance.services.MainService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@SpringBootTest
@Slf4j
class FormationjavaavanceApplicationTests {

	@Autowired
	private ApplicationContext context;

	@Test
	void contextLoads() {
	}

	@Test
	void singletonTest() {
		Singleton s = Singleton.getInstance();
		int h1 = s.hashCode();
		Singleton s2 = Singleton.getInstance();
		int h2 = s2.hashCode();
		Assert.isTrue(h1 == h2, "Hashcode equals");
	}

	@Test
	void bookTest() {
		Book b = new Book(1,"Java",10,99);
		Assert.isTrue(b != null, "Book null");
	}

	@Test
	void iocTest() {
		Book b = context.getBean("book", Book.class);
		Assert.isTrue(b != null, "Book null");
		IMedia m = context.getBean("book", IMedia.class);
		Assert.isTrue(m != null, "Book null");
	}

	@Test
	void forNameTest() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Book b = (Book)Class.forName("fr.aprr.formationjavaavance.entities.Book").getDeclaredConstructor().newInstance();
		Assert.isTrue(b != null, "Book null");
	}

	@Test
	void reflexionTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Book b = new Book(1,"Java",10,99);
		Class c = b.getClass();
		for(Annotation a : c.getAnnotations()) {
			log.info(a.toString());
		}
		for(Method m : c.getDeclaredMethods()) {
			log.info(m.getName());
		}
		Method m = c.getMethod("toString");
		m.invoke(b);
		m = c.getMethod("setPrice", double.class);
		m.invoke(b, 99);
		Assert.isTrue(b.getPrice() == 99, "Price");
	}

	@Test
	void openCsvTest() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data/export.csv"));
		CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
		CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).withCSVParser(parser).build();
		List<String[]> rows = csvReader.readAll();
		for(String[] row : rows) {
			for(String value : row) {
				System.out.print(value + "|");
			}
			System.out.println();
		}
		CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(new FileOutputStream("data/output.csv"), "Cp1252"), ';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
		csvWriter.writeAll(rows);
		csvWriter.close();
	}

	@Test
	void RepositoryTestRead() throws IOException {
		CsvRepository repository = new CsvRepository();
		repository.open("data/export.csv", ';');
		repository.read();
		Assert.isTrue(repository.getRows().size() > 0, "Reader");
	}

	@Test
	void RepositoryTestWrite() throws IOException {
		CsvRepository repository = new CsvRepository();
		repository.open("data/export.csv", ';');
		repository.read();
		repository.write("data/output.csv", ';');
	}

	@Autowired
	private MainService service;

	@Test
	void MainServiceTest() throws IOException {
		service.workflow("data/export.csv", "data/output.csv");
	}


}
