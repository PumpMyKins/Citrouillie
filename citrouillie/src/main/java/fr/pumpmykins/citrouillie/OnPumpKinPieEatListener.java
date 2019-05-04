package fr.pumpmykins.citrouillie;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffectType;

public class OnPumpKinPieEatListener implements Listener {

	@EventHandler
	public void OnPumpKinsPieEat(PlayerItemConsumeEvent event) {
		
		if(event.getItem().getType().equals(Material.PUMPKIN_PIE));
		{
			Player p = event.getPlayer();
			
			double max_health = p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
			double health = p.getHealth();
			if(health < max_health) {
				
				double max_regen = max_health - health;
				if(health + 4 > max_regen)
					p.setHealth(health+max_regen);
				else
					p.setHealth(health+4);
			}
			p.addPotionEffect(PotionEffectType.ABSORPTION.createEffect(4800, 2));
			
		}
	}
}
