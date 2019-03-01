package self.ed.springbootatmosphere;

import org.atmosphere.cpr.AtmosphereServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootAtmosphereApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAtmosphereApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean<AtmosphereServlet> atmosphereServlet() {
        // "ws://localhost:8080/test"
        ServletRegistrationBean<AtmosphereServlet> servlet = new ServletRegistrationBean<>(new AtmosphereServlet(), "/test/*");
        servlet.addInitParameter("org.atmosphere.interceptor.HeartbeatInterceptor.clientHeartbeatFrequencyInSeconds", "1");
        return servlet;
    }
}
