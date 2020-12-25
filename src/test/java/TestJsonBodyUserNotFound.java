import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

import static entities.User.DOCUMENTATION_URL;
import static entities.User.MESSAGE;

public class TestJsonBodyUserNotFound extends BaseClass {

    CloseableHttpClient client = HttpClientBuilder.create().build();;
    CloseableHttpResponse response;

    @Test
    public void returnsCorrectMessage() throws IOException{
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/alexandru_stan");

        response = client.execute(get);
        String jsonBody = EntityUtils.toString((response.getEntity()));
        JSONObject jsonObject = new JSONObject(jsonBody);

        String messageValue = (String) getValueFor(jsonObject, MESSAGE);
        Assert.assertEquals(messageValue, "Not Found");
    }

    @Test
    public void returnsCorrectDocumentation() throws IOException{
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/alexandru_stan");

        response = client.execute(get);
        String jsonBody = EntityUtils.toString((response.getEntity()));
        JSONObject jsonObject = new JSONObject(jsonBody);

        String docValue = (String) getValueFor(jsonObject, DOCUMENTATION_URL);
        Assert.assertEquals(docValue, "https://developer.github.com/v3/users/#get-a-single-user");
    }

    private Object getValueFor(JSONObject jsonObject, String key) {
        return jsonObject.get(key);
    }
}
