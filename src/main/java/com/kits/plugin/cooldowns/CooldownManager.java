package com.kits.plugin.cooldowns;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class CooldownManager {

    @Getter
    private static final Map<String, Cooldown> cooldowns = new HashMap<>();

    public static boolean checkCooldown(String label) {
        if (!cooldowns.containsKey(label)) return true;
        Cooldown cooldown = cooldowns.get(label);
        return System.currentTimeMillis() - cooldown.timestamp >= cooldown.timeSpan.timeUnit.toMillis(cooldown.timeSpan.amount);
    }

    public static void setNow(String label, TimeSpan timeSpan) {
        if (cooldowns.containsKey(label)) {
            cooldowns.get(label).timestamp = System.currentTimeMillis();
            return;
        }
        cooldowns.put(label, new Cooldown(System.currentTimeMillis(), timeSpan));
    }
}
