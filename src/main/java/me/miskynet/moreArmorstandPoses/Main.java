package me.miskynet.moreArmorstandPoses;

import me.miskynet.moreArmorstandPoses.custom.Pose;
import me.miskynet.moreArmorstandPoses.listener.InteractListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

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
