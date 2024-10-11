package com.audit;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;

public class Logger {
    public void update(String name) {
        Date date = new Date();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/com/audit/audit.csv", true));
            bufferedWriter.write(name + ',' + date + '\n');
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
