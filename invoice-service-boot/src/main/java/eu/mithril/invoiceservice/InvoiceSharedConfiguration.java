package eu.mithril.invoiceservice;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InvoiceSharedConfiguration {

    @Bean
    @Conditional(IsOracleDatabaseCondition.class)
    public DataSource customDatasource() {
        return null;
    }
}
