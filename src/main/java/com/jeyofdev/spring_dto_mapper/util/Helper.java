package com.jeyofdev.spring_dto_mapper.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {
    public static String simpleDateFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(new Date());
    }
}
