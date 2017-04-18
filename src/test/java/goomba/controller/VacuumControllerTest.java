package goomba.controller;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Gino on 16/04/2017.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(VacuumController.class)
public class VacuumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String REST_URL = "/vacuum/";

    private static final String JSON_REQUEST_TEST_1 = getTestJson("vacuumRequest-Test_1.json");
    private static final String JSON_RESPONSE_TEST_1 = getTestJson("vacuumResponse-Test_1.json");
    private static final String JSON_REQUEST_TEST_2 = getTestJson("vacuumRequest-Test_2.json");
    private static final String JSON_RESPONSE_TEST_2 = getTestJson("vacuumResponse-Test_2.json");
    private static final String JSON_REQUEST_TEST_3 = getTestJson("vacuumRequest-Test_3.json");
    private static final String JSON_RESPONSE_TEST_3 = getTestJson("vacuumResponse-Test_3.json");

    private static final String JSON_REQUEST_TEST_4 = getTestJson("invalid_vacuumRequest-Test_4.json");
    private static final String JSON_RESPONSE_TEST_4 = getTestJson("invalid_vacuumResponse-Test_4.json");

    @Test
    public void testVacuum_validJSONRequests_givesCorrectValid_JSONResponse() throws Exception {
        testVacuum(JSON_REQUEST_TEST_1, JSON_RESPONSE_TEST_1);
        testVacuum(JSON_REQUEST_TEST_2, JSON_RESPONSE_TEST_2);
        testVacuum(JSON_REQUEST_TEST_3, JSON_RESPONSE_TEST_3);
    }

    @Test
    public void testVacuum_BadJSONRequest_JSONResponse_IsError() throws Exception {
        testVacuum(JSON_REQUEST_TEST_4, JSON_RESPONSE_TEST_4);
    }

    private void testVacuum(String jsonRequest, String expectedJsonResponse) throws Exception {
        mockMvc.perform(post(REST_URL).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonRequest).accept(MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk()).andExpect(content().string(expectedJsonResponse));
    }

    private static String getTestJson(String path) {
        File file = new File(VacuumControllerTest.class.getClassLoader().getResource(path).getFile());
        try {
            return FileUtils.readFileToString(file);
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}