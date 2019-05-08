package fr.pumpmykins.citrouillie;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;

public class OnPumpkinBreakByPiston implements Listener {

	@EventHandler
	public void onPistonBreakBlock(BlockPistonExtendEvent event) {
		
        for (Block block : event.getBlocks().toArray(new Block[0])) {
            Material type = block.getType();
            if (type.equals(Material.PUMPKIN)) {
                event.setCancelled(true);
                event.getBlock().setType(Material.AIR);
            }
        }
	}
}
