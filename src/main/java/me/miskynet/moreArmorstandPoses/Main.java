package me.miskynet.moreArmorstandPoses;

import me.miskynet.moreArmorstandPoses.custom.Pose;
import me.miskynet.moreArmorstandPoses.listener.InteractListener;
import me.miskynet.moreArmorstandPoses.utils.Config;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        Config.setup("customPoses.yml");

        getServer().getPluginManager().registerEvents(new InteractListener(), this);

        Pose.setupPoses();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance() {
        return Main.getPlugin(Main.class);
    }

}
