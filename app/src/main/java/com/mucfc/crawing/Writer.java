package com.mucfc.crawing;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by abby on 2017/9/14.
 */

public class Writer {
    public static void write(String message){

        try {
            String filePathAndName = Environment.getExternalStorageDirectory()
                    .getAbsolutePath()
                    + "/Download/list1.csv";

            File f = new File(filePathAndName);
            if (!f.exists()) {
                f.createNewFile();
            }
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f, true),"UTF-8");
            BufferedWriter writer=new BufferedWriter(write);
            writer.write(message);
            writer.close();
        } catch (Exception e) {
            System.out.println("写文件内容操作出错");
            e.printStackTrace();
        }
    }
}
