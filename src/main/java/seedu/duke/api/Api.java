package seedu.duke.api;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Api {

    private static final String API_KEY = "O8EVQ7YSWDW08BN9";
    public static final String SYMBOL = "IBM";


    public Api() throws IOException {

        StringBuilder request = new StringBuilder();
        request.append("https://www.alphavantage.co/query?");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("symbol", SYMBOL);
        parameters.put("function", "TIME_SERIES_DAILY_ADJUSTED");

        String paramsToAppend = buildParamsString(parameters);

        request = request.append(paramsToAppend);

        // Creating a Request
        URL url = new URL(request.toString());
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;
        http.setRequestMethod("GET");

        // Prints response from API call
        String response = FullResponseBuilder.getFullResponse(http);
        // System.out.println(response);

    }

    // Builds a string to append relevant the params to append to URL
    public static String buildParamsString(Map<String, String> params)
            throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        result.append("apikey=" + API_KEY);

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }

}
