package fr.pumpmykins.citrouillie;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
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
				p.addPotionEffect(PotionEffectType.SLOW.createEffect(600, 2));
			if(r == 1)
				p.addPotionEffect(PotionEffectType.LUCK.createEffect(12000, 5));
			if(r > 400)
				p.addPotionEffect(PotionEffectType.WITHER.createEffect(40, 1));
			if(r > 490)
				p.addPotionEffect(PotionEffectType.WITHER.createEffect(200, 2));
			if(r < 400 && r > 300)
				p.addPotionEffect(PotionEffectType.LEVITATION.createEffect(600, 4));
			if(r > 100)
				p.addPotionEffect(PotionEffectType.BLINDNESS.createEffect(200, 1));
			if(r < 100)
				p.addPotionEffect(PotionEffectType.FAST_DIGGING.createEffect(2400, 2));
			if(r == 499 && r == 123)
				event.setCancelled(true);
			
			System.out.println(Integer.toString(r));
			
		}
	}
}
