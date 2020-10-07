package id.sna.crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import id.sna.crud.dao.*;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages="id.sna.crud")
@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer {
	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Bean
	public DataSource getDataSource() {
		try {
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
	       	dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	       	dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/contactdb");
	       	dataSource.setUsername("root");
	       	dataSource.setPassword("");
	 
	       	System.out.println("## getDataSource: " + dataSource);
	       	return dataSource;

		} catch (Exception e) {
            System.err.println("Failed to load JDBC driver.");
        	return null;
        }
 
	}
	
	@Bean
	public CustomerDAOInterface getCustomerDAO() {
		return new CustomerDAO(getDataSource());
	}
	
}
