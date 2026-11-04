package me.miskynet.moreArmorstandPoses.utils;

import me.miskynet.moreArmorstandPoses.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class PDCUtils {

    public final static NamespacedKey armorStandPoseID = new NamespacedKey(Main.getInstance(), "armorStandPoseID");

    /**
     * Gets the value of a {@link PersistentDataType} from an {@link Entity} or {@link ItemStack}
     * @param object The {@link Entity} or {@link ItemStack} that should be checked
     * @param namespacedKey The {@link NamespacedKey} that should be checked
     * @param persistentDataType The {@link PersistentDataType} that should be checked
     * @return The value of the {@link PersistentDataType} or null if not found
     * */
    public static Object getPDC(Object object, NamespacedKey namespacedKey, PersistentDataType persistentDataType) {
        if (object instanceof Entity entity) {
            return entity.getPersistentDataContainer().get(namespacedKey, persistentDataType);
        } else if (object instanceof ItemStack itemStack) {
            return itemStack.getItemMeta().getPersistentDataContainer().get(namespacedKey, persistentDataType);
        } else {
            throw new IllegalArgumentException("Object must be an instance of Entity or ItemStack");
        }
    }

    /**
     * Sets a value of a {@link PersistentDataType} to an {@link Entity} or {@link ItemStack}
     * @param object The {@link Entity} or {@link ItemStack} that should be modified
     * @param namespacedKey The {@link NamespacedKey}
     * @param persistentDataType The {@link PersistentDataType}
     * @param value The value to set
     * */
    public static void setPDC(Object object, NamespacedKey namespacedKey, PersistentDataType persistentDataType, Object value) {
        if (object instanceof Entity entity) {
            entity.getPersistentDataContainer().set(namespacedKey, persistentDataType, value);
        } else if (object instanceof ItemStack itemStack) {
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.getPersistentDataContainer().set(namespacedKey, persistentDataType, value);
            itemStack.setItemMeta(itemMeta);
        } else {
            throw new IllegalArgumentException("Object must be an instance of Entity or ItemStack");
        }
    }

}
