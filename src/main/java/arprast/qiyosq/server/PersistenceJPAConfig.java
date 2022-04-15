package arprast.qiyosq.server;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author ari-prasetiyo
 * @see http://www.baeldung.com/the-persistence-layer-with-spring-and-jpa
 * @see https://stackoverflow.com/questions/3880563/what-transaction-manager-to-use-jpa-spring
 * @see http://blog.netgloo.com/2014/10/06/spring-boot-data-access-with-jpa-hibernate-and-mysql/
 * @category Transaction Manager, JPA Transaction Manager
 */
//@Configuration
//@ComponentScan("arprast.qiyosq")
//@EnableTransactionManagement(proxyTargetClass=true)
//@EnableJpaRepositories(basePackages = "arprast.qiyosq", transactionManagerRef = "transactionManager")
//@AnnotationDrivenTx(transactionManager="txManager") 
//@EnableTransactionManagement
public class PersistenceJPAConfig {

	/*@Autowired
    private DataSource dataSource;
//	@Autowired
//    EntityManagerFactory emf;
	
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean emf() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan(new String[] {"arprast.qiyosq"});
        emf.setJpaVendorAdapter(
        new HibernateJpaVendorAdapter());
        return emf;
   }
    
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager tm = new JpaTransactionManager(emf() .getObject());
//        tm.setEntityManagerFactory(emf);
        tm.setDataSource(dataSource);
        return tm;
    }*/
	
	
	/*@Autowired
	DataSource dataSource;

	@Autowired
	JpaVendorAdapter jpaVendorAdapter;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan(new String[] { "arprast.qiyosq.model" });
		em.setJpaVendorAdapter(jpaVendorAdapter);
		return em;
	}

	//@Bean
//	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
//		JpaTransactionManager jtManager = new JpaTransactionManager(entityManagerFactory().getObject());
//		jtManager.setEntityManagerFactory(emf);
//		return jtManager;
//	}
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager jtManager = new JpaTransactionManager(entityManagerFactory().getObject());
		jtManager.setEntityManagerFactory(emf);
		return jtManager;
	}
	
	

	@Bean(name="transactionManager")
	@Qualifier("transactionManager")
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory().getObject());
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
	return new DataSourceTransactionManager(dataSource);
	}

//	@Bean
//	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//		return new PersistenceExceptionTranslationPostProcessor();
//	}
*/
}