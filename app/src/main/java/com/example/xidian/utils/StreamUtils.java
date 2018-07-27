package com.example.xidian.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtils {
    public static String streamToString(InputStream is){
        ByteArrayOutputStream bos = new   ByteArrayOutputStream();
        byte[] buffur =  new byte[1024];
        int temp = -1;
        try {
            while ((temp = is.read(buffur))!=-1){
                bos.write(buffur,0,temp);
            }
            //返回读取的数据;
            return bos.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bos.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    };
}
