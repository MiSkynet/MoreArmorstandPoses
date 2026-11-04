package me.miskynet.moreArmorstandPoses.listener;

import io.papermc.paper.datacomponent.DataComponentTypes;
import me.miskynet.moreArmorstandPoses.custom.ArmorstandConfigMenu;
import me.miskynet.moreArmorstandPoses.custom.ComponentUtils;
import me.miskynet.moreArmorstandPoses.custom.Pose;
import me.miskynet.moreArmorstandPoses.utils.Config;
import me.miskynet.moreArmorstandPoses.utils.PDCUtils;
import me.miskynet.moreArmorstandPoses.utils.Utils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;

public class InteractListener implements Listener {

    private final ArmorstandConfigMenu armorstandConfigMenu = new ArmorstandConfigMenu(ComponentUtils.component("Armorstand Settings"), 27);

    private final HashMap<Player, ArmorStand> playerArmorStandRelationship = new HashMap<>();

    @EventHandler
    public void interact(PlayerInteractEntityEvent event) {

        // cancel if the interacted entity is no armor stand
        if (!(event.getRightClicked().getType().equals(EntityType.ARMOR_STAND))) {
            return;
        }

        ArmorStand armorStand = (ArmorStand) event.getRightClicked();

        Player player = event.getPlayer();

        // check if the modify tool has to be used and
        // if it's the correct one
        if (!checkForTool(player)) {
            return;
        }

        // check if the player is sneaking
        if (!(player.isSneaking())) {
            return;
        }

        // cancel the opening when another player has already opened the menu
        if (playerArmorStandRelationship.containsValue(armorStand)) {
            return;
        }

        // set the default position if the armor stand doesn't have a pose set currently
        if (PDCUtils.getPDC(armorStand, PDCUtils.armorStandPoseID, PersistentDataType.INTEGER) == null) {
            Pose.poseList.get(0).applyPose(armorStand);
            PDCUtils.setPDC(armorStand, PDCUtils.armorStandPoseID, PersistentDataType.INTEGER, 0);
        }

        // put the player and the armor stand into a hashmap so no other play
        // can open the GUI while another play has the GUI already open
        playerArmorStandRelationship.put(event.getPlayer(), armorStand);
        // update the buttons in the GUI so the data of the armor stand is visible on the items in the gui
        armorstandConfigMenu.updateButtons(playerArmorStandRelationship.get(event.getPlayer()));
        armorstandConfigMenu.openForPlayer(event.getPlayer());

        event.setCancelled(true);
    }

    @EventHandler
    public void invClose(InventoryCloseEvent event) {

        if (!(event.getInventory().getHolder() instanceof ArmorstandConfigMenu)) {
            return;
        }

        playerArmorStandRelationship.remove(event.getPlayer());
    }

    @EventHandler
    public void clickEvent(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        ArmorStand currentArmorStand = playerArmorStandRelationship.get(player);

        if (event.getInventory().getHolder() instanceof ArmorstandConfigMenu) {

            // check for invalid click / player is on click cooldown
            if (!Utils.checkForAllowedClick((Player) event.getWhoClicked())) {
                event.setCancelled(true);
                return;
            }

            // toggle the baseplate
            if (event.getSlot() == ArmorstandConfigMenu.toggleBaseplateButtonSlot) {
                if (currentArmorStand.hasBasePlate()) {
                    currentArmorStand.setBasePlate(false);
                }else {
                    currentArmorStand.setBasePlate(true);
                }
            }

            // toggle pose mechanic
            if (event.getSlot() == ArmorstandConfigMenu.togglePoseButtonSlot) {
                if (PDCUtils.getPDC(currentArmorStand, PDCUtils.armorStandPoseID, PersistentDataType.INTEGER) != null) {
                    int currentPoseID = (int) PDCUtils.getPDC(currentArmorStand, PDCUtils.armorStandPoseID, PersistentDataType.INTEGER);
                    if (currentPoseID < (Pose.poseList.size() - 1)) {
                        Pose.poseList.get(currentPoseID + 1).applyPose(currentArmorStand);
                        PDCUtils.setPDC(currentArmorStand, PDCUtils.armorStandPoseID, PersistentDataType.INTEGER, currentPoseID + 1);
                    }else {
                        Pose.poseList.get(0).applyPose(currentArmorStand);
                        PDCUtils.setPDC(currentArmorStand, PDCUtils.armorStandPoseID, PersistentDataType.INTEGER, 0);
                    }
                }
            }

            // toggle arms
            if (event.getSlot() == ArmorstandConfigMenu.toggleArmsButtonSlot) {
                if (currentArmorStand.hasArms()) {
                    currentArmorStand.setArms(false);
                }else {
                    currentArmorStand.setArms(true);
                }
            }

            armorstandConfigMenu.updateButtons(currentArmorStand);
            Utils.createClickCooldown((Player) event.getWhoClicked());
            event.setCancelled(true);
        }
    }

    /**
     * Check if a player has to use (and if yes, is using) the correct tool
     * @param player The player that should be checked for
     * @return True if the player is using the correct tool, false otherwise
     * */
    private boolean checkForTool(Player player) {

        // return true when the force-mod-tool is disabled in the config.yml
        if (!((boolean) Config.get("config.yml", "force-mod-tool"))) {
            return true;
        }

        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        Material materialInMainHand = itemInMainHand.getType();

        // return false when the material does not match the material in the config
        if (!(materialInMainHand.equals(Material.valueOf((String) Config.get("config.yml", "mod-tool.material"))))) {
            return false;
        }

        // check if displayName is an Integer and is set to -1
        if (Config.get("config.yml", "mod-tool.displayName") instanceof Integer &&
                (Integer) Config.get("config.yml", "mod-tool.displayName") == -1) {
            return true;
        }

        // check if the item is renamed
        // when an item is not renamed, #displayName() will return null
        if (itemInMainHand.getItemMeta().displayName() != null) {
            // check if the displayName in the config is the same as the name on the item
            if (Config.get("config.yml", "mod-tool.displayName")
                    .equals(ComponentUtils.toString((TextComponent) itemInMainHand.getItemMeta().displayName()))) {
                return true;
            }
        } else {
            // check if the default item name quals the display name in the config
            if (Utils.getCapitalizedString(itemInMainHand.getType().name()).equals(Config.get("config.yml", "mod-tool.displayName"))) {
                return true;
            }
        }

        return false;
    }
}
