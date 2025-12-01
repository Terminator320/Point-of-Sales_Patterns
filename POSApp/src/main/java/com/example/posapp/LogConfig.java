package com.example.posapp;


import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogConfig {

    static {
        try{
            Logger log = Logger.getLogger("");
            FileHandler fh = new FileHandler("POSApp/src/logfile.log", false);
            fh.setFormatter(new SimpleFormatter());
            log.addHandler(fh);
            log.setLevel(Level.WARNING);
            log.setUseParentHandlers(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized Logger getLogger(String name){
        return Logger.getLogger(name);
    }

}
