package com.common.io;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * @date: 2020-07-18
 * @author: fanhaodong
 */
public class TimeOut {
    public static void main(String[] args) throws IOException, ParseException {
        HashMap<String, List<Long>> map = new HashMap<>();
        String[] strings = IOUtils.readLines(new File("/data/tmp/data.log"));
        for (String string : strings) {
            int index = string.indexOf("trace_id=");
            String substring = string.substring(index + 9, index + 9 + 19);
            List<Long> list = map.computeIfAbsent(substring, k -> new ArrayList<>());
            int start = string.indexOf("message:");
            String substring1 = string.substring(start + 8, start + 8 + 19);
            Date date = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse(substring1);
            list.add(date.getTime());
            map.put(substring, list);
        }

        map.forEach(new BiConsumer<String, List<Long>>() {
            @Override
            public void accept(String s, List<Long> longs) {
                long start = 0;
                long end = 0;
                List<Long> list = longs.stream().sorted().collect(Collectors.toList());
                for (Long aLong : list) {
                    if (start == 0) {
                        start = aLong;
                    }
                    end = aLong;
                }
                System.out.printf("key=%s,len=%d,start=%d,end=%d,spend=%d\n", s, longs.size(), start, end, (end - start));
            }
        });
    }
}
