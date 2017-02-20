package com.asiainfo.demo.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.asiainfo.demo.database.DynamicDataSource;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.nonxa.AtomikosNonXADataSourceBean;

@Configuration
@PropertySource(value = "classpath:db.properties")
@EnableTransactionManagement   //  必须加这个，否则jta事务无效
public class JTAConfiguration {

	@Autowired
	private Environment environment;
	
	@Bean("ds_jta_mysql")
	public DataSource getMysqlAtomikosDataSourceBean() {
		AtomikosNonXADataSourceBean  dataSource = new AtomikosNonXADataSourceBean();

		dataSource.setUniqueResourceName("ds_jta_mysql");
		dataSource.setDriverClassName(environment.getRequiredProperty("mysql.jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("mysql.jdbc.url"));
		dataSource.setUser(environment.getRequiredProperty("mysql.jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("mysql.jdbc.password"));
		
		return dataSource;
	}
	
	
	@Bean("ds_jta_oracle")
	public DataSource getOracleAtomikosDataSourceBean() {
		AtomikosNonXADataSourceBean  dataSource = new AtomikosNonXADataSourceBean();

		dataSource.setUniqueResourceName("ds_jta_oracle");
		dataSource.setDriverClassName(environment.getRequiredProperty("oracle.jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("oracle.jdbc.url"));
		dataSource.setUser(environment.getRequiredProperty("oracle.jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("oracle.jdbc.password"));
		
		return dataSource;
	}
	
	/*
	 * 多数据源配置
	 */
	@Bean("jtaDynamicDataSource")
	public DataSource getDynamicDataSource(){
		
		DynamicDataSource dataSource = new DynamicDataSource();
		Map<Object, Object> sources = new HashMap<Object, Object>();
		sources.put("ds_mysql", getMysqlAtomikosDataSourceBean());
		sources.put("ds_oracle", getOracleAtomikosDataSourceBean());
		dataSource.setTargetDataSources(sources);
		
		// 设置默认数据源
		dataSource.setDefaultTargetDataSource(getMysqlAtomikosDataSourceBean());
		return dataSource;
	}
	
	
	@Bean("jtaSqlSessionFactory")
	public SqlSessionFactory getSqlSessionFactory() throws Exception {
		SqlSessionFactoryBean beanFactory = new SqlSessionFactoryBean();
		beanFactory.setDataSource(getDynamicDataSource());
		
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		beanFactory.setMapperLocations(resolver.getResources("classpath:com/asiainfo/demo/mapper/*.xml"));
		return beanFactory.getObject();
	}
	
	
	@Bean(name = "jtaSqlSession")
	public SqlSession getSqlSession() throws Exception {
		return new SqlSessionTemplate(getSqlSessionFactory());
	}
	
	
	
	/*   jta事务 start    */
	@Bean(name = "jtaTransactionManager")
	@Qualifier("JTA")
	public JtaTransactionManager getJtaTransactionManager() throws SystemException {
		
		JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
		jtaTransactionManager.setUserTransaction(userTransactionImp());
		jtaTransactionManager.setTransactionManager(userTransactionManager());
		jtaTransactionManager.setAllowCustomIsolationLevels(true);
		
		return jtaTransactionManager;
	}
	
	@Bean
	public TransactionManager userTransactionManager() {
		UserTransactionManager userTransManager = new UserTransactionManager();
		userTransManager.setForceShutdown(true);
		return userTransManager;
	}
	
	@Bean("atomikosUserTransaction")
	public UserTransaction userTransactionImp() throws SystemException {
		UserTransactionImp userTransaction = new UserTransactionImp();
		userTransaction.setTransactionTimeout(5000000);
		return userTransaction;
	}
	/*   jta事务 end    */
}
