package nodomain.akd;

public class GotoAPI {

	public GotoAPI() {
		System.out.println("[AGT] API initialized!");
	}
	
	public void SendPlayer(String player, String world) {
		Main.getGotoInstance().send(player, world);
	}
	
	public void GenWorld(String worldname) {
		Main.getGeneratorsInstance().GenerateLimbo(worldname);
	}
}
