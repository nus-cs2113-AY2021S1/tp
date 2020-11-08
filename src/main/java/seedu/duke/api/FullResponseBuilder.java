package seedu.duke.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;

public class FullResponseBuilder {
    public static String getFullResponse(HttpURLConnection con) throws IOException {

        Reader streamReader = null;
        if (con.getResponseCode() > 299) {
            streamReader = new InputStreamReader(con.getErrorStream());
        } else {
            streamReader = new InputStreamReader(con.getInputStream(), "utf-8");
        }

        BufferedReader br = new BufferedReader(streamReader);
        String responseLine = null;
        StringBuilder content = new StringBuilder();
        while ((responseLine = br.readLine()) != null) {
            content.append(responseLine);
        }

        br.close();

        StringBuilder fullResponseBuilder = new StringBuilder();

        fullResponseBuilder.append("Response: ")
                .append(content);

        return fullResponseBuilder.toString();
    }
}