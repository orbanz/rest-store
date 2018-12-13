package com.orbanz.store;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.jayway.restassured.response.Response;
import com.orbanz.store.product.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.jayway.restassured.RestAssured;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RestStoreApplication.class)
@TestPropertySource(value = {"classpath:application.properties"})
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class OrderControllerTest {

    @Test
    public void createProduct() {
        //Given
        Product testProduct = new Product("name", 12);

        //When
        Response response = post("/products", testProduct);

        // Then
        Assert.assertEquals(response.statusCode(), HttpStatus.OK.value());

    }

    @Before
    public void setBaseUri() {
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost"; // replace as appropriate
    }
}

