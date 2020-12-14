package com.example.client_zhihu_hzy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StringTools {
    public static String readStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int length; while((length = inputStream.read(buf)) >0) {
            byteArrayOutputStream.write(buf, 0 ,length);
        }
        byte[] stringBytes = byteArrayOutputStream.toByteArray();
        String str = new String(stringBytes);
        return str;
    }
}

