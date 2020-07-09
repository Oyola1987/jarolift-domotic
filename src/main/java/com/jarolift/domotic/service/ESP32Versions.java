package com.jarolift.domotic.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

@Controller
public class ESP32Versions {
    private static final String NO_FILES = "No 'bin' files";
    private final static String FOLDER_PATH = "./bin_esp32";
    private static Logger logger = LogManager.getLogger(ESP32Versions.class);
    File dir = new File(FOLDER_PATH);

    public ESP32Versions() {
        ensureFolder();
    }

    public ByteArrayResource getLastFile(String userAgent) {
        File file = getLatestFile();
        String version = file != null ? file.getName() : NO_FILES;
        logger.info("[USER AGENT]: " + userAgent);
        logger.info("[LAST VERSION]: " + version);

        if(file == null) {
            return null;
        } else {
            ByteArrayResource resource = null;
            try {
                Path path = Paths.get(file.getAbsolutePath());
                resource = new ByteArrayResource(Files.readAllBytes(path));
            } catch (Exception e) {
                logger.error("Error user agent: " + userAgent);
                logger.error("Error message: " + e.getMessage());
            }
            return resource;
        }
    }

    public String getLastVersion(String userAgent) {
        File file = getLatestFile();
        String version = file != null ? file.getName() : NO_FILES;
        logger.info("[USER AGENT]: " + userAgent);
        logger.info("[LAST VERSION]: " + version);
        return file == null ? null : version;
    }

    private File getLatestFile() {
        ensureFolder();
        File [] binFiles = dir.listFiles((dir, name) -> name.endsWith(".bin"));

        Arrays.sort(binFiles, Collections.reverseOrder());

        return binFiles.length > 0 ? binFiles[0] : null;
    }

    private void ensureFolder() {
        if (!dir.exists()) {
            dir.mkdir();
        }
    }
}
