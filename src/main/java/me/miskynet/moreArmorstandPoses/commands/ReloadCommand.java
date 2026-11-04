package me.miskynet.moreArmorstandPoses.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import me.miskynet.moreArmorstandPoses.custom.ComponentUtils;
import me.miskynet.moreArmorstandPoses.custom.Pose;
import me.miskynet.moreArmorstandPoses.utils.Config;


public class ReloadCommand implements BasicCommand {
    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] args) {

        if (commandSourceStack.getSender().hasPermission("masp.reload")) {

            Config.reload("config.yml");
            Config.reload("customPoses.yml");

            Pose.poseList.clear();
            Pose.setupPoses();

            commandSourceStack.getSender().sendMessage(ComponentUtils.component("&aMoreArmorstandPoses reloaded!"));
        }
    }
}
