package nodomain.akd;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	private static Main instance = null;
	private static GotoExecutor GotoInstance = null;
	private static GenworldExecutor GenworldInstance = null;
	private static Generators GeneratorsInstance = null;
	private static GotoAPI API = null;
	
	
	@Override
    public void onEnable() {
		instance = this;
		GotoInstance = new GotoExecutor();
		GenworldInstance = new GenworldExecutor();
		GeneratorsInstance = new Generators();
		API = new GotoAPI();
		System.out.println("[AGT] Goto bussy!");
		
		getCommand("goto").setExecutor(GotoInstance);
		getCommand("genworld").setExecutor(GenworldInstance);
    }
    @Override
    public void onDisable() {
    	instance = null;
    }
    
    public static Main getInstance() { return instance;}
	public static GotoExecutor getGotoInstance() {		return GotoInstance;	}
	public static GenworldExecutor getGenworldInstance() {		return GenworldInstance;	}
	public static Generators getGeneratorsInstance() {		return GeneratorsInstance;	}
	public static GotoAPI getAPI() { return API; }
	
    
}
