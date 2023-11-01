package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import helpers.Helper;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.given;

public class RegistrationTests implements Helper{

    String endpoint = "/v1/user/registration/usernamepassword";

    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
    }


    @Test
    public void registrationPositive() {

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("coral_" + i + "@gmail.com")
                .password("565656Ca$")
                .build();


        AuthResponseDTO responseDTO = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(AuthResponseDTO.class);

        System.out.println(responseDTO.getToken());

    }

    @Test
    public void registrationNegativeWrongEmail() {

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("c@oral_" + i + "@gmail.com")
                .password("565656Ca$")
                .build();

        ErrorDTO errorDTO = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(400)
                .extract()
                .as(ErrorDTO.class);

        System.out.println(errorDTO.getMessage());

    }

    @Test
    public void registrationNegativeDuplicateUser() {

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("coral@gmail.com")
                .password("565656Ca$")
                .build();

        ErrorDTO errorDTO = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(409)
                .extract()
                .as(ErrorDTO.class);

        System.out.println(errorDTO.getMessage());

    }

    }




