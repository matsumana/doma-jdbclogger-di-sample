package info.matsumana;

import info.matsumana.logger.MyJdbcLogger;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.DomaAbstractConfig;
import org.seasar.doma.jdbc.JdbcLogger;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.H2Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:/database/schema.sql")
                .addScript("classpath:/database/dataload.sql").build();
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public Dialect dialect() {
        return new H2Dialect();
    }

    @Bean
    public JdbcLogger getMyJdbcLogger() {
        return new MyJdbcLogger();
    }

    @Bean
    public Config domaConfig() {
        return new DomaAbstractConfig() {
            @Override
            public Dialect getDialect() {
                return dialect();
            }

            @Override
            public DataSource getDataSource() {
                return new TransactionAwareDataSourceProxy(dataSource());
            }

            @Override
            public JdbcLogger getJdbcLogger() {
                return getMyJdbcLogger();
            }
        };
    }
}
