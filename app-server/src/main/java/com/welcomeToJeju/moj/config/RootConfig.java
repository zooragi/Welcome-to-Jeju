package com.welcomeToJeju.moj.config;

import javax.sql.DataSource;

import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(
		basePackages = "com.welcomeToJeju.moj",
		excludeFilters = {
				@Filter(type = FilterType.REGEX, pattern = "com.welcomeToJeju.moj.web.*")
		})

@PropertySource("classpath:com/welcomeToJeju/moj/config/jdbc.properties")
@EnableTransactionManagement
@MapperScan("com.welcomeToJeju.moj.dao")

// 모든 공유 하는 객체들은 여기서 관리해야된다
public class RootConfig {

	@Bean
  public DataSource dataSource(
      @Value("${jdbc.driver}") String jdbcDriver,
      @Value("${jdbc.url}") String jdbcUrl,
      @Value("${jdbc.username}") String jdbcUsername,
      @Value("${jdbc.password}") String jdbcPassword) {


    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName(jdbcDriver);
    ds.setUrl(jdbcUrl);
    ds.setUsername(jdbcUsername);
    ds.setPassword(jdbcPassword);
    return ds;
  }

  // 6) 트랜잭션 관리자 생성
  // => commit/rollback 을 다룬다.
  @Bean 
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  // 7) SqlSessionFactory 객체 생성
  @Bean
  public SqlSessionFactory sqlSessionFactory(
      DataSource dataSource, // DB 커넥션풀
      ApplicationContext appCtx // Spring IoC 컨테이너
      ) throws Exception {

    // Log4J2 기능 활성화시키기
    // => 로그 출력 형식은 .properties 파일이나 .xml 파일로 설정한다.
    LogFactory.useLog4J2Logging();

    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);

    // mybatis 설정 파일을 XML 파일로 따로 두지 말고,
    // 다음과 같이 자바 코드로 설정하면 편하다.
    // 
    sqlSessionFactoryBean.setTypeAliasesPackage("com.welcomeToJeju.moj.domain");
    sqlSessionFactoryBean.setMapperLocations(
        appCtx.getResources("classpath:com/welcomeToJeju/moj/dao/*Dao.xml"));
    return sqlSessionFactoryBean.getObject();
  }
}
