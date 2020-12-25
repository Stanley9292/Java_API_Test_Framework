import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

import static entities.User.*;

public class TestJsonBodyUserFound extends BaseClass {

    CloseableHttpClient client = HttpClientBuilder.create().build();;
    CloseableHttpResponse response;

    @Test
    public void returnsCorrectUsername() throws IOException{
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/Stanley9292");

        response = client.execute(get);
        String jsonBody = EntityUtils.toString((response.getEntity()));

//        System.out.println(jsonBody);
        JSONObject jsonObject = new JSONObject(jsonBody);

        String loginValue = (String) getValueFor(jsonObject, USERNAME);
        Assert.assertEquals(loginValue, "Stanley9292");
    }

    @Test
    public void returnsCorrectID() throws IOException{
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/Stanley9292");

        response = client.execute(get);
        String jsonBody = EntityUtils.toString((response.getEntity()));
        JSONObject jsonObject = new JSONObject(jsonBody);

        int loginValue = (Integer) getValueFor(jsonObject, ID);
        Assert.assertEquals(loginValue, 17671581);
    }

    private Object getValueFor(JSONObject jsonObject, String key) {
        return jsonObject.get(key);
    }
}
