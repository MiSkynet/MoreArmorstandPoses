package me.miskynet.moreArmorstandPoses.custom;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ArmorstandConfigMenu extends Menu {

    public static final int toggleBaseplateButtonSlot = 11;
    public static final int togglePoseButtonSlot = 13;
    public static final int toggleArmsButtonSlot = 15;

    /**
     * @param title The title of the {@link Menu}
     * @param size  The size of the inventory (A multiple of 9 and max 54)
     *
     */
    public ArmorstandConfigMenu(TextComponent title, int size) {
        super(title, size);
        buildMenu();
    }


    public void buildMenu() {
        // fill the entire inventory with gray stained glass pane
        for (int i = 0; i < this.getSize(); i++) {
            Item item = new Item(Material.GRAY_STAINED_GLASS_PANE, ComponentUtils.component(" "));
            this.setItem(i, item.toItemStack());
        }
    }

    /**
     * Update the buttons in the inventory to a new(er) state
     * */
    public void updateButtons(ArmorStand armorStand) {
        this.setItem(toggleBaseplateButtonSlot, getBaseplateButton(armorStand));
        this.setItem(togglePoseButtonSlot, getTogglePoseButton(armorStand));
        this.setItem(toggleArmsButtonSlot, getToggleArmsButton(armorStand));
    }

    /**
     * Gets the toggle baseplate button
     * This also display the current state of the baseplate (whether its enabled or not)
     * @param armorStand The targeted armor stand
     * @return Button as {@link ItemStack}
     * */
    public ItemStack getBaseplateButton(ArmorStand armorStand) {
        if (armorStand.hasBasePlate()) {
            Item toggleBasePlateButton = new Item(Material.SMOOTH_STONE_SLAB, ComponentUtils.component(false, "&7Toggle Baseplate (&aenabled&7)"));
            return toggleBasePlateButton.toItemStack();
        }else {
            Item toggleBasePlateButton = new Item(Material.SMOOTH_STONE_SLAB, ComponentUtils.component(false, "&7Toggle Baseplate (&cdisabled&7)"));
            return toggleBasePlateButton.toItemStack();
        }
    }

    /**
     * Gets the toggle pose button
     * This also displays the current pose
     * @param armorStand The targeted armor stand
     * @return Button as {@link ItemStack}
     * */
    public ItemStack getTogglePoseButton(ArmorStand armorStand) {
        if (Pose.getCurrentPoseID(armorStand) != null) {
            int currentPoseID = Pose.getCurrentPoseID(armorStand);
            Pose pose = Pose.getPoseByID(currentPoseID);

            // create lore
            ArrayList<Component> lore = new ArrayList<>();
            lore.add(ComponentUtils.component(false, "&8Current Pose: " + pose.getName()));
            // create item
            Item togglePoseItem = new Item(Material.NETHER_STAR, ComponentUtils.component(false, "&7Toggle Pose"), lore);
            return togglePoseItem.toItemStack();
        }else {
            // create lore
            ArrayList<Component> lore = new ArrayList<>();
            lore.add(ComponentUtils.component(false, "&8No pose selected"));
            // create item
            Item togglePoseItem = new Item(Material.NETHER_STAR, ComponentUtils.component(false, "&7Toggle Pose"), lore);
            return togglePoseItem.toItemStack();
        }
    }

    /**
     * Gets the toggle arms button
     * @param armorStand
     * @return Button as {@link ItemStack}
     * */
    public ItemStack getToggleArmsButton(ArmorStand armorStand) {
        if (armorStand.hasArms()) {
            Item item = new Item(Material.STICK, ComponentUtils.component(false, "&7Toggle Arms (&aenabled)"));
            return item.toItemStack();
        }else {
            Item item = new Item(Material.STICK, ComponentUtils.component(false, "&7Toggle Arms (&cdisabled)"));
            return item.toItemStack();
        }
    }

}