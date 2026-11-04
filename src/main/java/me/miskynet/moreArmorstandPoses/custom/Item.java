package me.miskynet.moreArmorstandPoses.custom;

import me.miskynet.moreArmorstandPoses.utils.Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;
import io.papermc.paper.datacomponent.item.CustomModelData;

import java.util.List;

/**
 * The {@link Item} is a util to create an {@link ItemStack} more easy. In
 * the end, you can convert the {@link Item} into an {@link ItemStack} by
 * using {@link #toItemStack()}
 * */
public class Item {

    private Material material;
    private Component displayName;
    private List<Component> lore;
    private Integer amount;
    private CustomModelDataComponent customModelDataComponent;
    private List<Enchantment> enchantments;
    private boolean enchantmentGlint = false;
    private ItemMeta itemMeta = null;

    /*
    * Different constructors
    * obv reasons
    * */
    /**
     * @param material The {@link Material} of the {@link Item}
     * */
    public Item(Material material) {
        this(material, null, null, 1, null);
    }

    /**
     * @param material The {@link Material} of the {@link Item}
     * @param displayName The display name of the {@link Item} as {@link Component}
     * */
    public Item(Material material, Component displayName) {
        this(material, displayName, null, 1, null);
    }

    /**
     * @param material The {@link Material} of the {@link Item}
     * @param displayName The display name of the {@link Item} as {@link Component}
     * @param lore The lore of the {@link Item} as {@link List} of {@link Component}
     * */
    public Item(Material material, Component displayName, List<Component> lore) {
        this(material, displayName, lore, 1, null);
    }

    /**
     * @param material The {@link Material} of the {@link Item}
     * @param displayName The display name of the {@link Item} as {@link Component}
     * @param lore The lore of the {@link Item} as {@link List} of {@link Component}
     * @param amount Amount of the {@link Item}
     * */
    public Item(Material material, Component displayName, List<Component> lore, int amount) {
        this(material, displayName, lore, amount, null);
    }

    /**
     * @param material The {@link Material} of the {@link Item}
     * @param displayName The display name of the {@link Item} as {@link Component}
     * @param lore The lore of the {@link Item} as {@link List} of {@link Component}
     * @param amount Amount of the {@link Item}
     * @param customModelDataComponent The {@link CustomModelDataComponent} for the {@link Item}
     * */
    public Item(Material material, Component displayName, List<Component> lore, int amount, CustomModelDataComponent customModelDataComponent) {
        this.material = material;
        this.displayName = displayName;
        this.lore = lore;
        this.amount = amount;
        this.customModelDataComponent = customModelDataComponent;
    }

    /**
     * Sets the {@link Material} of the {@link Item}
     * @param material The new {@link Material} of the {@link Item}
     * @return {@link Item}
     * */
    public Item setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * Gets the {@link Material} of the {@link Item}
     * @return {@link Material}
     * */
    public Material getMaterial() {
        return this.material;
    }

    /**
     * Sets the Display name of the {@link Item}
     * @param displayName The new display name of the {@link Item} as {@link Component}
     * @return {@link Item}
     * */
    public Item setDisplayName(Component displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * Gets the display name of the {@link Item}
     * @return Display name as {@link Component}
     * */
    public Component getDisplayName() {
        return this.displayName;
    }

    /**
     * Sets the Lore of the {@link Item}
     * @param lore Lore as {@link List} of {@link Component}
     * @return {@link Item}
     * */
    public Item setLore(List<Component> lore) {
        this.lore = lore;
        return this;
    }

    /**
     * Gets the lore of the {@link Item}
     * @return Lore as {@link List} of {@link Component}
     * */
    public List<Component> getLore() {
        return this.lore;
    }

    /**
     * Sets the amount of the {@link Item}
     * @param amount The amount of the {@link Item}
     * @return {@link Item}
     * */
    public Item setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Gets the amount of the {@link Item}
     * @return Lore as {@link List} of {@link Component}
     * */
    public Integer getAmount() {
        return this.amount;
    }

    /**
     * Sets a {@link CustomModelData} for the {@link Item}
     * @param customModelDataComponent The {@link CustomModelDataComponent} of the {@link Item}
     * @return {@link Item}
     * */
    public Item setCustomModelData(CustomModelDataComponent customModelDataComponent) {
        this.customModelDataComponent = customModelDataComponent;
        return this;
    }

    /**
     * Gets the {@link CustomModelDataComponent} of the {@link Item}
     * @return {@link CustomModelDataComponent}
     * */
    public CustomModelDataComponent getCustomModelDataComponent() {
        return this.customModelDataComponent;
    }

    /**
     * Adds an {@link Enchantment} to the {@link Item}
     * @param enchantment The {@link Enchantment} to add
     * @return {@link Item}
     * */
    public Item addEnchantment(Enchantment enchantment) {
        this.enchantments.add(enchantment);
        return this;
    }

    /**
     * Gets a {@link List} of all the {@link Enchantment} on the {@link Item}
     * @return {@link List} with all {@link Enchantment}
     * */
    public List<Enchantment> getEnchantments() {
        return this.enchantments;
    }

    /**
     * Sets an enchantment glint for the {@link Item}
     * @param enchantmentGlint True if the enchantment glint should be set, false if not
     * @return {@link Item}
     * */
    public Item setEnchantmentGlint(Boolean enchantmentGlint) {
        this.enchantmentGlint = enchantmentGlint;
        return this;
    }

    /**
     * Sets the {@link ItemMeta} of the {@link Item}. When setting it, it will
     * not override other setters like {@link #setDisplayName(Component)}, {@link #setAmount(int)}
     * or {@link #setEnchantmentGlint(Boolean)}
     * @param itemMeta The new {@link ItemMeta} of the {@link Item}
     * @return {@link Item}
     * */
    public Item setItemMeta(ItemMeta itemMeta) {
        this.itemMeta = itemMeta;
        return this;
    }

    /**
     * Gets the {@link ItemMeta} of the {@link Item}
     * @return {@link ItemMeta}
     * */
    public ItemMeta getItemMeta() {
        return this.itemMeta;
    }

    /**
     * Converts the {@link Item} into an {@link ItemStack}
     * @return {@link ItemStack}
     */
    public ItemStack toItemStack() {
        ItemStack itemStack = new ItemStack(this.material);
        ItemMeta itemMeta = this.itemMeta;

        if (this.itemMeta == null) {
            itemMeta = itemStack.getItemMeta();
        }

        if (itemMeta != null) {
            if (this.displayName != null) {
                itemMeta.displayName(this.displayName);
            } else {
                itemMeta.displayName(ComponentUtils.component(false, Utils.formatEnumToString(this.material.toString())));
            }
            if (this.lore != null) itemMeta.lore(this.lore);
            if (this.customModelDataComponent != null) itemMeta.setCustomModelDataComponent(this.customModelDataComponent);

            itemMeta.setEnchantmentGlintOverride(this.enchantmentGlint);

            itemStack.setItemMeta(itemMeta);
        }

        if (this.amount != null) itemStack.setAmount(this.amount);

        return itemStack;
    }

    /**
     * Converts an {@link ItemStack} into an {@link Item}. It is possible that
     * data is lost by converting since an {@link Item} doesn't support
     * every Paper {@link ItemStack} attributes
     * @return {@link Item}
     * */
    public Item parseData(ItemStack itemStack) {
        this.material = itemStack.getType();
        this.amount = itemStack.getAmount();

        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemMeta != null) {
            if (this.displayName != null) this.displayName = itemMeta.displayName();
            if (this.lore != null) itemMeta.lore();
            if (this.customModelDataComponent != null) itemMeta.setCustomModelDataComponent(this.customModelDataComponent);
        }

        return this;
    }
}
