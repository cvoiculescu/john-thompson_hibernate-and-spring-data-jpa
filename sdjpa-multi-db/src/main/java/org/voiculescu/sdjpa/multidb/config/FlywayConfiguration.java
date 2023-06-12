package org.voiculescu.sdjpa.multidb.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfiguration {

    @Bean
    @ConfigurationProperties("spring.card.flyway")
    public DataSourceProperties cardFlywayDataSourceProps() {
        return new DataSourceProperties();
    }

    @Bean(initMethod = "migrate")
    public Flyway cardFlyway(@Qualifier("cardFlywayDataSourceProps") DataSourceProperties cardFlywayDataSourceProps) {
        return Flyway.configure()
                .dataSource(cardFlywayDataSourceProps.getUrl(), cardFlywayDataSourceProps.getUsername(), cardFlywayDataSourceProps.getPassword())
                .locations("classpath:db/migrations/card")
                .load();
    }

    @Bean
    @ConfigurationProperties("spring.cardholder.flyway")
    public DataSourceProperties cardHolderFlywayDataSourceProps() {
        return new DataSourceProperties();
    }

    @Bean(initMethod = "migrate")
    public Flyway cardHolderFlyway(@Qualifier("cardHolderFlywayDataSourceProps") DataSourceProperties cardHolderFlywayDataSourceProps) {
        return Flyway.configure()
                .dataSource(cardHolderFlywayDataSourceProps.getUrl(), cardHolderFlywayDataSourceProps.getUsername(), cardHolderFlywayDataSourceProps.getPassword())
                .locations("classpath:db/migrations/cardholder")
                .load();
    }

    @Bean
    @ConfigurationProperties("spring.pan.flyway")
    public DataSourceProperties panFlywayDataSourceProps() {
        return new DataSourceProperties();
    }

    @Bean(initMethod = "migrate")
    public Flyway panFlyway(@Qualifier("panFlywayDataSourceProps") DataSourceProperties panFlywayDataSourceProps) {
        return Flyway.configure()
                .dataSource(panFlywayDataSourceProps.getUrl(), panFlywayDataSourceProps.getUsername(), panFlywayDataSourceProps.getPassword())
                .locations("classpath:db/migrations/pan")
                .load();
    }

}
