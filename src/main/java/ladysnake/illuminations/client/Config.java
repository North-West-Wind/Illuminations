package ladysnake.illuminations.client;

import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class Config {
    public static final Path PROPERTIES_PATH = FabricLoader.getInstance().getConfigDir().resolve("illuminations.properties");
    private static final Properties config = new Properties();
    private static EyesInTheDark eyesInTheDark;
    private static int density;
    private static boolean autoupdate;

    public enum EyesInTheDark {
        ENABLE, DISABLE, ALWAYS
    }

    public static void load() {
        // if illuminations.properties exist, load it
        if (Files.isRegularFile(PROPERTIES_PATH)) {
            // load illuminations.properties
            try {
                config.load(Files.newBufferedReader(PROPERTIES_PATH));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else { // if no illuminations.properties, load default values
            // define default properties
            config.setProperty("eyes-in-the-dark", EyesInTheDark.ENABLE.toString());
            config.setProperty("density", "100");
            config.setProperty("auto-update", "true");
        }

        try {
            eyesInTheDark = EyesInTheDark.valueOf(config.getProperty("eyes-in-the-dark"));
            density = Integer.parseInt(config.getProperty("density"));
            autoupdate = Boolean.parseBoolean(config.getProperty("auto-update"));
        } catch (Exception e) {
            setEyesInTheDark(EyesInTheDark.ENABLE);
            setDensity(100);
            setAutoUpdate(true);
        }
    }

    public static void save() {
        try {
            config.store(Files.newBufferedWriter(Config.PROPERTIES_PATH), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static EyesInTheDark getEyesInTheDark() {
        return eyesInTheDark;
    }

    public static void setEyesInTheDark(EyesInTheDark value) {
        eyesInTheDark = value;
        config.setProperty("eyes-in-the-dark", value.toString());
        Config.save();
    }

    public static int getDensity() {
        return density;
    }

    public static void setDensity(int value) {
        density = value;
        config.setProperty("density", Integer.toString(value));
        Config.save();
    }

    public static boolean getAutoUpdate() {
        return autoupdate;
    }

    public static void setAutoUpdate(boolean value) {
        autoupdate = value;
        config.setProperty("auto-update", Boolean.toString(value));
        Config.save();
    }
}
