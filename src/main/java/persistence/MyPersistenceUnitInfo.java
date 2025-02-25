package persistence;

import java.net.URL;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.PersistenceUnitTransactionType;

public class MyPersistenceUnitInfo implements PersistenceUnitInfo {

	@Override
	public String getPersistenceUnitName() {
		return "persistence-unit-test";
	}

	@Override
	public String getPersistenceProviderClassName() {
		return "org.hibernate.jpa.HibernatePersistenceProvider";
	}

	@Override
	public PersistenceUnitTransactionType getTransactionType() {

		return PersistenceUnitTransactionType.JTA;
	}

	@Override
	public DataSource getJtaDataSource() {
		HikariDataSource dataSource = new HikariDataSource();

		dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/employeedb");
		dataSource.setUsername("abu_mukhlef");
		dataSource.setPassword("");

		return dataSource;
	}

	@Override
	public DataSource getNonJtaDataSource() {

		return null;
	}

	@Override
	public List<String> getMappingFileNames() {

		return null;
	}

	@Override
	public List<URL> getJarFileUrls() {

		return null;
	}

	@Override
	public URL getPersistenceUnitRootUrl() {

		return null;
	}

	@Override
	public List<String> getManagedClassNames() {

		return List.of("presentation.model.Employees");
	}

	@Override
	public boolean excludeUnlistedClasses() {

		return false;
	}

	@Override
	public SharedCacheMode getSharedCacheMode() {

		return null;
	}

	@Override
	public ValidationMode getValidationMode() {

		return null;
	}

	@Override
	public Properties getProperties() {
	    Properties properties = new Properties();
	    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
	    properties.setProperty("hibernate.hbm2ddl.auto", "update");
	    properties.setProperty("hibernate.show_sql", "true");
	    properties.setProperty("hibernate.format_sql", "true");
	    return properties;
	}


	@Override
	public String getPersistenceXMLSchemaVersion() {

		return null;
	}

	@Override
	public ClassLoader getClassLoader() {

		return null;
	}

	@Override
	public void addTransformer(ClassTransformer transformer) {

	}

	@Override
	public ClassLoader getNewTempClassLoader() {

		return null;
	}

}
