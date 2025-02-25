package external.db;

import java.sql.SQLException;

import org.hibernate.HibernateException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBConnectionProvider {
//	private static EntityManagerFactory emf = new HibernatePersistenceProvider()
//			.createContainerEntityManagerFactory(new MyPersistenceUnitInfo(), new HashMap<>());

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit-test");
	private static EntityManager em = emf.createEntityManager();

	public static EntityManager getTransactionBegin() throws SQLException {
		try {
			em.getTransaction().begin();
			return em;
		} catch (HibernateException e) {
			em.getTransaction().rollback();
			System.out.println("Error connecting to the database: \n" + e.getMessage() + "\n" + e.getStackTrace());
			throw e;
		} finally {
			em.clear();
		}
	}

	public static EntityManager getTransactionCommit() throws SQLException {
		try {
			em.getTransaction().commit();
			return em;
		} catch (Exception e) {
			System.out.println("Error connecting to the database: \n" + e.getMessage());
			throw e;
		} finally {
			em.clear();
		}
	}

//	public static EntityManager setEntityManagerPersist(Object emp) throws SQLException {
//		try {
//			em.persist(emp);
//			return em;
//		} catch (Exception e) {
//			System.out.println("Error connecting to the database: \n" + e.getMessage());
//			throw e;
//		}
//	}
//
//	public static EntityManager getEntityManagerClose() throws SQLException {
//		try {
//			em.close();
//			return em;
//		} catch (Exception e) {
//			System.out.println("Error connecting to the database: \n" + e.getMessage());
//			throw e;
//		}
//	}

}
