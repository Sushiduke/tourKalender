package org.pakkagames.tourkalender.testconfig;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * @author jog
 */
@Configuration
@ComponentScan(basePackages = { "org.pakkagames.tourkalender.service", "org.pakkagames.tourkalender.repository", "org.pakkagames.tourkalender.gpx.service" })
@EnableJpaRepositories(basePackages = { "org.pakkagames.tourkalender.repository" })
@Profile("test")
// @Import(value = { PersistenceConfiguration.class })
public class SimpleTestHSQLConfiguration {

	@Bean
	public DataSource getDataSource() {

		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase dataSource = builder.setType(EmbeddedDatabaseType.HSQL) //
				// .addScript("sql/myGPSPosition_create.sql") //
				// .addScript("sql/myGPSPosition_insert.sql") //
				.build();
		return dataSource;
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(false);
		factory.setJpaVendorAdapter(vendorAdapter);

		factory.setPersistenceProviderClass(org.hibernate.jpa.HibernatePersistenceProvider.class);
		factory.setPackagesToScan("org.pakkagames.tourkalender.domain");
		factory.setDataSource(getDataSource());
		factory.afterPropertiesSet();

		return factory.getObject();
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		return txManager;
	}

}