package com.asiainfo.demo.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.asiainfo.demo.database.DynamicDataSource;



/**
 * mybatis 配置类
 * @author liqingguo
 *
 */
@Configuration
@PropertySource(value = "classpath:db.properties")
@MapperScan("com.asiainfo.demo.mapper") //  这个配置貌似没用
@ComponentScan("com.asiainfo.demo.*")
@EnableAspectJAutoProxy
public class MybatisConfiguration {
	
	@Autowired
	private Environment environment;
	
	
	
	/*
	 * 数据源配置
	 */
	@Bean(name = "ds_mysql")
	public DataSource mysqlDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("mysql.jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("mysql.jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("mysql.jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("mysql.jdbc.password"));
		dataSource.setDefaultAutoCommit(false);
		dataSource.setEnableAutoCommitOnReturn(true);
		return dataSource;
	}
	
	
	@Bean(name = "ds_oracle")
	public DataSource oracleDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("oracle.jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("oracle.jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("oracle.jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("oracle.jdbc.password"));
		dataSource.setDefaultAutoCommit(false);
		dataSource.setEnableAutoCommitOnReturn(true);
		return dataSource;
	}
	
	
	/*
	 * 多数据源配置
	 */
	@Bean("dynamicDataSource")
	public DataSource getDynamicDataSource(){
		
		DynamicDataSource dataSource = new DynamicDataSource();
		Map<Object, Object> sources = new HashMap<Object, Object>();
		sources.put("ds_mysql", mysqlDataSource());
		sources.put("ds_oracle", oracleDataSource());
		dataSource.setTargetDataSources(sources);
		
		// 设置默认数据源
		dataSource.setDefaultTargetDataSource(mysqlDataSource());
		return dataSource;
	}
	
	
	
	@Bean
	public SqlSessionFactory getSqlSessionFactory() throws Exception {
		SqlSessionFactoryBean beanFactory = new SqlSessionFactoryBean();
		beanFactory.setDataSource(getDynamicDataSource());
		
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		beanFactory.setMapperLocations(resolver.getResources("classpath:com/asiainfo/demo/mapper/*.xml"));
		return beanFactory.getObject();
	}
	
	
	@Bean(name = "sqlSession")
	public SqlSession getSqlSession() throws Exception {
		return new SqlSessionTemplate(getSqlSessionFactory());
	}

}
