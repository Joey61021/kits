package com.kits.plugin.utilities;

import org.bukkit.ChatColor;

import java.util.concurrent.TimeUnit;

public class Utils {

	public static String color(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}

	public static String convertLongToDate(long l) {
		int day     = (int) TimeUnit.MILLISECONDS.toDays(l);
		long hours  = TimeUnit.MILLISECONDS.toHours(l) - day * 24L;
		long minute = TimeUnit.MILLISECONDS.toMinutes(l) - TimeUnit.MILLISECONDS.toHours(l) * 60L;
		long second = TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MILLISECONDS.toMinutes(l) * 60L;
		StringBuilder FORMAT = new StringBuilder();
		if (day >= 1) {
			FORMAT.append(day).append("d ");
		}
		if (hours >= 1L) {
			FORMAT.append(hours).append("h ");
		}
		if (minute >= 1L) {
			FORMAT.append(minute).append("m ");
		}
		if (second >= 1L) {
			if (second < 10L) {
				FORMAT.append("0");
			}
			FORMAT.append(second).append("s");
		}
		String result = FORMAT.toString();
		return result.isEmpty() ? "00s" : result;
	}
}
