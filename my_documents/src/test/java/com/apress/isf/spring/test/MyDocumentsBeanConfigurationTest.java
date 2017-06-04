package com.apress.isf.spring.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;
import com.apress.isf.java.service.SearchEngine;
import com.apress.isf.spring.config.MyDocumentsContext;

public class MyDocumentsBeanConfigurationTest {

	private ApplicationContext context;
	private SearchEngine searchEngine;
	private Type webType;
	
	@Before
	public void setup() {
		context = new AnnotationConfigApplicationContext(MyDocumentsContext.class);
		searchEngine = context.getBean(SearchEngine.class);
		webType = context.getBean(Type.class);
	}

	@Test
	public void testFindByType() {
				
		List<Document> documents = searchEngine.findByType(webType);
		
		assertThat(documents.size(), is(1));
		assertThat(documents.get(0).getType().getName(), is(webType.getName()));
		assertThat(documents.get(0).getType().getDesc(), is(webType.getDesc()));
		assertThat(documents.get(0).getType().getExtension(), is(webType.getExtension()));
	}

	@Test
	public void testListAll() {
		List<Document> documents = searchEngine.listAll();
		assertThat(documents.size(), is(4));
	}
}
