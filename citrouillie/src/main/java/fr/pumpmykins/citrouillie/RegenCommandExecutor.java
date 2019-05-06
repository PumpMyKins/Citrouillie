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
			double max_health = p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
			if(p.getHealth() < max_health) {
				
				if(args.length < 1) {
					
					int cost = 0;
					cost =(int) ((p.getHealth() / 2) *100);
					
					if(ecoAPI.canRemoveHoldings(sender.getName(),new BigDecimal(cost))) {
						
						ecoAPI.removeHoldings(sender.getName(), new BigDecimal(cost));
						double health = p.getHealth();
						if(health+2 > max_health) {
							p.setHealth(max_health);
						} else {	
							p.setHealth(health+2);
						}
						p.sendMessage("1 Coeur vous à été redonné");
						
					} else {
						
						p.sendMessage("Vous n'avez pas l'argent necessaire !");
					}
				} else {
					
					if(args[0].matches("-?(0|[1-9]\\d*)")) {
						
						int cost = 0;
						int regen = 0;
						
						for(int i = 0; i < Integer.parseInt(args[0]); i++) {
							
							regen+=2;
							if(p.getHealth()+ regen > max_health) {
								break;
							}
							double v = p.getHealth();
							v = v / 2;
							BigDecimal bd = new BigDecimal(v);
							bd.setScale(0, BigDecimal.ROUND_FLOOR);
							v = bd.doubleValue();
							
							cost = (int) (cost + ((1*v)/2 * 100));
							
						}
						if(ecoAPI.canRemoveHoldings(sender.getName(),new BigDecimal(cost))) {
							
							ecoAPI.removeHoldings(sender.getName(), new BigDecimal(cost));
							double health = p.getHealth();
							if(health+regen > max_health) {
								p.setHealth(max_health);
							} else {	
								p.setHealth(health+regen);
							}
							p.sendMessage(Integer.toString(regen/2)+" Coeur vous à été redonné");
							
						} else {
							
							p.sendMessage("Vous n'avez pas l'argent necessaire !");
						}
						
						
					} else {
						
						p.sendMessage("Veuillez indiquez un nombre de vie à regenerer !");
					}
				}
			} else {
				
				p.sendMessage("Tu as déjà toute ta vie !");
			}
		}
		return true;
	}

}
