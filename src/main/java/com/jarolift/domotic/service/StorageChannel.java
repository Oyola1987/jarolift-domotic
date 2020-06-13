package com.jarolift.domotic.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StorageChannel {
    private final static String FILEPATH = "./tmp/channel.txt";
    private final static File file = new File(FILEPATH);
    private Logger logger;

    public StorageChannel() {
        logger = LogManager.getLogger(StorageChannel.class);
    }

    public void writeChannel(int channel) {
        try {
            ensureFolder();
            FileWriter myWriter = new FileWriter(FILEPATH);
            myWriter.write(String.valueOf(channel));
            myWriter.close();
        } catch (IOException e) {
            logger.error("Error writing channel with value: " + channel);
            e.printStackTrace();
        }
    }

    public String readChannel() {
        String data = "";

        if(file.exists()) {
            try {
                Scanner myReader = new Scanner(file);
                while (myReader.hasNextLine()) {
                    data = myReader.nextLine();
                }
                myReader.close();
            } catch (IOException e) {
                logger.error("Error reading file: " + FILEPATH);
                e.printStackTrace();
            }
        }

        return data;
    }

    private void ensureFolder() {
        File dir = new File(file.getParent());
        if (!dir.exists()) {
            dir.mkdir();
        }
    }
}
