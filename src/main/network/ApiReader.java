package network;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class ApiReader {
    IpaParser ipaParser = new IpaParser();

    public ApiReader(String word) throws IOException, JSONException {
        ProcessBuilder curl = new ProcessBuilder("curl", "--request", "GET",
                "--url", "https://lingua-robot.p.rapidapi.com/language/v1/entries/en/" + word,
                "--header", "x-rapidapi-host: lingua-robot.p.rapidapi.com",
                "--header", "x-rapidapi-key: ff50c5ea27msh10ca8c7622ea3e7p1b8dccjsna64f8ff2028f");

        Process command = curl.start();
        InputStream inputStream = command.getInputStream();
        String data = readSource(inputStream);
        ipaParser.parseData(data);
    }

    public String readSource(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        return sb.toString();
    }

    public String[] getipalist() {
        return ipaParser.ipaList.toArray(new String[0]);
    }
}