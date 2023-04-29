package com.spdx.hms.v1.component;

import com.spdx.hms.HmsApplication;
import com.spdx.hms.util.RestUtility;
import com.spdx.hms.v1.model.inbound.request.StudentSaveRequest;
import com.spdx.hms.v1.util.FileHelper;
import org.json.JSONException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.net.URISyntaxException;
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = HmsApplication.class)
//@ActiveProfiles(profiles = "test")
public class StudentComponentTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private static final String FILE_PATH = "src/test/java/com/spdx/hms/v1/component/data/";
    @DisplayName("Test Save Student failure")
    @Test
    public void testSaveStudentFailure() throws JSONException, IOException, URISyntaxException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, RestUtility.APPLICATION_API_SPIDEX_V1_JSON);
        httpHeaders.add(HttpHeaders.ACCEPT, RestUtility.APPLICATION_API_SPIDEX_V1_JSON);
        ResponseEntity<String> studentResponse = restTemplate.exchange("/api/hms/students/", HttpMethod.POST,
                                                                       new HttpEntity<>(
                                                                               new StudentSaveRequest(),
                                                                               httpHeaders),
                                                                       String.class);

        String expectedJson = FileHelper.readFile(FILE_PATH , "studentsavefailureresponse.json");
        JSONAssert.assertEquals(expectedJson, studentResponse.getBody(), JSONCompareMode.LENIENT);
    }

}
