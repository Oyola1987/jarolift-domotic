package com.jarolift.domotic.service;

import org.springframework.stereotype.Controller;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;

@Controller
public class ESP32Versions {
    private final static String FOLDER_PATH = "./bin_esp32";
    File dir = new File(FOLDER_PATH);

    public ESP32Versions() {
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    public File getLastestVersion() {
        File [] binFiles = dir.listFiles((dir, name) -> name.endsWith(".bin"));

        Arrays.sort(binFiles, Collections.reverseOrder());

        return binFiles.length > 0 ? binFiles[0] : null;
    }
}
