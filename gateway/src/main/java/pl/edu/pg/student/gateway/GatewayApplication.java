package pl.edu.pg.student.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class GatewayApplication {

	private final DiscoveryClient discoveryClient;

	@Autowired
	public GatewayApplication(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		ServiceInstance professor = discoveryClient.getInstances("professor").stream()
				.findFirst()
				.orElseThrow();

		ServiceInstance university = discoveryClient.getInstances("university").stream()
				.findFirst()
				.orElseThrow();

		return builder
				.routes()
				.route("universities", r -> r
						.host("localhost:8080")
						.and()
						.path("/api/universities/{name}", "/api/universities")
						.uri(university.getUri()))
				.route("professors", r -> r
						.host("localhost:8080")
						.and()
						.path("/api/professors", "/api/professors/**", "/api/universities/professors", "/api/universities/{name}/professors", "/api/universities/{name}/professors/**")
						.uri(professor.getUri()))
				.build();
	}

	@Bean
	public CorsWebFilter corsWebFilter() {

		final CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Collections.singletonList("*"));
		corsConfig.setMaxAge(3600L);
		corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
		corsConfig.addAllowedHeader("*");

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);

		return new CorsWebFilter(source);
	}
}
