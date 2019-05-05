package fr.pumpmykins.citrouillie;

import java.math.BigDecimal;

import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.tnemc.core.TNE;
import net.tnemc.core.common.api.TNEAPI;

public class RegenCommandExecutor implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			Player p = (Player) sender;
			TNEAPI ecoAPI = TNE.instance().api();
			if(p.getHealth() < p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()) {
				if(ecoAPI.canRemoveHoldings(sender.getName(),new BigDecimal(100))) {
					
					ecoAPI.removeHoldings(sender.getName(), new BigDecimal(100));
					double health = p.getHealth();
					double max_health = p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
					if(health+2 > max_health) {
						p.setHealth(max_health);
					} else {	
						p.setHealth(health+2);
					}
					p.sendMessage("1 Coeur vous à été redonné");
					
				} else {
					
					p.sendMessage("Vous n'avez pas l'argent necessaire !");
				}
			}
		}
		return true;
	}

}
