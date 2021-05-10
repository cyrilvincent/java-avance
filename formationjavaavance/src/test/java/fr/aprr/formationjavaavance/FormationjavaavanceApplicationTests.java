package fr.aprr.formationjavaavance;

import fr.aprr.formationjavaavance.entities.Book;
import fr.aprr.formationjavaavance.entities.IMedia;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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


}
