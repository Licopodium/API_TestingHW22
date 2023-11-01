package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.parsing.Parser;
import dto.ContactDTO;
import helpers.Helper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class DeleteContactByIDTests implements Helper {

    ContactDTO contactDTO;
    String id;
    String endpoint = "/v1/contacts";

    @BeforeMethod
    public void precondition(){

        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";


        contactDTO = ContactDTO.builder()
                .name("Group39")
                .lastName("Automations")
                .email("grou_" + i + "@mail.com")
                .phone("987654" + i)
                .address("Tel Aviv")
                .description("Students")
                .build();

        String message = given()
                .header(authHeader, token)
                .body(contactDTO)
                .contentType(ContentType.JSON)
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .path("message");

        id = message.substring(message.lastIndexOf(" ") + 1);
    }

    @Test
    public void deleteContactByIDTestsPositive(){
        contactDTO.setId(id);


        given()
                .header(authHeader, token)
                .body(contactDTO)
                .contentType(ContentType.JSON)
                .when()
                .delete(endpoint)
                .then()
                .assertThat().statusCode(200);
               // .assertThat().body("message", containsString("Contact deleted successfully "));
    }

}
