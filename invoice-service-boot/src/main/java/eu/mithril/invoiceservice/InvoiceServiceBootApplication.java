package eu.mithril.invoiceservice;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
@ComponentScan("eu.mithril")
public class InvoiceServiceBootApplication {

	@Bean
	public InitializingBean runner(DataSource dataSource) {
		return () -> System.out.println("Running datasource=" + dataSource);
	}

	public static void main(String[] args) {
		SpringApplication.run(InvoiceServiceBootApplication.class, args);
	}

}
