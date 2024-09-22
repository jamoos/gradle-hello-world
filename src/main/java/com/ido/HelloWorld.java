package com.ido;
import java.util.jar.Manifest;
import java.io.IOException;
import java.net.URL;

/**
 * Hello world!
 */
 
public class HelloWorld {
    public static void main(String[] args) {
        try {
            // Get the Manifest from the JAR file
            URL manifestUrl = HelloWorld.class.getResource("/META-INF/MANIFEST.MF");
            Manifest manifest = new Manifest(manifestUrl.openStream());

            // Get the Implementation-Version attribute
            String version = manifest.getMainAttributes().getValue("Implementation-Version");

            System.out.println("Mr. K says: \"World, Hold on!\" (Version: " + version + ")");
        } catch (IOException e) {
            System.err.println("Error reading Manifest: " + e.getMessage());
        }
    }
}

