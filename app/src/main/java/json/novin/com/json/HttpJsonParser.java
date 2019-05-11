package json.novin.com.json;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by NovinDevelopers on 2/10/2018.
 */

public class HttpJsonParser
{
    public static String getJson(String link)
    {
        URL url = null;
        try
        {
            url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("USER-AGENT", "Mozilla/5.0");
            connection.setRequestProperty("ACCEPT-LANGUAGE", "en-US,en;0.5");

            InputStream in = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = "";
            StringBuilder responseOutput = new StringBuilder();
            while ((line = br.readLine()) != null)
            {
                responseOutput.append(line);
            }
            br.close();
            return responseOutput.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return "";
    }
}
