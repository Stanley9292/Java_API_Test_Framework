import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;

import static org.testng.Assert.*;

public class get401 extends BaseClass {

    CloseableHttpClient client = HttpClientBuilder.create().build();;
    CloseableHttpResponse response;

    @BeforeMethod
    public void setup(){

        client = HttpClientBuilder.create().build();
    }

    @AfterMethod
    public void closeResources() throws IOException {
        client.close();
        response.close();
    }

    @DataProvider
    private Object[][] endpoints(){
        return new Object[][]{
                {"/user"},
                {"/user/followers"},
                {"/notifications"}
        };
    }


    @Test(dataProvider =  "endpoints")
    public void userReturns401(String endpoint) throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + endpoint);

        response = client.execute(get);

        response.getStatusLine().getStatusCode();
        int actualStatus = response.getStatusLine().getStatusCode();
        assertEquals(actualStatus, 401);

    }

//    @Test
//    public void userFollowersReturns401() throws IOException {
//        HttpGet get = new HttpGet(BASE_ENDPOINT + "/user/followers");
//
//        response = client.execute(get);
//
//        response.getStatusLine().getStatusCode();
//        int actualStatus = response.getStatusLine().getStatusCode();
//        assertEquals(actualStatus, 401);
//
//    }
//    @Test
//    public void NotificationsReturns401() throws IOException {
//        HttpGet get = new HttpGet(BASE_ENDPOINT + "/notifications");
//
//        response = client.execute(get);
//
//        response.getStatusLine().getStatusCode();
//        int actualStatus = response.getStatusLine().getStatusCode();
//        assertEquals(actualStatus, 401);
//
//    }

}
