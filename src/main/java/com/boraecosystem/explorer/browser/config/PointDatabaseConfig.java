package com.boraecosystem.explorer.browser.config;

import com.boraecosystem.explorer.browser.property.ApplicationProperty;
import com.boraecosystem.explorer.browser.property.Channel;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@EnableJpaRepositories(
    entityManagerFactoryRef = "pointEntityManagerFactory",
    transactionManagerRef = "pointTransactionManager",
    basePackages = {"com.boraecosystem.explorer.browser.point.repository"}
)
public class PointDatabaseConfig {

    private final ApplicationProperty property;

    @Autowired
    public PointDatabaseConfig(ApplicationProperty property) {
        this.property = property;
    }

    @Bean(name = "pointDataSource")
    public DataSource dataSource() {
        RoutingDataSource dataSource = new RoutingDataSource();
        Map<Object, Object> param = new HashMap<>();
        for (Channel channel : property.getChannels()) {
            HikariDataSource hikari = new HikariDataSource();
            hikari.setDataSource(channel.getDatasource());
            param.put(channel.getAppId(), hikari);
        }
        dataSource.setTargetDataSources(param);
        return dataSource;
    }

    @Bean(name = "pointEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean pointEntityManagerFactory(
        EntityManagerFactoryBuilder builder, @Qualifier("pointDataSource") DataSource dataSource
    ) {
        return builder
            .dataSource(dataSource)
            .packages("com.boraecosystem.explorer.browser.point.domain")
            .persistenceUnit("point")
            .build();
    }

    @Bean(name = "pointTransactionManager")
    public PlatformTransactionManager pointTransactionManager(
        @Qualifier("pointEntityManagerFactory") EntityManagerFactory pointEntityManagerFactory
    ) {
        return new JpaTransactionManager(pointEntityManagerFactory);
    }
}
