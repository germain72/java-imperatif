package myapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import myapp.model.Person;

public class Dao {
	private EntityManagerFactory factory = null;
	
	public void init() {
		factory = Persistence.createEntityManagerFactory("myBase");
	}
	
	public void close() {
		if (factory != null) {
		factory.close();
		}
	}
	
	// Creer un EM et ouvrir une transaction
	private EntityManager newEntityManager() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		return (em);
	}
	
	// Fermer un EM et defaire la transaction si necessaire
	private void closeEntityManager(EntityManager em) {
		if (em != null) {
			if (em.isOpen()) {
				EntityTransaction t = em.getTransaction();
			if (t.isActive()) {
				try {
				t.rollback();
				} catch (PersistenceException e) {
					e.printStackTrace();
					
				}
			}
				em.close();
			}
		}
	}
	
	// Nouvelle version simplifiee
	public Person addPerson(Person p) {
		EntityManager em = null;
		try {
			em = newEntityManager();
			// utilisation de l'EntityManager
			em.persist(p);
			em.getTransaction().commit();
			System.err.println("addPerson witdh id=" + p.getId());
			return (p);
		} finally {
			closeEntityManager(em);
		}
	}
	
	public void updatePerson(Person p) {
		EntityManager em = null;
		try {
			em = newEntityManager();
			em.merge(p);
			em.getTransaction().commit();
		} 		catch(Exception e) {
			e.printStackTrace();
		}		finally {
			closeEntityManager(em);
		}
	}
	public void removePerson(long id) {
		EntityManager em = null;
		try {
			em = newEntityManager();
			em.remove(id);
			em.getTransaction().commit();
		} 		catch(Exception e) {
			e.printStackTrace();
		}		finally {
			closeEntityManager(em);
		}
	}
	
	public Person findPerson(long id) {
		EntityManager em = null;
			try {
				em = newEntityManager();
				// utilisation de l'EntityManager
				Person p = em.find(Person.class, id);
				return p;
			} finally {
				closeEntityManager(em);
			}
		}
	
	public List<Person> findAllPersons() {
			EntityManager em = null;
			try {
					em = newEntityManager();
					String query = "SELECT p FROM Person p";
					TypedQuery<Person> q = em.createQuery(query, Person.class);
					return q.getResultList();
				} finally {
					closeEntityManager(em);
				}
		}
	//a-vos-clics@wanadoo.fr
	public List<Person> findPersonsByFirstName(String pattern) {
			EntityManager em = null;
			try {
					em = newEntityManager();
					String query = "SELECT p FROM Person p where p.firstName like :param1";
					TypedQuery<Person> q = em.createQuery(query, Person.class);
					q.setParameter("param1", pattern);
					return q.getResultList();
				} finally {
					closeEntityManager(em);
			}
		
		}
}
