package okhttp;

import dto.ContactDTO;
import dto.ContactListDTO;
import dto.ContactResponseDTO;
import helpers.Helper;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddNewContactTests implements Helper {

    String endpoint = "/v1/contacts";

    @Test
    public void addNewContactPositive() throws IOException {

        ContactDTO contactDTO = ContactDTO.builder()
                .name("Group39")
                .lastName("Automations")
                .email("grou_" + i + "@mail.com")
                .phone("987654" + i)
                .address("Tel Aviv")
                .description("Students")
                .build();


        RequestBody requestBody = RequestBody.create(gson.toJson(contactDTO), JSON);

        Request request = new Request.Builder()
                .url(baseURL + endpoint)
                .addHeader("Authorization", token)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        ContactResponseDTO contactResponseDTO = gson.fromJson(response.body().string(), ContactResponseDTO.class);

        String message = contactResponseDTO.getMessage();
        System.out.println(message);

        String id = message.substring(message.lastIndexOf(" ") + 1);

System.out.println(id);
    }

}
