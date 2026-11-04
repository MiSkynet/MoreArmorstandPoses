package me.miskynet.moreArmorstandPoses.custom;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * A {@link Menu} is an {@link Inventory} that can make it
 * easier to create an Inventory
 * */
public class Menu implements InventoryHolder {

    private Inventory inventory;
    private int size;
    private TextComponent title;

    /**
     * @param title The title of the {@link Menu}
     * @param size The size of the Inventory (A multiple of 9 and max 54)
     * */
    public Menu(TextComponent title, int size) {
        this.inventory = Bukkit.createInventory(this, size, title);
        this.title = title;
        this.size = size;
    }

    /**
     * Builds the menu (add all basic / necessary items)
     * */
    public void buildMenu() {}

    /**
     * Builds the {@link Menu} for a specific player. This can be used when, for example,
     * the {@link Menu} contains items that require data of the player
     * @param player The player the menu is being built for
     * */
    public void buildMenu(Player player) {
        buildMenu();
    }

    /**
     * Sets an {@link ItemStack} into a specific slot
     * @param slot Slot where the item should be put
     * @param itemStack The {@link ItemStack} that should be put into the slot
     * */
    public Menu setItem(int slot, ItemStack itemStack) {
        this.inventory.setItem(slot, itemStack);
        return this;
    }

    /**
     * Gets an {@link ItemStack} out of a menu {@link Menu}
     * @param slot The slot the {@link ItemStack} should be got from
     * */
    public ItemStack getItem(int slot) {
        return this.inventory.getItem(slot);
    }

    /**
     * Gets the {@link Inventory} (The bukkit version of this {@link Menu})
     * @return Inventory of the shop
     * */
    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }

    /**
     * Opens the {@link Menu} for a player
     * */
    public void openForPlayer(Player player) {
        player.openInventory(this.inventory);
    }

    /**
     * Gets the size of the {@link Menu} (in slots)
     * @return Amount of slots in the {@link Menu}
     * */
    public int getSize() {
        return this.size;
    }

    /**
     * Gets the title of the {@link Menu}
     * */
    public TextComponent getTitle() {
        return this.title;
    }

}
