package springassignments.xdlwCensusApp.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jca.endpoint.GenericMessageEndpointFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springassignments.xdlwCensusApp.XDLWGeographyDAL.XDLWGeoDAO;
import springassignments.xdlwCensusApp.XDLWGeographyDAL.XDLWGeoDAOImpl;

import java.sql.Driver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "springassignments.xdlwCensusApp.controllers", "springassignments.xdlwCensusApp.XDLWGeographyDAL"})
public class WebMvcConfig {
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver vr = new InternalResourceViewResolver();
        vr.setPrefix("/");
        vr.setSuffix(".jsp");

        return vr;
    }

    @Bean
    public DriverManagerDataSource getDataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/censusdb");
        ds.setUsername("root");
        ds.setPassword("");

        return ds;
    }

    @Bean
    public XDLWGeoDAO getXDLWGeoDAO() {
        return new XDLWGeoDAOImpl(getDataSource());
    }

}
