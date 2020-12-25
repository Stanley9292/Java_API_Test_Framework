import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.NotFound;
import entities.User;
import jdk.nashorn.api.scripting.NashornScriptEngine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;

import static entities.User.*;

public class BodyTestWithJackson extends BaseClass {

    CloseableHttpClient client = HttpClientBuilder.create().build();;
    CloseableHttpResponse response;

    @Test
    public void returnsCorrectLogin() throws IOException{
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/Stanley9292");

        response = client.execute(get);

        unmarshall(response, User.class);
        User user = unmarshall(response, User.class);
        Assert.assertEquals(user.getLogin(), "Stanley9292");

    }

    @Test
    public void returnsCorrectID() throws IOException{
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/Stanley9292");

        response = client.execute(get);

        unmarshall(response, User.class);
        User user = unmarshall(response, User.class);
        Assert.assertEquals(user.getId(), 17671581);

    }

//    @Test
//    public void notFoundMessageIsCorrect() throws IOException{
//        HttpGet get = new HttpGet(BASE_ENDPOINT + "/nonexistingendpoint");
//
//        response = client.execute(get);
//
//        unmarshall(response, User.class);
//        NotFound notFoundMessage = unmarshall(response, NotFound.class);
////        Assert.assertEquals(user.getId(), 17671581);
//
//    }

    private User unmarshall(CloseableHttpResponse response, Class<User> clazz) throws IOException {
        String jsonBody = EntityUtils.toString(response.getEntity());

        JsonFactory jsonFactory = new JsonFactory();
        jsonFactory.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
        jsonFactory.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, false);
        ObjectMapper mapper = new ObjectMapper(jsonFactory);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        final User user = mapper
                .readValue(jsonBody, clazz);
        return user;
    }

//    private User <T> T unmarshallGeneric(CloseableHttpResponse response, Class<T> clazz) throws IOException {
//        String jsonBody = EntityUtils.toString(response.getEntity());
//
//        JsonFactory jsonFactory = new JsonFactory();
//        jsonFactory.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET,true);
//        ObjectMapper mapper = new ObjectMapper(jsonFactory);
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        final User user = mapper
//                .readValue(jsonBody, clazz);
//        return user;
//    }
}
