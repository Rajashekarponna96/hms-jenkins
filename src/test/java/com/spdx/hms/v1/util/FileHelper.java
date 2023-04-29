package com.spdx.hms.v1.util;


import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@UtilityClass
public class FileHelper {

    public static String readFile(String path, String fileName) {

        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path + fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            ExceptionUtils.wrapAndThrow(e);
        }
        return sb.toString();
    }


}