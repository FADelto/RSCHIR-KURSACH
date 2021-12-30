package com.example.Schedule.scheduleApi;

import com.example.Schedule.domain.User;
import com.example.Schedule.enums.OddOrEven;
import com.example.Schedule.enums.Weekdays;
import com.example.Schedule.enums.urls;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class API {

    public Vector<Lesson> parseJson(String link, String day) throws JSONException, IOException {
        JSONObject json = API.readJsonFromUrl(link);
        JSONArray lessons = json.getJSONObject("schedule").getJSONObject(day).getJSONArray("lessons");
        Vector<Lesson> lessonsVector = new Vector<Lesson>();
        for (int i =0; i < lessons.length();i++){
            JSONArray lessons1 = json.getJSONObject("schedule").getJSONObject(day).getJSONArray("lessons").getJSONArray(i);
            for (int x =0; x < lessons1.length();x++){
                    Lesson lessonFromJSON = new Lesson(getParamFromJsonArray(lessons, i, x,"name"),
                            getParamFromJsonArray(lessons, i, x,"time_start"),
                            getParamFromJsonArray(lessons, i, x,"time_end"),
                            isWeekOddOrEven(lessons, i, x),
                            Integer.parseInt(day),
                            "");
                    lessonsVector.add(lessonFromJSON);
            }
        }
        return lessonsVector;
    }



    public String getParamFromJsonArray(JSONArray lessons, int i, int x, String param) throws JSONException {
        return lessons.getJSONArray(i).getJSONObject(x).get(param).toString();
    }

    public OddOrEven isWeekOddOrEven(JSONArray lessons, int i, int x) throws JSONException {
        JSONArray lessonDates = (JSONArray) lessons.getJSONArray(i).getJSONObject(x).get("weeks");
        if (lessonDates.getInt(0) % 2 == 1) return OddOrEven.ODD; else
            return OddOrEven.EVEN;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public String getJsonUrl(User user) throws MalformedURLException {
        String group = user.getGroupName();
        String groupEncode = URLEncoder.encode(group);
        URL jsonUrlBuf = new URL(urls.JSON_URL.getUrl()+groupEncode+"/full_schedule");
        return String.valueOf(jsonUrlBuf);
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        } finally {
            is.close();
        }
    }
    public static OddOrEven WeekOddorEven() {
        SimpleDateFormat formater = new SimpleDateFormat("ww");
        Date date = new Date();
        int weekNumber=Integer.parseInt(formater.format(date));
        if ((weekNumber=weekNumber%2) == 0) {
            return OddOrEven.EVEN;
        } else{
            return OddOrEven.ODD;
        }
    }

    public Vector<Lesson> getAllLessons(User user) throws IOException, JSONException {
        Vector<Lesson> allLessons = new Vector<Lesson>();
        for (int i = Weekdays.MONDAY.getDayNumber(); i < Weekdays.SUNDAY.getDayNumber(); i++) {
            allLessons.addAll(parseJson(getJsonUrl(user), i + ""));
        }
        Vector<Lesson> lessons = new Vector<>();
        for (int i = 0; i < allLessons.size(); i++) {
            if ((allLessons.get(i).getOddOrEven().equals(API.WeekOddorEven()))) {
                lessons.add(allLessons.get(i));
            }
        }
        String timeBuf = null;
        for (int i = 0; i < lessons.size(); i++) {
            if ((lessons.get(i).getTimeStart().equals(timeBuf))) {
                lessons.remove(i);
            }
            timeBuf = lessons.get(i).getTimeStart();
        }
        return lessons;
    }
    public Vector<Lesson> getDayLessons(User user) throws IOException, JSONException {
        Vector<Lesson> allLessons = new Vector<Lesson>();
        SimpleDateFormat formater = new SimpleDateFormat("u");
        Date date = new Date();
        int weekday = Integer.parseInt(formater.format((date)));
            allLessons.addAll(parseJson(getJsonUrl(user), weekday + ""));
        Vector<Lesson> lessons = new Vector<>();
        for (int i = 0; i < allLessons.size(); i++) {
            if ((allLessons.get(i).getOddOrEven().equals(API.WeekOddorEven()))) {
                lessons.add(allLessons.get(i));
            }
        }
        String timeBuf = null;
        for (int i = 0; i < lessons.size(); i++) {
            if ((lessons.get(i).getTimeStart().equals(timeBuf))) {
                lessons.remove(i);
            }
            timeBuf = lessons.get(i).getTimeStart();
        }
        return lessons;
    }
    public void updateDatabase() throws IOException {
        JSONObject json = new JSONObject();
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost request = new HttpPost("http://delto.xyz:5000/refresh?secret_key=BadRat2012");
            StringEntity params = new StringEntity(json.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            httpClient.execute(request);
        } catch (Exception ex) {
        } finally {
            httpClient.close();
        }
    }
}
