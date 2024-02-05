package com.example.restwithspringbootandjavaerudio.integrationtests.testcontainers;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.lifecycle.Startable;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.containers.MySQLContainer;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;

@ContextConfiguration(initializers = AbstracIntegrationTests.Initializer.class)
public class AbstracIntegrationTests {
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.34");
    public class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        private static void startContainers(){
            Startables.deepStart((Collection<? extends Startable>) Stream.of(mysql)).join();
        }

        private static Map<String, String> createConnectionConfiguration(){
            return Map.of(
                    "spring.datasource.url", mysql.getJdbcUrl(),
                    "spring.datasource.username", mysql.getUsername(),
                    "spring.datasource.password", mysql.getPassword()


            );
        }
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            startContainers();
            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            MapPropertySource testcontainers = new MapPropertySource(
                    "testcontainers",
                    (Map)createConnectionConfiguration());
            environment.getPropertySources().addFirst(testcontainers);
        }
    }
}
