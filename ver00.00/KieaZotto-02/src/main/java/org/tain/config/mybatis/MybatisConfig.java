package org.tain.config.mybatis;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class MybatisConfig {

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		
		bean.setDataSource(dataSource);
		bean.setConfigLocation(resolver.getResource("classpath:mybatis/_mybatis_config.xml"));
		bean.setMapperLocations(resolver.getResources("classpath:mybatis/mappers/**/*Mapper.xml"));
		bean.setTypeAliasesPackage("org.tain.mybatis.models");
		
		bean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
		bean.getObject().getConfiguration().setCacheEnabled(true);
		bean.getObject().getConfiguration().setCallSettersOnNulls(true);
		bean.getObject().getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
		
		return bean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
