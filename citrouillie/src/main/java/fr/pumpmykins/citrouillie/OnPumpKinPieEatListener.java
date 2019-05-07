package fr.pumpmykins.citrouillie;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class OnPumpKinPieEatListener implements Listener {

	@EventHandler
	public void OnPumpKinsPieEat(PlayerItemConsumeEvent event) {
		
		Material item = event.getItem().getType();
		Player p = event.getPlayer();
		
		if(event.getItem().getType().equals(Material.PUMPKIN_PIE)) {
			
			double max_health = p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
			double health = p.getHealth();
			if(health+4 < max_health) {
				
				p.setHealth(health+4);
			} else {
				
				p.setHealth(max_health);
			}
			
			p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 4800, 2), true);
			
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		
		if(!(event.getItem() == null)) {
			
			Material item = event.getItem().getType();
			Player p = event.getPlayer();
			
			if(item.equals(Material.PUMPKIN_PIE) && event.getPlayer().getFoodLevel() == 20) {
								
				double max_health = p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
				double health = p.getHealth();
				if(health+4 < max_health) {
					
					p.setHealth(health+4);
				} else {
					
					p.setHealth(max_health);
				}
				
				p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 4800, 2), true);
				
				p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() -1);
			
			}  else if(item.equals(Material.POTION) || item.equals(Material.SPLASH_POTION) || item.equals(Material.LINGERING_POTION)) {
				
				PotionMeta potionMeta = (PotionMeta) event.getItem().getItemMeta();
				PotionData potionData = potionMeta.getBasePotionData();
				PotionType potionType = potionData.getType();
				if(potionType.equals(PotionType.INSTANT_HEAL) || potionType.equals(PotionType.REGEN)) {
					
					event.setCancelled(true);
					p.sendMessage("§4§lAttention le verre sa coupe !");
				}
			}
		}
	}
}
