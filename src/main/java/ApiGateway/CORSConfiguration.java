
package ApiGateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class CORSConfiguration implements WebFluxConfigurer {
    public CORSConfiguration() {
    }

    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("Bla bla");
        registry.addMapping("/**").allowCredentials(false).allowedOrigins(new String[]{"http://localhost:4200"}).allowedHeaders(new String[]{"*"}).allowedMethods(new String[]{"*"}).exposedHeaders(new String[]{"Set-Cookie"});
    }

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(false);
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedOrigin("http://localhost:4200");
        corsConfiguration.addExposedHeader("Set-Cookie");
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsWebFilter(corsConfigurationSource);
    }
}
