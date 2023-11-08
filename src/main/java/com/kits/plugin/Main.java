package com.kits.plugin;

import com.kits.plugin.commands.ClaimKitCmd;
import com.kits.plugin.services.KitService;
import com.kits.plugin.services.message.MessageService;
import com.kits.plugin.utilities.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

//	Configs
	private Config config;
	private Config kitsConfig;

//	Services
	private MessageService messageService;
	private KitService     kitService;

	@Override
	public void onEnable() {
		loadFiles();
		setupServices();
		registerCommands();

		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	}

	@Override
	public void onDisable() {
		Bukkit.getOnlinePlayers().forEach(HumanEntity::closeInventory);
	}

	void loadFiles() {
		config     = new Config(this, getDataFolder(), "config", "config.yml");
		kitsConfig = new Config(this, getDataFolder(), "kits", "kits.yml");
	}

	void setupServices() {
		messageService    = new MessageService(config);
		kitService        = new KitService(config, messageService, kitsConfig);
	}

	void registerCommands() {
		getCommand("claimkit").setExecutor(new ClaimKitCmd(messageService, kitService, kitsConfig));
	}
}
