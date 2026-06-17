package nl.orbinuity.overload.config;

import java.util.Properties;
import java.io.*;

public class OverloadConfig {
    public static boolean autoMode = true;
    public static boolean nightMode = false;
    private static final File FILE = new File("config/overload.properties");

    public static void save() {
        try {
            Properties p = new Properties();
            p.setProperty("autoMode", String.valueOf(autoMode));
            p.setProperty("nightMode", String.valueOf(nightMode));
            FILE.getParentFile().mkdirs();
            p.store(new FileWriter(FILE), "Overload Config");
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static void load() {
        if (!FILE.exists()) return;
        try (FileReader reader = new FileReader(FILE)) {
            Properties p = new Properties();
            p.load(reader);
            autoMode = Boolean.parseBoolean(p.getProperty("autoMode", "true"));
            nightMode = Boolean.parseBoolean(p.getProperty("nightMode", "false"));
        } catch (IOException e) { e.printStackTrace(); }
    }
}