package com.common.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class IOUtilsTest {

    @Test
    public void testReadLines() throws IOException {
        String[] strings = IOUtils.readLines(new File("D:\\MyDesktop\\test.txt"));
        for (String string : strings) {
            System.out.println(string);
        }
    }

    @Test
    public void testWriteLines() throws IOException {
        IOUtils.writeLines(new File("D:\\MyDesktop\\test5.txt"),true, Arrays.asList("a", "b").toArray(new String[0]));
    }

}