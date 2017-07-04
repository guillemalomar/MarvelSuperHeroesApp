import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Test {
    public static void main(String[] args) throws ClientProtocolException {
        try {
            URL requestUrl = new URL("http://developer.marvel.com");
            URLConnection con = requestUrl.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb=new StringBuilder();
            int cp;
            while ((cp = in.read()) != -1) {
                sb.append((char) cp);
            }
            String json=sb.toString();
            System.out.println(json);
        }catch (MalformedURLException e){
        }catch(Exception e){
            throw new ClientProtocolException();
        }
    }
}