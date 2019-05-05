package fr.pumpmykins.citrouillie;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class OnPumpKinPieEatListener implements Listener {

	@EventHandler
	public void OnPumpKinsPieEat(PlayerItemConsumeEvent event) {
		
		if(event.getItem().getType().equals(Material.PUMPKIN_PIE)) {
			Player p = event.getPlayer();
			
			double max_health = p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
			double health = p.getHealth();
			if(health+4 < max_health) {
				
				p.setHealth(health+4);
			} else {
				
				p.setHealth(max_health);
			}
			
			p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 4800, 2), true);
			
		} else if(event.getItem().getType().equals(Material.GOLDEN_APPLE) || event.getItem().getType().equals(Material.ENCHANTED_GOLDEN_APPLE)) {
			
			event.setCancelled(true);
			event.getPlayer().sendMessage("L'or ne se mange pas !");
		}
	}
	
	@EventHandler
	public void OnRegen(EntityRegainHealthEvent event) {
		
		if(!(event.getEntity() instanceof Player)) {
			
			return;
		}
		if(event.getRegainReason().equals(RegainReason.MAGIC_REGEN) || event.getRegainReason().equals(RegainReason.MAGIC) || event.getRegainReason().equals(RegainReason.REGEN))
			event.setCancelled(true);
	}
}
