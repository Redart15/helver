package redart15.helver.entity.mob;

import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.helper.EnvironmentHelper;

public class PlayerUtil {
    private PlayerUtil() {/* no need to initiate*/}

    public enum InventoryType {
        HOLD, MAIN, ARMOR
    }

    /// Y pos on the server counted from the player's foot height but on the client it is counted from the player's head height
    /// We want count the player pos from his feet
    public static double getY(Player player) {
        if (EnvironmentHelper.isSinglePlayer()) {
            return player.y - player.bbHeight;
        }
        return player.y;
    }

    public static double getHeadY(Player player) {
        if (EnvironmentHelper.isSinglePlayer()) {
            return player.y;
        }
        return player.y - player.bbHeight;
    }

    public static void damageItem(Player player, ItemStack stack, InventoryType type, int index) {
        PlayerUtil.damageItem(player, 1, stack, type, index);
    }

    /// The normal damageItem does not destroy the item if the item durability hits zero.
    public static void damageItem(Player player, int itemDamage, ItemStack stack, InventoryType type, int index) {
        stack.damageItem(itemDamage, player);
        if (stack.stackSize <= 0) {
            switch (type) {
                case HOLD:
                    player.destroyCurrentEquippedItem();
                    return;
                case ARMOR:
                    PlayerUtil.damageItemArmor(player, itemDamage, stack, index);
                    return;
                case MAIN:
                default:
                    PlayerUtil.damageItemMain(player, itemDamage, stack, index);
            }
        }
    }

    /// The normal damageItem does not destroy the item if the item durability hits zero. This target an item to destroy
    /// in the player main inventory at an index.
    public static void damageItemMain(Player player, int itemDamage, ItemStack stack, int index) {
        stack.damageItem(itemDamage, player);
        if (stack.stackSize <= 0) {
            player.inventory.mainInventory[index] = null;
        }
    }

    ///  Tool taking only 1 damage is quite common.
    public static void damageItemMain(Player player, ItemStack stack, int index) {
        PlayerUtil.damageItemMain(player, 1, stack, index);
    }

    /// The normal damageItem does not destroy the item if the item durability hits zero. This target an item to destroy
    /// in the player armor inventory at an index.
    public static void damageItemArmor(Player player, int itemDamage, ItemStack stack, int index) {
        stack.damageItem(itemDamage, player);
        if (stack.stackSize <= 0) {
            player.inventory.armorInventory[index] = null;
        }
    }

    ///  Tool taking only 1 damage is quite common.
    public static void damageItemArmor(Player player, ItemStack stack, int index) {
        PlayerUtil.damageItemArmor(player, 1, stack, index);
    }
}
