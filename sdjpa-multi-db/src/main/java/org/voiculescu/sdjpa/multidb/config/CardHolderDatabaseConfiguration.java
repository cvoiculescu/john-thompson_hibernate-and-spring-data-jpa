package org.voiculescu.sdjpa.multidb.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.voiculescu.sdjpa.multidb.domain.cardholder.CreditCardHolder;

import javax.sql.DataSource;
import java.util.Objects;

@EnableJpaRepositories(
        basePackages = "org.voiculescu.sdjpa.multidb.repositories.cardholder",
        entityManagerFactoryRef = "cardHolderEntityManagerFactory",
        transactionManagerRef = "cardHolderTransactionManager"
)
@Configuration
public class CardHolderDatabaseConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "spring.cardholder.datasource")
    public DataSourceProperties cardHolderDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    DataSource cardHolderDataSource(@Qualifier("cardHolderDataSourceProperties") DataSourceProperties cardHolderDataSourceProperties) {
        return cardHolderDataSourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean cardHolderEntityManagerFactory(
            @Qualifier("cardHolderDataSource") DataSource cardHolderDataSource,
            EntityManagerFactoryBuilder builder) {
        return builder.dataSource(cardHolderDataSource)
                .packages(CreditCardHolder.class)
                .persistenceUnit("cardholder")
                .build();
    }

    @Bean
    PlatformTransactionManager cardHolderTransactionManager(
            @Qualifier("cardHolderEntityManagerFactory") LocalContainerEntityManagerFactoryBean cardHolderEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(cardHolderEntityManagerFactory.getObject()));
    }
}
