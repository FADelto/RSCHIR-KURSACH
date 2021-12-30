package com.example.Schedule.controller;

import com.example.Schedule.domain.User;
import com.example.Schedule.scheduleApi.API;
import com.example.Schedule.scheduleApi.Lesson;
import org.json.JSONException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.Vector;

@Controller
public class MainController {

    @GetMapping("/")
    public String index()
    {
        return "index";
    }

    @GetMapping("/weekSchedule")
        public String weekSchedule(@AuthenticationPrincipal User user, @RequestParam(required = false, defaultValue = "") String filter, Map<String,Object> model) throws JSONException, IOException
        {
            API buf = new API();
            Vector<Lesson> lessons = buf.getAllLessons(user);
            model.put("lessons", lessons);
            model.put("filter",filter);
            return "weekSchedule";
        }

    @GetMapping("/daySchedule")
    public String daySchedule(@AuthenticationPrincipal User user,  @RequestParam(required = false, defaultValue = "") String filter, Map<String,Object> model) throws JSONException, IOException {
        API buf = new API();
        Vector<Lesson> lessons = buf.getDayLessons(user);
        model.put("lessons", lessons);
        model.put("filter",filter);
        return "daySchedule";
    }

    @GetMapping("/update")
    public String updateDatabase() throws JSONException, IOException {
        API buf = new API();
        buf.updateDatabase();
        return "index";
    }

    @GetMapping("/error")
    public String error(){
        return "error";
    }
}