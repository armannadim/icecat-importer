package com.christian.icecat;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author NAseq
 */
public class configProperties {

    public static Properties properties = new Properties();

    public static void init() {
        try {
            File file = new File("src/config.properties");
            System.out.println(file.getAbsolutePath());
            properties.load(new FileInputStream("src\\main\\java\\com\\christian\\icecat\\config.properties"));
            //setVariables();
        } catch (IOException e) {
            System.out.println("Error Property files: " + e);
            System.exit(0);
        }
    }

    /*public static void setVariables() {
        connect.setICECAT_URL_STR(properties.getProperty("config.ICECAT_URL_STR"));
        connect.setICECAT_USERNAME(properties.getProperty("config.ICECAT_USERNAME"));
        connect.setICECAT_PASSWORD(properties.getProperty("config.ICECAT_PASSWORD"));
        connect.setCSV_FILE_NAME(properties.getProperty("config.CSV_FILE_NAME"));
        connect.setSAVE_XML_FILES_TO(properties.getProperty("config.SAVE_XML_FILES_TO"));
        
        List<String> elephantList = Arrays.asList(properties.getProperty("config.catList").split(","));
        connect.setCatList(elephantList);
        
        connect.setNumber(Integer.parseInt(properties.getProperty("config.number")));
    }*/
}
