package com.common.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * TODO
 *
 * @date:2020/2/26 22:40
 * @author: <a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */
public class Demo {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        String[] strings = str.split(" ");
        String res = "";
        for (int i = strings.length - 1; i >= 0; i--) {
            if (i==0){
                res += strings[i] ;
            }else{
                res += strings[i]+ " ";
            }

        }
        System.out.println(res);
    }


}
