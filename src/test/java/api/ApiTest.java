package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiTest {
    @Test
    public void testGetMethod() {
        Response response = RestAssured.get("https://postman-echo.com/get?foo1=bar1&foo2=bar2");

        assertEquals(200, response.getStatusCode());

        assertEquals("bar1", response.jsonPath().getString("args.foo1"));
        assertEquals("bar2", response.jsonPath().getString("args.foo2"));
    }

    @Test
    public void testDeleteMethod() {
        Response response = RestAssured.delete("https://postman-echo.com/delete");
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testPostMethod() {
        String jsonBody = "{ \"foo1\": \"bar1\", \"foo2\": \"bar2\" }";

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
                .body(jsonBody)
                .post("https://postman-echo.com/post");

        assertEquals(200, response.getStatusCode());

        assertEquals("bar1", response.jsonPath().getString("json.foo1"));
        assertEquals("bar2", response.jsonPath().getString("json.foo2"));
    }

    @Test
    public void testPutMethod() {
        String jsonBody = "{ \"foo1\": \"bar1\", \"foo2\": \"bar2\" }";
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
                .body(jsonBody)
                .put("https://postman-echo.com/put");

        assertEquals(200, response.getStatusCode());

        assertEquals("bar1", response.jsonPath().getString("json.foo1"));
        assertEquals("bar2", response.jsonPath().getString("json.foo2"));
    }

    @Test
    public void testPatchMethod() {
        String jsonBody = "{ \"foo1\": \"bar1\", \"foo2\": \"bar2\" }";
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
                .body(jsonBody)
                .patch("https://postman-echo.com/patch");

        assertEquals(200, response.getStatusCode());

        assertEquals("bar1", response.jsonPath().getString("json.foo1"));
        assertEquals("bar2", response.jsonPath().getString("json.foo2"));
    }
}