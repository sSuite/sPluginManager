package tk.nekotech.sPluginManager;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class onCmdConsole{

	// Colors
	ChatColor aqua = ChatColor.AQUA;
	ChatColor black = ChatColor.BLACK;
	ChatColor blue = ChatColor.BLUE;
	ChatColor darkaqua = ChatColor.DARK_AQUA;
	ChatColor darkblue = ChatColor.DARK_BLUE;
	ChatColor darkgray = ChatColor.DARK_GRAY;
	ChatColor darkgreen = ChatColor.DARK_GREEN;
	ChatColor darkpurple = ChatColor.DARK_PURPLE;
	ChatColor darkred = ChatColor.DARK_RED;
	ChatColor gold = ChatColor.GOLD;
	ChatColor gray = ChatColor.GRAY;
	ChatColor green = ChatColor.GREEN;
	ChatColor lightpurple = ChatColor.LIGHT_PURPLE;
	ChatColor red = ChatColor.RED;
	ChatColor white = ChatColor.WHITE;
	ChatColor yellow = ChatColor.YELLOW;
	// End Colors
	sPluginManager p;

	public onCmdConsole(sPluginManager p){
		this.p = p;
	}

	public boolean cmd(CommandSender i, Command j, String[] l){
		if (j.getName().equalsIgnoreCase("spluginmanager") || j.getName().equalsIgnoreCase("spm") || j.getName().equalsIgnoreCase("splugman")){
			if (l.length == 0){
				help(i);
				return true;
			}
			if (l[0].equalsIgnoreCase("list"))
				// list
				return true;
			else{
				i.sendMessage(ChatColor.RED + "Unknown command.");
				help(i);
				return true;
			}
		}
		return false;
	}

	public final void help(CommandSender i){
		i.sendMessage(darkred + "Aliases: /splugman, /spm");
		i.sendMessage(gold + "Usage: /spluginmanager [list|disable [plugin] [-r]|enable|remembered]");
		i.sendMessage(gold + "  /spluginmanager     " + green + "Displays this help message");
		i.sendMessage(gold + "    list              " + green + "Lists installed plugins on server");
		i.sendMessage(gold + "    disable [plugin]  " + green + "Disables specified plugin");
		i.sendMessage(gold + "      -s              " + green + "Always disable plugin");
		i.sendMessage(gold + "      +s              " + green + "Removes -s flag");
		i.sendMessage(gold + "    enable [plugin]   " + green + "Enables specified plugin");
		i.sendMessage(gold + "    remembered        " + green + "Lists plugins to always disable");
		i.sendMessage(gold + "Example: /splugman disable myplugin -s");
		i.sendMessage(gold + "Disables myplugin, even when the server restarts.");
	}
}
