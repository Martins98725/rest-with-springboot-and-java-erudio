package com.example.restwithspringbootandjavaerudio.integrationtests.swagger;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import com.example.restwithspringbootandjavaerudio.config.TestConfigs;
import com.example.restwithspringbootandjavaerudio.integrationtests.testcontainers.AbstracIntegrationTests;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationsTest extends AbstracIntegrationTests {
    @Test
    public void shoudDisplaySwaggerUIPage(){
        var content = given().basePath("/swagger-ui/index.html#/").port(TestConfigs.SERVER_PORT)
                .when().get()
                .then().statusCode(200)
                .extract().body().asString();

        assertTrue(content.contains("Swagger UI"));
    }
}
