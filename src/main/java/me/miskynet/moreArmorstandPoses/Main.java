package me.miskynet.moreArmorstandPoses;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import me.miskynet.moreArmorstandPoses.commands.ReloadCommand;
import me.miskynet.moreArmorstandPoses.custom.Pose;
import me.miskynet.moreArmorstandPoses.listener.InteractListener;
import me.miskynet.moreArmorstandPoses.utils.Config;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        Config.setup("config.yml");
        Config.setup("customPoses.yml");

        getServer().getPluginManager().registerEvents(new InteractListener(), this);

        Pose.setupPoses();

        // register reload command
        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS.newHandler(event -> {
            event.registrar().register("maspreload", new ReloadCommand());
        }));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance() {
        return Main.getPlugin(Main.class);
    }

}
