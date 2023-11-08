package com.kits.plugin.services;

import com.kits.plugin.cooldowns.CooldownManager;
import com.kits.plugin.cooldowns.TimeSpan;
import com.kits.plugin.enums.ArmorType;
import com.kits.plugin.services.message.Message;
import com.kits.plugin.services.message.MessageService;
import com.kits.plugin.utilities.Config;
import com.kits.plugin.utilities.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.TimeUnit;

@Getter
@AllArgsConstructor
public class KitService {

    @NonNull
    private final Config         config;
    @NonNull
    private final MessageService messageService;
    @NonNull
    private final Config         kitsConfig;

    public void applyKit(Player player, String kit) {
        boolean clear = config.getBoolean("Commands.ClaimKit.Clear-Inventory");
        if (clear) {
            player.getInventory().clear();
            player.getInventory().setArmorContents(null);
        }
        if (!kitsConfig.contains(kit + ".content")) return;
        for (String key : kitsConfig.getConfigurationSection(kit + ".content").getKeys(false)) {
            ItemStack item = kitsConfig.getItemStack(kit + ".content." + key);
            try {
                if (clear)
                    player.getInventory().setItem(Integer.parseInt(key), item);
                else if (item != null) {
                    player.getInventory().addItem(item);
                }
            } catch (Exception exception) {
                player.getInventory().setItem(ArmorType.valueOf(key.toUpperCase()).getSlot(), item);
            }
        }
        CooldownManager.setNow(kit + "-" + player.getUniqueId(), new TimeSpan(kitsConfig.getInt(kit + ".cooldown"), TimeUnit.SECONDS));
    }

    public void selectKit(Player player, String kit) {
        if (!player.hasPermission(kitsConfig.getString(kit + ".permission"))) {
            messageService.sendTitle(player, Message.CMD_KIT_NO_PERMISSION);
            return;
        }
        if (!CooldownManager.checkCooldown(kit + "-" + player.getUniqueId())) {
            String cooldown = Utils.convertLongToDate(TimeUnit.SECONDS.toMillis(kitsConfig.getInt(kit + ".cooldown"))
                            - (System.currentTimeMillis() - CooldownManager.getCooldowns().get(kit + "-" + player.getUniqueId()).timestamp));
            messageService.sendTitle(player,
                                        Message.CMD_KIT_ON_COOLDOWN,
                                        (s) -> s.replace("%time%", cooldown));
            return;
        }
        applyKit(player, kit);
        messageService.sendMessage(player,
                                    Message.CMD_KIT_SELECTED,
                                    (s) -> s.replace("%kit%", kitsConfig.getString(kit + ".name")));
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
    }
}
