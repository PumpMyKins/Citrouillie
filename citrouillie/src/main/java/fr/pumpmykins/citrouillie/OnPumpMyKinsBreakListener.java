package fr.pumpmykins.citrouillie;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class OnPumpMyKinsBreakListener implements Listener {

	@EventHandler
	public void OnPumpKinsBreak(BlockBreakEvent event) {
		
		Player p = event.getPlayer();
		Block b = event.getBlock();
		if(b.getBlockData().getMaterial().equals(Material.PUMPKIN)) {
			
			Random rand = new Random();
			int r = rand.nextInt(100);
			
			if(r < 50) {
				
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 2), true);
			}
			if(r > 50) {
				
				p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 1), true);
			}
			if(r < 10) {
				
				p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 2400, 5), true);
				ItemStack is = new ItemStack(Material.PUMPKIN_PIE, 1);
				ItemMeta pumpkinPie = is.getItemMeta();
				pumpkinPie.setDisplayName("Kdo de vie");
				is.setItemMeta(pumpkinPie);
				Location pos = p.getLocation();
				p.getWorld().dropItem(pos, is);
			}
			if(r > 25) {
				
				p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 40, 1), true);
			}
			if(r > 95) {
				
				p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 40, 0), true);
			}
			if(r > 98) {
				
				p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200, 2), true);
				event.setCancelled(true);
				b.setType(Material.AIR);
			}
			if(r < 55 && r > 45) {
				
				p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 200, 4), true);
			}
			if(r > 85) {
				
				p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING,6000, 2), true);
			}
			if(r < 60) {
				
				p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 2), true);
			}
			if(r == 7) {
				event.setCancelled(true);
			}
		}
	}
}
