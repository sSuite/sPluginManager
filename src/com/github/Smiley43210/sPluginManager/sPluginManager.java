package com.github.Smiley43210.sPluginManager;

import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class sPluginManager extends JavaPlugin {

	int x = -2389;
	private FileConfiguration cfg;
	Logger log = this.getLogger();


	public void onEnable() {

		cfg = this.getConfig();
		if(cfg.getBoolean("sendStats")){
			log.info("This plugin will send usage stats to metrics.griefcraft.com");
			log.info("every 10 minutes. Option to disable is in the config.");
			try {
				Metrics metrics = new Metrics();
				metrics.beginMeasuringPlugin(this);
			} catch (IOException e) {
				PST(e);
			}
		}
		startTimer
	}

	public void onDisable() {

	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		onCmdPlayer cmdPlayer = new onCmdPlayer(this);
		onCmdConsole cmdConsole = new onCmdConsole(this);
		if (!(sender instanceof Player)) {
			// Command sent by console/plugin
			cmdConsole.cmd(sender, cmd, args);
		} else {
			// Command sent by player
			cmdPlayer.cmd(sender, cmd, args);
		}
		return true;
	}

	public final void startTimer(){
		this.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {

			public void run() {
				x = 0;
			}
		}, 0L, 12000L);
	}

}
