package nodomain.akd.commandLogic;

import java.io.UnsupportedEncodingException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import nodomain.akd.Main;
import nodomain.akd.interDB.LoginDB;

public class LoginExecutor extends AbstractCommand {
	Location lobby = new Location(Bukkit.getWorld("lobby"), 0.5, 60, 0.5);
	
	public LoginExecutor() {
		super("login");
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		for (int i=0; i < args.length; i++) {
			System.out.println(Integer.toString(i) + " - " + args[i]);
		}
		
		if (args.length == 0) {
			sender.sendMessage(Main.getInstance().getConfig().getString("messages.login-no-password"));
			return false;
		}
		if (Main.getDB().checkPassword(sender.getName(), Main.getDB().hash(args[0]))) {
			System.out.println(sender.getName() + " " + args[0].getClass());
			if (sender.getName().equals("OllyHearn")) {
				sender.sendMessage("§3Привет, создатель!");
			}
			sender.sendMessage(Main.getInstance().getConfig().getString("messages.login-succsess"));
			Main.getListeners().stopId(sender.getName());
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
				Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "goto " + sender.getName() + " lobby");}, 40);
			//LoginDB.getInstance().closeConnection();
			return true;
		}
		else {
			sender.sendMessage(Main.getInstance().getConfig().getString("messages.login-fail"));
			return false;
		}
		
		
	}

}
