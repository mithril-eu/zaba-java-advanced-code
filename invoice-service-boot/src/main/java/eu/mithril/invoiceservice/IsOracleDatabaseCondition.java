package eu.mithril.invoiceservice;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class IsOracleDatabaseCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return oracleJdbcOnClasspath() && databaseUrlSet(context);
    }

    private boolean databaseUrlSet(ConditionContext context) {
        System.out.println("database url check");
        return context.getEnvironment().containsProperty("spring.datasource.url");
    }

    private boolean oracleJdbcOnClasspath() {
        try {
            System.out.println("classpath check");
            Class.forName("oracle.jdbc.OracleDriver");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }


}
