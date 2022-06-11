package nodomain.akd;

import org.bukkit.entity.Player;

public class Teleports {
	
	private static Teleports instance = null;
	
	public Teleports() {
		instance = this;
	}

	public void sendToLobby (Player player) {
		
	}
	
	public static Teleports getInstance() { return instance; }
}
