package me.miskynet.moreArmorstandPoses.utils;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.util.EulerAngle;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Utils {

    private static final Map<UUID, Long> clickCooldownMap = new HashMap<>();
    private static final int clickCooldown = 200;

    /**
     * Formats an enum constant to a more readable string
     * @param string The enum constant to format
     * @return A formatted string
     */
    /*
     * Coded by AI c:
     * */
    public static String formatEnumToString(String string) {

        String lowerCase = string.toLowerCase().replace('_', ' ');

        String[] words = lowerCase.split(" ");
        StringBuilder formattedName = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                formattedName.append(word.substring(0, 1).toUpperCase())
                        .append(word.substring(1))
                        .append(" ");
            }
        }

        return formattedName.toString().trim();
    }

    /**
     * Calculate degree into radiant
     * @param degree The degree that should be calculated into radiant
     * @return Radiant as {@link Double}
     * */
    public static Double degreeInRadiant(Double degree) {
        return degree / (180 / Math.PI);
    }

    /**
     * Creates a new click cooldown. This prevents players from
     * clicking again in an inventory
     * @param player The player that the cooldown should be applied on
     * */
    public static void createClickCooldown(Player player) {
        long currentTime = System.currentTimeMillis();
        clickCooldownMap.put(player.getUniqueId(), currentTime);
    }

    /**
     * Checks if a player has an active cooldown
     * @param player The player that should be checked
     * @return True when the {@link Player} can click, false when not
     * */
    public static Boolean checkForAllowedClick(Player player) {
        long currentTime = System.currentTimeMillis();

        if (clickCooldownMap.containsKey(player.getUniqueId())) {
            long lastClick = clickCooldownMap.get(player.getUniqueId());
            if (currentTime - lastClick < clickCooldown) {
                return false;
            }
            clickCooldownMap.remove(player.getUniqueId());
        }
        return true;
    }

    /**
     * The {@link #radEuler(Double, Double, Double)} method creates a basic {@link EulerAngle}
     * but takes degree as an input
     * @param x angle in x direction in degree
     * @param y angle in y direction in degree
     * @param z angle in z direction in degree
     * @return {@link EulerAngle}
     * */
    public static EulerAngle radEuler(Double x, Double y, Double z) {
        return new EulerAngle(Utils.degreeInRadiant(x), Utils.degreeInRadiant(y), Utils.degreeInRadiant(z));
    }

    /**
     * Capitalizes each part of a string that is considered a new word
     * @param string The String that will be capitalized
     * @return The capitalized string
     * */
    // Coded by AI
    public static String getCapitalizedString(String string) {

        String[] parts = string.toLowerCase().split("_");
        StringBuilder builder = new StringBuilder();

        for (String part : parts) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(StringUtils.capitalize(part));
        }

        String formattedName = builder.toString();
        return formattedName;
    }

}
