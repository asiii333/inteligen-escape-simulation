package pl.sp.simulation;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

/**
 * Created by Asia on 2015-06-05.
 */
public class Constans {


    //przyszły obiekt Properties
    private Properties properties = new Properties();

    public Constans(){
        System.setProperty("file.encoding", "UTF-8");
        loadProperties();
    }

    public String getValue(String key){
        return properties.getProperty(key);
    }
    public void loadProperties(){
        //Plik z konfiguracją

        //File f = new File("resources/constans.properties");
        //Strumień wejściowy
        InputStream is;
        try {
            String filename = "constans.properties";
            InputStream input = Constans.class.getClassLoader().getResourceAsStream(filename);
            //ładujemy nasze ustawienia
            properties.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
