package nodomain.akd;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;

//import org.bukkit.plugin.Plugin;

//import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Listeners implements Listener{
	
	private static Listeners instance = null;
	private String[][] logging = new String[100][2];
	
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent e) {
		instance = this;

		Location login = new Location(Bukkit.getWorld("loginWorld"), 8.5, -58, 8.5);
		e.getPlayer().teleport(login);
		if (Main.getDB().userExists(e.getPlayer().getName())) {
			int pid = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
				e.getPlayer().sendMessage(Main.getInstance().getConfig().getString("messages.login-welcome"));
			}, 40, 200);
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
				if(e.getPlayer().getLocation().getWorld() == Bukkit.getWorld("loginWorld") ) {
					Main.getListeners().stopId(e.getPlayer().getName());
					e.getPlayer().kickPlayer(Main.getInstance().getConfig().getString("messages.login-toolong"));
				}
			}, 2400);
			Main.getListeners().setId(pid, e.getPlayer().getName());
		}
		else {
			int pid = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
				e.getPlayer().sendMessage(Main.getInstance().getConfig().getString("messages.register-welcome"));
			}, 40, 200);
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
				if(e.getPlayer().getLocation().getWorld() == Bukkit.getWorld("loginWorld") ) {
					Main.getListeners().stopId(e.getPlayer().getName());
					e.getPlayer().kickPlayer(Main.getInstance().getConfig().getString("messages.login-toolong"));
				}
			}, 2400);
			
			Main.getListeners().setId(pid, e.getPlayer().getName());
		}
	}
	
	public void onPlayerKickEvent(PlayerKickEvent e) {
		Main.getListeners().stopId(e.getPlayer().getName());
	}
	
	public void stopId(String playername) {
		int pid = this.getId(playername);
		System.out.println("Deleting id" + Integer.toString(pid) + " from player " + playername);
		Bukkit.getScheduler().cancelTask(pid);
	}
	
	private void setId(int logid, String playername) {
		boolean found = false;
		for (int i = 0; i < this.logging.length; i++) {
			if(this.logging[i][0] == null) {
				System.out.println("Set logtask id" + Integer.toString(logid));
				this.logging[i][0] = playername;
				this.logging[i][1] = Integer.toString(logid);
				found = true;
				break;
			}
		}
		if (!found) {
			Bukkit.getPlayer(playername).kickPlayer(Main.getInstance().getConfig().getString("messages.login-full"));
		}
		
	}
	
	private int getId(String name) {
		String temp;
		for (int i = 0; i < this.logging.length; i++) {
			System.out.println("Seeking logid for " + name);
			if (this.logging[i][0].equals(name)) {
				System.out.println("Got logtask id" + this.logging[i][1] + " from player " + this.logging[i][0]);
				this.logging[i][0] = null;
				temp = this.logging[i][1];
				this.logging[i][0] = null;
				return Integer.parseInt(temp);
			}
		}
		return 0;
	}
	
	public static Listeners getInstance() { return instance; }
	
	
// unused		

//		e.getPlayer().sendMessage(Main.getInstance().getConfig().getString("messages.login-welcome"));
		
	//	
//		private static final String CHAR_LIST = 
//		        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//		public String randomString(int RANDOM_STRING_LENGTH){
//			StringBuffer randomString = new StringBuffer();
//	                for(int i=0; i<RANDOM_STRING_LENGTH; i++){
//	                    int number = getRandomNumber();
//	                    char ch = CHAR_LIST.charAt(number);
//	                    randomString.append(ch);
//	               }
//	               return randomString.toString();
//	        }
//		private int getRandomNumber() {
//	        int randomInt = 0;
//	        Random randomGenerator = new Random();
//	        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
//	        if (randomInt - 1 == -1) {
//	           return randomInt;
//	        } else {
//	         return randomInt - 1;
//	       }
//	     }
	//	
		
//		Generators nw = new Generators();
//		String worldname = this.randomString(5);
//		nw.GenerateLimbo(worldname);
}
