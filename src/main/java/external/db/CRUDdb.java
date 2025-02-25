package external.db;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import presentation.model.Employees;

public class CRUDdb {

	public static void start() throws ClassNotFoundException, SQLException {
		System.out.println("Connected to the database! V3");
	}

	public static Map<Integer, Employees> getResReadRow() throws SQLException {
		System.out.println("getResReadRow");
		Map<Integer, Employees> employeeList = new HashMap<>();
		EntityManager em = DBConnectionProvider.getTransactionBegin();
		try {
			List<Employees> employeesList = em.createQuery("SELECT e FROM Employees e", Employees.class)
					.getResultList();
			DBConnectionProvider.getTransactionCommit();

			for (Employees emp : employeesList) {
				employeeList.put(emp.getId(), emp);
			}
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new SQLException("Error reading all employees", e);
		} finally {
			em.clear();
		}
		return employeeList;
	}

	public static Employees getResReadById(int id) throws SQLException {
		EntityManager em = DBConnectionProvider.getTransactionBegin();
		Employees emp = null;
		try {
			emp = em.find(Employees.class, id);
			DBConnectionProvider.getTransactionCommit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new SQLException("Error reading employee by id", e);
		} finally {
			em.clear();
		}
		return emp;
	}

	public static boolean insert(Employees emp) throws SQLException {
		System.out.println("insert 1");
		EntityManager em = DBConnectionProvider.getTransactionBegin();
		try {
			em.persist(emp);
			DBConnectionProvider.getTransactionCommit();
			System.out.println("insert 2");
			return true;
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new SQLException("Error during insert", e);
		} finally {
			em.clear();
		}
	}

	public static boolean update(Employees emp) throws SQLException {
		EntityManager em = DBConnectionProvider.getTransactionBegin();
		try {
			em.merge(emp);
			DBConnectionProvider.getTransactionCommit();
			return true;
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new SQLException("Error updating employee", e);
		} finally {
			em.clear();
		}
	}

	public static boolean delete(int id) throws SQLException {
		EntityManager em = DBConnectionProvider.getTransactionBegin();
		try {
			Employees emp = em.find(Employees.class, id);
			if (emp != null) {
				em.remove(emp);
				DBConnectionProvider.getTransactionCommit();
				return true;
			} else {
				DBConnectionProvider.getTransactionCommit();
				return false;
			}
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new SQLException("Error deleting employee", e);
		} finally {
			em.clear();
		}
	}

}
