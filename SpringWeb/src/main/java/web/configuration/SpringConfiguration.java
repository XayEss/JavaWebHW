package web.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@EnableWebMvc
//@Configuration
//public class SpringConfiguration {
//	
//	@Bean
//	public JdbcTemplate jdbcTemplate() {
//		return new JdbcTemplate(driverManagerDataSource());
//	}
//	
//	@Bean
//	public DataSource driverManagerDataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost/webdb");
//        dataSource.setUsername("root");
//        dataSource.setPassword("");
//		return dataSource;
//	}
//
//}
