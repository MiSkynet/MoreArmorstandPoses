package me.miskynet.moreArmorstandPoses.custom;

import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.awt.*;

public class ComponentUtils {

    /**
     * Creates a {@link Component} and always unsets italic by default
     * (used to use text formation in messages)
     * @param string The string that gets converted into a Component
     * @return {@link Component}
     * */
    public static TextComponent component(String string) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(string);
    }

    /**
     * Creates a component and define if the message should be italic
     * (used to use text formation in messages)
     * @param italic True if the message should be italic. False if not
     * @param string The string that gets converted into a Component
     * @return {@link Component}
     * */
    public static TextComponent component(boolean italic, String string) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(string).decoration(TextDecoration.ITALIC, italic);
    }

    /**
     * Convert a {@link Component} into a {@link String} with text formation
     * @param component The {@link Component} that should be converted into a string
     * @return {@link Component} as {@link String}
     * */
    public static String toString(TextComponent component) {
        return LegacyComponentSerializer.legacyAmpersand().serialize(component);
    }

}
