package com.prodigy.fondbase.controller.converter;

import org.springframework.format.Formatter;

import java.awt.*;
import java.text.ParseException;
import java.util.Locale;

public class ColorFormatters {

    public static class ColorFormatter implements Formatter<Color>{
        @Override
        public Color parse(String text, Locale locale) throws ParseException {
            return Color.decode(text);
        }

        @Override
        public String print(Color object, Locale locale) {
            return object.toString();
        }
    }
}
