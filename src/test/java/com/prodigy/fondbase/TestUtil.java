package com.prodigy.fondbase;

import com.prodigy.fondbase.controller.json.JsonUtil;
import com.prodigy.fondbase.utils.DownloadUtil;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import java.io.*;
import java.net.URL;

import static com.prodigy.fondbase.controller.json.JsonUtil.writeValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class TestUtil {

    public static String getContent(ResultActions action) throws UnsupportedEncodingException {
        return action.andReturn().getResponse().getContentAsString();
    }

    public static ResultActions print(ResultActions action) throws UnsupportedEncodingException {
        System.out.println(getContent(action));
        return action;
    }

    public static <T> T readFromJson(ResultActions action, Class<T> clazz) throws UnsupportedEncodingException {
        return JsonUtil.readValue(getContent(action), clazz);
    }

    public static <T> ResultMatcher contentJson(T expected) {
        return content().json(writeValue(expected));
    }

    public static <T> ResultMatcher contentJsonArray(T... expected) {
        return contentJson(expected);
    }

    public static BufferedWriter getFileWriter() {

        BufferedWriter writer = null;
        try {
            URL resource = DownloadUtil.class.getResource("/files/analize.txt");
            File file = new File(resource.toURI());

            writer = new BufferedWriter(new FileWriter(file));

        } catch (Exception e) {

        } finally {

        }

        return writer;
    }

}
