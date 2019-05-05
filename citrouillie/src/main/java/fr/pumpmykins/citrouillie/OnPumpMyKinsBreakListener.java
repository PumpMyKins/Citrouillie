package fr.pumpmykins.citrouillie;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class OnPumpMyKinsBreakListener implements Listener {

	@EventHandler
	public void OnPumpKinsBreak(BlockBreakEvent event) {
		
		Player p = event.getPlayer();
		Block b = event.getBlock();
		if(b.getBlockData().getMaterial().equals(Material.PUMPKIN)) {
			
			Random rand = new Random();
			int r = rand.nextInt(500);
			
			if(r < 4) 
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 600, 2), true);
			if(r == 1)
				p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 12000, 5), true);
			if(r > 400)
				p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 40, 1), true);
			if(r > 490)
				p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200, 2), true);
			if(r < 400 && r > 300)
				p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 600, 4), true);
			if(r > 100)
				p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 1), true);
			if(r < 100)
				p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING,2400, 2), true);
			if(r == 499 && r == 123)
				event.setCancelled(true);
			
			System.out.println(Integer.toString(r));
			
		}
	}
}
