package eu.mithril.invoice_spring_boot_starter;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.vibur.dbcp.ViburDBCPDataSource;
import org.vibur.dbcp.ViburDataSource;

@Configuration
@EnableConfigurationProperties(InvoiceStarterProperties.class)
@ConditionalOnClass(ViburDataSource.class)
@ConditionalOnMissingBean(DataSource.class)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
public class InvoiceDatasourcePoolAutoconfiguration {

    @Bean
    public ViburDBCPDataSource dataSource(InvoiceStarterProperties properties) {
        try (var ds = new ViburDBCPDataSource()) {
            ds.setJdbcUrl(properties.getUrl());
            ds.setUsername(properties.getUsername());
            ds.setPassword(properties.getPassword());
            ds.setDriverClassName(properties.getDriverClassName());
            return ds;
        }
    }
}
