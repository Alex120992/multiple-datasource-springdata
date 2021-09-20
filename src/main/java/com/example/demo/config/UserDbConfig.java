package com.example.demo.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryRef", basePackages = {"com.example.demo.dao.user"},
        transactionManagerRef = "userTransactionManager"
)
public class UserDbConfig {

    @Primary
    @Bean(name = "userDb")
    @ConfigurationProperties(prefix = "spring.user.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "entityManagerFactoryRef")
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder, @Qualifier("userDb") DataSource dataSource) {
        Map<String,Object> map = new HashMap<>();
//        map.put("hibernate.hbm2ddl.auto", "update");
        map.put("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
        return builder.dataSource(dataSource).properties(map).packages("com.example.demo.model.user").persistenceUnit("user").build();
    }

    @Primary
    @Bean(name = "userTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactoryRef")
                                                                 EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);

    }

    @Bean("liquibase")
    @DependsOn("userDb")
    @Primary
    public SpringLiquibase springLiquibase(@Qualifier("userDb") DataSource dataSource){
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(dataSource);
        springLiquibase.setChangeLog("classpath:db.changelog/db.changelog-master.xml");
        return springLiquibase;
    }

}
