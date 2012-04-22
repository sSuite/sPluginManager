package tk.nekotech.sPluginManager;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import tk.nekotech.sSuiteLib.ErrorHandling;
import tk.nekotech.sSuiteLib.Logging;

public class sPluginManager extends JavaPlugin{

	int x = -2389;
	private FileConfiguration cfg;
	private ErrorHandling err;
	private Logging log;

	public void onEnable(){
		cfg = getConfig();
		err = new ErrorHandling(this, cfg, "", "");
		if (cfg.getBoolean("sendStats")){
			log.info("This plugin will send usage stats to metrics.griefcraft.com");
			log.info("every 10 minutes. Option to disable is in the config.");
			try{
				Metrics metrics = new Metrics();
				metrics.beginMeasuringPlugin(this);
			}catch (IOException e){
				err.PST(e);
			}
		}
		startTimer();
	}

	public void onDisable(){
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		onCmdPlayer cmdPlayer = new onCmdPlayer(this);
		onCmdConsole cmdConsole = new onCmdConsole(this);
		if (!(sender instanceof Player))
			// Command sent by console/plugin
			cmdConsole.cmd(sender, cmd, args);
		else
			// Command sent by player
			cmdPlayer.cmd(sender, cmd, args);
		return true;
	}

	public final void startTimer(){
		getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable(){

			public void run(){
				x = 0;
			}
		}, 0L, 12000L);
	}
}
