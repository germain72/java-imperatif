package tests.java;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import myapp.AppSpring;
import myapp.dao.Dao2;
import myapp.model.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppSpring.class)
class TestDao2 {
	@Autowired
	Dao2 dao;
	

	@Test
	void testAddlivre() {
		Person p = new Person("BEBE", new Date("12/10/2021"));
		dao.addperson(p);
		
	}

}
