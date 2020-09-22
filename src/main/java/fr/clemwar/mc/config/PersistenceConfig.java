package fr.clemwar.mc.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import fr.clemwar.mc.dao.McDao;
import fr.clemwar.mc.dao.McDaoImpl;

@Configuration
@PropertySource("classpath:application.properties")
public class PersistenceConfig {
	
	@Autowired
	private Environment env;

	/**
	 * Datasource settings : connect to bdd mc_project_db
	 * 
	 * @return Datasource
	 */
	@Bean
	public DataSource dataSource() {
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(env.getProperty("postgreDriver"));
		dataSourceBuilder.url(env.getProperty("dbUrl"));
		dataSourceBuilder.username(env.getProperty("dbUsername"));
		dataSourceBuilder.password(env.getProperty("dbPassword"));
		return dataSourceBuilder.build();
	}

	/**
	 * Entity Manager Factory with hibernate
	 * 
	 * @return EntityManager
	 */
	@Bean
	public EntityManagerFactory entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("fr.clemwar.mc.model");
		factory.setDataSource(dataSource());
		factory.afterPropertiesSet();

		return factory.getObject();
	}

	/**
	 * Prepare transaction manager
	 * 
	 * @return PlatformTransactionManager
	 */
	@Bean
	public PlatformTransactionManager transactionManager() {

		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		return txManager;
	}

	@Bean
	McDao mcDao() {
		return new McDaoImpl();
	}
}
