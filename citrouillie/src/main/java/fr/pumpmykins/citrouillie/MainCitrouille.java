package fr.pumpmykins.citrouillie;

import org.bukkit.plugin.java.JavaPlugin;

public class MainCitrouille extends JavaPlugin {

	private static MainCitrouille instance;
	
	public void onEnable() {
		
		instance = this;
		
		getServer().getPluginManager().registerEvents(new OnPumpMyKinsBreakListener(), this);
		getServer().getPluginManager().registerEvents(new OnPumpKinPieEatListener(), this);
		getServer().getPluginManager().registerEvents(new OnPumpkinBreakByPiston(), this);
		this.getCommand("regen").setExecutor(new RegenCommandExecutor());
		this.getCommand("spawn").setExecutor(new SpawnCommandExecutor());
	}

	public static MainCitrouille getInstance() {
		return instance;
	}

	public static void setInstance(MainCitrouille instance) {
		MainCitrouille.instance = instance;
	}
	
	
}
