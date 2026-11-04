package me.miskynet.moreArmorstandPoses.utils;

import me.miskynet.moreArmorstandPoses.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Config {

    public static final Map<String, FileConfiguration> configs = new HashMap<>();
    public static final Map<String, File> files = new HashMap<>();

    /**
     * Set up a new file
     * @param filePath Path where the file should be created
     * */
    public static void setup(String filePath) {
        File file = new File(Main.getInstance().getDataFolder(), filePath);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            Main.getInstance().saveResource(filePath, false);
        }
        files.put(filePath, file);
        configs.put(filePath, YamlConfiguration.loadConfiguration(file));
    }

    /**
     * Gets an instance of a file
     * @param filePath Path to the file
     * @return Instance of a config
     * */
    public static FileConfiguration get(String filePath) {
        if (configs.containsKey(filePath)) {
            return configs.get(filePath);
        }
        // load the file in case it isn't already
        File file = new File(Main.getInstance().getDataFolder(), filePath);
        if (file.exists()) {
            files.put(filePath, file);
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            configs.put(filePath, config);
            return config;
        }
        return configs.get(filePath);
    }

    /**
     * Gets a key out of a config
     * @param filePath Path to the file (including the filename)
     * @param key The key in the config that value should be got
     * @return {@link Object} out of config
     * */
    public static Object get(String filePath, String key) {
        // load the file in case it isn't already
        File file = new File(Main.getInstance().getDataFolder(), filePath);
        if (file.exists()) {
            files.put(filePath, file);
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            configs.put(filePath, config);
        }else {
            return null;
        }

        FileConfiguration config = Config.get(filePath);
        return (config != null) ? config.get(key) : null;
    }

    /**
     * Saves content into a file
     * @param filePath Path to the file (including the filename)
      * */
    public static void save(String filePath) {
        try {
            configs.get(filePath).save(files.get(filePath));
        } catch (IOException e) {
            Bukkit.getLogger().warning("Could not save " + filePath);
        }
    }

    /**
     * Sets a key and value into a file
     * @param filepath Path to the file (including the filename)
     * @param key Key for the setting
     * @param value Value for the setting
     * */
    public static void set(String filepath, String key, Object value) {
        Config.get(filepath).set(key, value);
        Config.save(filepath);
    }

    /**
     * Reloads a file
     * @param filePath Path to the file that should be reloaded (including the filename)
     * */
    public static void reload(String filePath) {
        File file = new File(Main.getInstance().getDataFolder(), filePath);
        if (!file.exists()) {
            Bukkit.getLogger().warning("'" + filePath + "' does not exist. Trying to regenerate it!");
            try {
                Config.setup(filePath);
            } catch (IllegalArgumentException e) {
                Bukkit.getLogger().warning("Could not regenerate '" + filePath + "'. Skipping it!");
                files.remove(filePath);
                configs.remove(filePath);
                return;
            }
        }
        files.put(filePath, file);
        configs.put(filePath, YamlConfiguration.loadConfiguration(file));
    }

    /**
     * Checks if the settings file of a player exists
     * @param filepath Path to the file that should be checked (including the filename)
     * @return True if the file exists, false if not
     * */
    public static boolean checkForExistence(String filepath) {
        File file = new File(Main.getInstance().getDataFolder(), filepath);
        if (file.exists()) return true;
        else return false;
    }
}