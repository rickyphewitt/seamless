package com.rickyphewitt.seamless.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rickyphewitt.seamless.data.exceptions.ConfigException;
import com.rickyphewitt.seamless.data.exceptions.ConfigNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class FileService {

    private static Logger logger = LogManager.getLogger();

    /**
     * Reads a file
     *
     * @param fullyQualifiedFilePath
     * @return
     * @throws IOException
     * @throws ConfigNotFoundException
     */
    public static File read(String fullyQualifiedFilePath) throws IOException, FileNotFoundException {
        File f = null;
        f = new File(fullyQualifiedFilePath);
        if (!f.exists()) {
            String error = "No file found at file found at: " + fullyQualifiedFilePath;
            logger.warn(error);
            throw new FileNotFoundException(error);
        }
        return f;
    }


    /**
     * Writes an object to a file
     *
     * @param clazz
     * @param object
     * @param fullyQualifiedFileLocation
     * @throws ConfigException
     */
    public static void write(Class clazz, Object object, String fullyQualifiedFileLocation) throws ConfigException{
        makeDirsIfRequired(fullyQualifiedFileLocation);
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(fullyQualifiedFileLocation, "UTF-8");
        } catch (FileNotFoundException e) {
            String error = "Unable to find file at: " + fullyQualifiedFileLocation;
            logger.error(error);
            ConfigException configException = new ConfigException(error, e);
            throw configException;
        } catch (UnsupportedEncodingException e) {
            String error = "Incorrect encoding for file at: " + fullyQualifiedFileLocation;
            logger.error(error);
            ConfigException configException = new ConfigException(error, e);
            throw configException;
        }

        Gson gson = new GsonBuilder().create();
        writer.println(gson.toJson(object, clazz));
        writer.close();
    }

    /**
     * Creates any required directories for a given filepath
     *
     * @param fullyQualifiedFileLocation
     */
    private static void makeDirsIfRequired(String fullyQualifiedFileLocation) {
        String fileName = fullyQualifiedFileLocation.substring(fullyQualifiedFileLocation.lastIndexOf("/")+1);
        String directoryPath = fullyQualifiedFileLocation.substring(0, (fullyQualifiedFileLocation.length() - fileName.length()));
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

}
