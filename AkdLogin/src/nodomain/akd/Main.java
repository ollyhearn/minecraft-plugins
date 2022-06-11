package nodomain.akd;

import nodomain.akd.Listeners;
import nodomain.akd.commandLogic.LoginExecutor;
import nodomain.akd.commandLogic.RegisterExecutor;
import nodomain.akd.interDB.LoginDB;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class Main extends JavaPlugin {
	
	private static Main instance = null;
	private static LoginDB ldata = null;
	private static Listeners listeners = null;
	
	@Override
	public void onEnable() {
		System.out.println("[AL] LOG IN MOTTHERFUCKER!");
		saveDefaultConfig();
		instance = this;
		ldata = new LoginDB();
		listeners = new Listeners();
		getServer().getPluginManager().registerEvents(new Listeners(), this);
		getCommand("login").setExecutor(new LoginExecutor());
		getCommand("register").setExecutor(new RegisterExecutor());
		
		//Bukkit.getServicesManager().getRegistration();
	}
	
	@Override
	public void onDisable() {
		instance = null;
		System.out.println("[AL] Shut down properly, saving databases");
		ldata.closeConnection();
	}
	
	public static Listeners getListeners() { return listeners; }
	public static LoginDB getDB() { return ldata; }
	public static Main getInstance() { return instance; }
	
}
