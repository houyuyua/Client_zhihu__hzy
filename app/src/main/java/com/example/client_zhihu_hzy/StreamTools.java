package com.example.client_zhihu_hzy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamTools {
    //把inStream转换成String
      public static String readStreamToString(InputStream in) throws IOException {
                 //定义一个内存输出流
                 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                 int len = -1;
                 byte[] buffer = new byte[1024];//1kb
                 while ((len = in.read(buffer)) != -1) {
                         byteArrayOutputStream.write(buffer, 0, len);
                     }
                 in.close();
                 String content = new String(byteArrayOutputStream.toByteArray());
                 return content;
             }
}
