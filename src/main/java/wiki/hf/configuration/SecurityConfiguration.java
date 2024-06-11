package wiki.hf.configuration;

import org.springframework.core.annotation.*;
import org.springframework.context.annotation.*;

import org.springframework.security.config.*;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.config.http.*;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.web.cors.*;
import org.springframework.security.oauth2.jwt.*;

import com.nimbusds.jose.*;
import com.nimbusds.jose.jwk.*;
import com.nimbusds.jose.jwk.source.*;
import com.nimbusds.jose.proc.SecurityContext;

import lombok.*;

import java.util.List;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@RequiredArgsConstructor

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(User.withUsername("username")
                                                 .password("{noop}password")
                                                 .roles("ADMINISTRATOR")
                                                 .authorities("READ")
                                                 .build());
    }

    @Bean
    @Order(1)
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/api/**")
            .csrf(AbstractHttpConfigurer::disable)
            .httpBasic(Customizer.withDefaults())
            .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorizer -> authorizer.requestMatchers("/error").permitAll().anyRequest().authenticated())
            .oauth2ResourceServer(oauth2Configurer -> oauth2Configurer.jwt(Customizer.withDefaults()))
            .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                                                                     .accessDeniedHandler(new BearerTokenAccessDeniedHandler()));

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizer -> authorizer.requestMatchers("/account/login").permitAll()
                                                       .requestMatchers("/account/register").permitAll()
                                                       .requestMatchers("/account/management").authenticated()
                                                       .requestMatchers("/page/**").permitAll()
                                                       .anyRequest().authenticated())
            .csrf(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults())
            .formLogin(formLogin -> formLogin.defaultSuccessUrl("/account/management", true));

        return http.build();
    }

    @Bean
    JWK jwk() {
        KeyPair keyPair = KeyGenerator.generateRSAKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        return new RSAKey.Builder(publicKey).privateKey(privateKey).keyID("selfsigned").build();
    }

    @Bean
    JwtDecoder jwtDecoder(JWK jwk) throws JOSEException {
        return NimbusJwtDecoder.withPublicKey(jwk.toRSAKey().toRSAPublicKey()).build();
    }

    @Bean
    JwtEncoder jwtEncoder(JWK jwk) {
        return new NimbusJwtEncoder(new ImmutableJWKSet<>(new JWKSet(jwk)));
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configurationSource = new CorsConfiguration();

        configurationSource.setAllowedHeaders(List.of("http://localhost:8080"));
        configurationSource.setAllowedHeaders(List.of("*"));
        configurationSource.setAllowedHeaders(List.of("GET"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configurationSource);

        return source;
    }
}