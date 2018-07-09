package hx.com.example.rule;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author mingliang
 * @Date 2018-04-19 15:01
 */
public class HttpRequest {

    public static String getData(String u) throws Exception {
        String re="";
        URL url = new URL(u);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url
                .openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        InputStream is = httpURLConnection.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(isr);
        String iL = "";
        while ((iL = bufferedReader.readLine()) != null) {
            re += iL + "\n";
        }

        return re;
    }
}
