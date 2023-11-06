package com.github.vladimirplotnikov.homework;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Writer {

public static void write(String fileName, String data) {
            Path source = Paths.get(Main.class.getResource("/resources").getPath());
        File file= new File (source+File.separator + fileName);
        FileWriter fw;
        if (file.exists())
        {
            try {
                fw = new FileWriter(file,true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else
        {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                fw = new FileWriter(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try(BufferedWriter writer = new BufferedWriter(fw)){
            writer.write(data + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
}
}
