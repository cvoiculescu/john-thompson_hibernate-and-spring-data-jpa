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
        return flyway(cardFlywayDataSourceProps, "card");
    }

    @Bean
    @ConfigurationProperties("spring.cardholder.flyway")
    public DataSourceProperties cardHolderFlywayDataSourceProps() {
        return new DataSourceProperties();
    }

    @Bean(initMethod = "migrate")
    public Flyway cardHolderFlyway(@Qualifier("cardHolderFlywayDataSourceProps") DataSourceProperties cardHolderFlywayDataSourceProps) {
        return flyway(cardHolderFlywayDataSourceProps, "cardholder");
    }

    @Bean
    @ConfigurationProperties("spring.pan.flyway")
    public DataSourceProperties panFlywayDataSourceProps() {
        return new DataSourceProperties();
    }

    @Bean(initMethod = "migrate")
    public Flyway panFlyway(@Qualifier("panFlywayDataSourceProps") DataSourceProperties panFlywayDataSourceProps) {
        return flyway(panFlywayDataSourceProps, "pan");
    }

    private Flyway flyway(DataSourceProperties dsp, String location) {
        return Flyway.configure()
                .dataSource(dsp.getUrl(), dsp.getUsername(), dsp.getPassword())
                .locations(String.format("classpath:/db/migrations/%s", location))
                .load();
    }

}
