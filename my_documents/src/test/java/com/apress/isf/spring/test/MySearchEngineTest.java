package com.apress.isf.spring.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;
import com.apress.isf.java.service.MySearchEngine;
import com.apress.isf.java.service.SearchEngine;

public class MySearchEngineTest {
	
	private ClassPathXmlApplicationContext context;
	private Type documentType;
	private SearchEngine searchEngine = new MySearchEngine();
	
	@Before
	public void setup() {
		context = new ClassPathXmlApplicationContext("META-INF/spring/mydocuments-context.xml");
		searchEngine = context.getBean("searchEngineOld", SearchEngine.class);
		documentType = context.getBean("webType", Type.class);
	}

	@Test
	public void testFindByType() {
		
		List<Document> documents = searchEngine.findByType(documentType);
		assertThat(documents.size(), is(1));
		assertThat(documents.get(0).getType().getName(), is(documentType.getName()));
		assertThat(documents.get(0).getType().getDesc(), is(documentType.getDesc()));
		assertThat(documents.get(0).getType().getExtension(), is(documentType.getExtension()));
	}

	@Test
	public void testListAll() {
		List<Document> documents = searchEngine.listAll();
		assertThat(documents.size(), is(4));
	}

}
