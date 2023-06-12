package org.voiculescu.sdjpa.multidb.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.voiculescu.sdjpa.multidb.domain.cardholder.CreditCardHolder;
import org.voiculescu.sdjpa.multidb.domain.pan.CreditCardPAN;

import javax.sql.DataSource;
import java.util.Objects;

@EnableJpaRepositories(
        basePackages = "org.voiculescu.sdjpa.multidb.repositories.pan",
        entityManagerFactoryRef = "panEntityManagerFactory",
        transactionManagerRef = "panTransactionManager"
)
@Configuration
public class PanDatabaseConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("spring.pan.datasource")
    public DataSourceProperties panDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.pan.datasource.hikari")
    DataSource panDataSource(@Qualifier("panDataSourceProperties") DataSourceProperties panDataSourceProperties) {
        return panDataSourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean panEntityManagerFactory(
            @Qualifier("panDataSource") DataSource panEntityManagerFactory,
            EntityManagerFactoryBuilder builder) {
        return builder.dataSource(panEntityManagerFactory)
                .packages(CreditCardPAN.class)
                .persistenceUnit("pan")
                .build();
    }

    @Bean
    @Primary
    PlatformTransactionManager panTransactionManager(
            @Qualifier("panEntityManagerFactory") LocalContainerEntityManagerFactoryBean panEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(panEntityManagerFactory.getObject()));
    }
}
