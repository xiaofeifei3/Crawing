package com.mucfc.crawing;

import android.os.Environment;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by abby on 2017/9/14.
 */

public class Writer {
    public static void write(String message){
        try{
            FileWriter writer = new FileWriter(Environment.getExternalStorageDirectory()
                    .getAbsolutePath()
                    + "/Download/list1.csv", true);

            writer.write(message);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
