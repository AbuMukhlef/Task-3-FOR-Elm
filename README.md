**Version 3: Enhancing Persistence Configuration with JPA and Hibernate**

### Introduction
In this version, we introduce a new way of configuring **JPA (Jakarta Persistence API)** and **Hibernate** by implementing `PersistenceUnitInfo`. This method allows us to programmatically define the persistence unit without relying solely on `persistence.xml`. Additionally, we ensure a structured connection to **PostgreSQL** using **HikariCP** for efficient connection pooling.

---

## 1. Implementing `MyPersistenceUnitInfo`

### **What is `MyPersistenceUnitInfo`?**
`MyPersistenceUnitInfo` is a Java class implementing the `PersistenceUnitInfo` interface. This class defines how our persistence unit is configured programmatically.

### **Key Features in `MyPersistenceUnitInfo`**
1. **Database Configuration:**
   - Uses **HikariCP** as the connection pool provider.
   - Configures **JTA transactions** for better resource management.
   - Specifies the database connection properties for **PostgreSQL**.

2. **Entity Management:**
   - Registers `presentation.model.Employees` as a managed entity.
   - Allows auto-detection of other annotated entities.

3. **Hibernate Properties:**
   - Uses `org.hibernate.dialect.PostgreSQLDialect` for PostgreSQL compatibility.
   - Enables `hibernate.hbm2ddl.auto=update` to automatically update the database schema.
   - Shows formatted SQL logs for debugging.

### **Example Code: MyPersistenceUnitInfo.java**
```java
package persistence;

import java.net.URL;
import java.util.List;
import java.util.Properties;
import javax.sql.DataSource;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.*;

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
    public List<String> getManagedClassNames() {
        return List.of("presentation.model.Employees");
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

    // Other required methods return default/null values
}
```

---

## 2. `persistence.xml` Configuration
Although we have programmatic configuration in `MyPersistenceUnitInfo`, we also keep `persistence.xml` as an alternative method for defining our persistence settings.

### **Why Keep `persistence.xml`?**
- Some frameworks prefer XML-based configurations.
- Provides an easy way to tweak settings without recompiling the application.

### **Example Code: persistence.xml**
```xml
<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence" version="2.1">
    <persistence-unit name="persistence-unit-test">
        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
        <properties>
            <property name="javax.persistence.jdbc.driver" value="org.postgresql.Driver"/>
            <property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/employeedb"/>
            <property name="javax.persistence.jdbc.user" value="abu_mukhlef"/>
            <property name="javax.persistence.jdbc.password" value=""/>
            <property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQLDialect"/>
            <property name="hibernate.hbm2ddl.auto" value="update"/>
            <property name="hibernate.show_sql" value="true"/>
            <property name="hibernate.format_sql" value="true"/>
        </properties>
    </persistence-unit>
</persistence>
```

---

## 3. How to Use This Configuration
### **1. Adding Dependencies (Maven Example)**
Ensure you have the necessary dependencies in `pom.xml`:
```xml
<dependencies>
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>6.3.1.Final</version>
    </dependency>
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>42.5.4</version>
    </dependency>
    <dependency>
        <groupId>com.zaxxer</groupId>
        <artifactId>HikariCP</artifactId>
        <version>5.0.1</version>
    </dependency>
</dependencies>
```

### **2. Running the Application**
- Ensure PostgreSQL is running and the database `employeedb` exists.
- Use an application framework like **Spring Boot** or **Jakarta EE** to manage transactions and persistence context.
- Use `EntityManager` to perform database operations.

### **3. Example Query using JPA**
```java
@PersistenceContext
private EntityManager entityManager;

public List<Employees> getAllEmployees() {
    return entityManager.createQuery("SELECT e FROM Employees e", Employees.class).getResultList();
}
```

---

## 4. Summary of Enhancements
- **Introduced programmatic persistence configuration using `PersistenceUnitInfo`.**
- **Configured Hibernate with PostgreSQL dialect and HikariCP.**
- **Retained `persistence.xml` for additional flexibility.**
- **Provided setup instructions, dependencies, and usage examples.**

By following this approach, we achieve a more flexible and efficient persistence configuration for our Employee Management System. 🚀

