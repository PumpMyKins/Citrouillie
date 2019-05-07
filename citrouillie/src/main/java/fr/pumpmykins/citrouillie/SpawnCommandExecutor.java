package fr.pumpmykins.citrouillie;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommandExecutor implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			Player p = (Player) sender;
			p.teleport(p.getServer().getWorlds().get(0).getSpawnLocation());
			
			return true;
		}
		return false;
	}

}
