package com.koreaIT.java.BAM.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
       public static String getDate() {
    			LocalDateTime now = LocalDateTime.now();
    			String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
    			return formatedNow;
        }     
}