/*package com.example.restwithspringbootandjavaerudio.integrationtests.testcontainers;
import com.example.restwithspringbootandjavaerudio.config.MySQLContainer;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.lifecycle.Startable;
import org.testcontainers.lifecycle.Startables;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;

@ContextConfiguration(initializers = AbstracIntegrationTests.Initializer.class)
public class AbstracIntegrationTests {
   *//* public class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        static MySQLContainer<?> mysql = new MySQLContainer<>();
        private static void startContainers(){
            Startables.deepStart((Collection<? extends Startable>) Stream.of(mysql)).join();
        }
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            startContainers();
            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            MapPropertySource testContainer = new MapPropertySource(
                    "testcontainer", (java.util.Map<String, Object>) createConnectionConfiguration()
            );
            environment.getPropertySources().addFirst(testContainer);
        }

        private java.util.Map createConnectionConfiguration() {
            return Map.of(
                    "", mysql.getJdbcUrl();
            );
        }
    }*//*
}*/
