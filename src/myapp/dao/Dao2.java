package myapp.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import myapp.model.Person;

@Service
@Repository
@Transactional
public class Dao2 {
	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
	EntityManager em;
	public Person addperson(Person p) {
		em.persist(p);
		System.err.println("addPerson witdh id=" + p.getId());
		return (p);
	}
}
