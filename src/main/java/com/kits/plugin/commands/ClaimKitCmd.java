package com.kits.plugin.commands;

import com.kits.plugin.services.KitService;
import com.kits.plugin.services.message.Message;
import com.kits.plugin.services.message.MessageService;
import com.kits.plugin.utilities.Config;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public class ClaimKitCmd implements CommandExecutor {

	@NonNull
	private final MessageService messageService;
	@NonNull
	private final KitService     kitService;
	@NonNull
	private final Config         kitsConfig;

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
		if (!(sender instanceof Player)) {
			messageService.sendMessage(sender, Message.GENERIC_NO_CONSOLE);
			return false;
		}
		Player player = (Player) sender;
		if (args.length == 0) {
			messageService.sendMessage(player, Message.CMD_KIT_ENTER_KIT);
			return false;
		}
		if (kitsConfig.contains(args[0].toLowerCase()))
			kitService.selectKit(player, args[0].toLowerCase());
		else {
			messageService.sendMessage(player,
										Message.CMD_KIT_NOT_A_KIT,
										(s) -> s.replace("%input%", args[0]));
		}
		return false;
	}
}
