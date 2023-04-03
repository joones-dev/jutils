package dev.joones.jutils.features.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.inventory.InventoryClickEvent;

public interface GUI {
    /**
     * Checks if a {@link Player} has the {@link GUI} open
     * @param player The {@link Player} to check for
     * @return {@code true} if the {@link Player} has the {@link GUI} open, {@code false} otherwise
     */
    boolean isPlayer(Player player);

    /**
     * Opens the {@link GUI} for the {@link Player}
     * @param player The {@link Player} to open the {@link GUI} for
     */
    void open(Player player);

    /**
     * Gets called when a {@link Player} who has the {@link GUI} open clicks on the {@link GUI}
     * @param player The {@link Player} who clicked
     * @param slot The slot that was clicked
     * @param cursor The {@link ItemStack} in the {@link Player}'s cursor
     * @param action The {@link InventoryAction}
     * @return {@code false} if the {@link InventoryClickEvent} should be cancelled, {@code true} otherwise
     */
    boolean onClick(Player player, int slot, ItemStack cursor, InventoryAction action);

    /**
     * Gets called when a {@link Player} who has the {@link GUI} open, closes the {@link GUI}
     * @param player The {@link Player} who closed the {@link GUI}
     */
    void close(Player player);
}
