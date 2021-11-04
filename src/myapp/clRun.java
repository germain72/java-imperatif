package myapp;

import java.util.Date;

import myapp.dao.Dao;
import myapp.model.Person;

public class clRun {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dao monservice = new Dao();
		monservice.init();
		Person op1 = new Person();
		op1.setFirstName("Personne06");
		op1.setBirthDay(new Date("11/10/2021"));
		monservice.addPerson(op1);
		monservice.close();
	}

}
