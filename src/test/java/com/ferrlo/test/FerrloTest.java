package com.ferrlo.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;
@SpringApplicationContext({ "spring/application-config.xml", "spring/spring-hibernate.xml" })
public class FerrloTest extends UnitilsJUnit4 {
	@SpringApplicationContext
	public ApplicationContext applicationContext;

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Test
	public void testApplicationContext() {
		assertNotNull(applicationContext);
	}

}
