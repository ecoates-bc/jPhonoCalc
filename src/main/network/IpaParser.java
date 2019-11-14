package network;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class IpaParser {
    public String ipa;
    public List<String> ipaList;

    public void parseData(String data) throws JSONException {
        ipaList = new ArrayList<>();
        JSONObject wordData = new JSONObject(data);
        JSONArray entry = wordData.getJSONArray("entries");
        JSONObject entryObject = entry.getJSONObject(0);
        JSONArray pronunciations = entryObject.getJSONArray("pronunciations");
        JSONObject transcriptions = pronunciations.getJSONObject(0);
        JSONArray transArray = transcriptions.getJSONArray("transcriptions");
        JSONObject transcription = transArray.getJSONObject(0);

        ipa = transcription.getString("transcription");
        parseIPA();
    }

    public void parseIPA() {
        char[] ipaListChars = ipa.toCharArray();
        for (char c: ipaListChars) {
            if (c != '/' && c != 'ˈ') {
                ipaList.add(String.valueOf(c));
            }
        }
    }
}
