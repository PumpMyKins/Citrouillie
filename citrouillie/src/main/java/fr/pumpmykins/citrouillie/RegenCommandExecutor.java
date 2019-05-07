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
					cost =(int) (((p.getHealth() / 2)/2) *100);
					
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
						
						int regen = Integer.parseInt(args[0])*2;
						
						while(p.getHealth() + regen > max_health) {
							
							regen = regen-1;
						}
						int h =(int) p.getHealth();
						int cost = 0;
						for(int i = regen;i != 0; i--) {
						
							cost = cost + (h*50);
							h = h+1;
						}
						if(ecoAPI.canRemoveHoldings(sender.getName(), new BigDecimal(cost))) {
							
							ecoAPI.removeHoldings(sender.getName(), new BigDecimal(cost));
							p.setHealth(p.getHealth()+ regen);
							
							p.sendMessage(Integer.toString(regen) +"vous ont été redonné");
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
