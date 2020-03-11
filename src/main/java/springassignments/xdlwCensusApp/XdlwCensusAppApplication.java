package springassignments.xdlwCensusApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"springassignments.xdlwCensusApp.controllers"})
public class XdlwCensusAppApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(XdlwCensusAppApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(XdlwCensusAppApplication.class, args);
	}

}
