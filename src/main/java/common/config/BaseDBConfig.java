package common.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ConfigurationProperties("oracle")
@MapperScan(value = "common.mapper", sqlSessionFactoryRef="sqlSessionFactory_base")
@EnableJpaRepositories(basePackages= {"common.repository"},
		entityManagerFactoryRef = "entityManagerFactory_base",
		transactionManagerRef = "transactionManager_base"
		)
@EnableTransactionManagement

public class BaseDBConfig {

	@Bean(name = "DataSource_base", destroyMethod = "close")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource datasource01() {
        return DataSourceBuilder.create().build();
    }
    
	
	@Bean(name = "sqlSessionFactory_base")
    public SqlSessionFactory sqlSessionFactory_base(@Qualifier("DataSource_base")DataSource dataSource)throws Exception{
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
            sessionFactory.setDataSource(dataSource);
            
            Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/WebServiceMapper.xml");
            sessionFactory.setMapperLocations(res);
            sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mapper/mybatis-config.xml"));
            
            return sessionFactory.getObject();
    }
	
	@Bean(name = "entityManagerFactory_base")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			EntityManagerFactoryBuilder builder,
			@Qualifier("DataSource_base") DataSource dataSource) {
		
		// JPA Vendor setting (ORACLE
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.Oracle10gDialect");
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setDatabase(Database.ORACLE);
		
		// Make entityManagerFactory_
		LocalContainerEntityManagerFactoryBean  em =  builder.dataSource(dataSource)
				.packages("common.entity").build();
		em.setPersistenceUnitName("jpa_test");
		em.setJpaVendorAdapter(jpaVendorAdapter);
		em.setJpaProperties(additionalJpaProperties());
		
		return em;
	}
	
	Properties additionalJpaProperties(){
	        Properties properties = new Properties();
	        properties.setProperty("hibernate.format_sql", "false");
	        //properties.setProperty("generateDdl", "true");
	        //properties.setProperty("hibernate.default_schema", "bcs");
	        //properties.setProperty("hibernate.ddl-auto", "update");
	        properties.setProperty("hibernate.hbm2ddl.auto", "update");
	        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
	        properties.setProperty("hibernate.show_sql", "false");
	        properties.setProperty("hibernate.jdbc.batch_size", "10000");
	        properties.setProperty("hibernate.use_sql_comments", "true");
	        properties.setProperty("hibernate.format_sql", "false");
	        //properties.setProperty("hibernate.type", "trace");
	        //properties.setProperty("hibernate.order_inserts", "true");
	        //properties.setProperty("hibernate.order_updates", "true");
	        //properties.setProperty("hibernate.jdbc.batch_versioned_data", "true");
	        return properties;
	    }
	
	
	@Bean(name = "transactionManager_base")
	public PlatformTransactionManager transactionManager(
			@Qualifier("entityManagerFactory_base") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
