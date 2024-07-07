package resources;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    public static RequestSpecification request;
    public RequestSpecification requestSpecification() throws IOException {

        if(request == null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            request = new RequestSpecBuilder().setBaseUri(getGlobalValues("baseUrl")).addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return request;
        }
        return request;

    }

    public static String getGlobalValues(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\mkhapali\\IdeaProjects\\restAssuredCucumber\\src\\test\\java\\resources\\global.properties");
        prop.load(fis);

        return prop.getProperty(key);

    }


}
