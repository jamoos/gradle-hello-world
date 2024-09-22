package com.ido;

/**
 * Hello world!
 */
 
public class HelloWorld {
    public static void main(String[] args) {
        String version = System.getProperty("app.version");
        if (version == null) {
            version = "Unknown"; 
        }
        System.out.println("Mr. K says: \"World, Hold on!\" (Version: " + version + ")");
    }
}
