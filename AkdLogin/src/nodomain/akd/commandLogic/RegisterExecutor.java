package nodomain.akd.commandLogic;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;

import nodomain.akd.Main;

public class RegisterExecutor extends AbstractCommand {
	Location lobby = new Location(Bukkit.getWorld("lobby"), 0.5, 60, 0.5);

	public RegisterExecutor() {
		super("register");
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (!Main.getDB().userExists(sender.getName())) {
			if (args.length == 2 && args[0].equals(args[1]) ) {
				try {
					Main.getDB().addUser(sender.getName(), Main.getDB().hash(args[0]));
					sender.sendMessage(Main.getInstance().getConfig().getString("messages.register-succsess"));
					Main.getListeners().stopId(sender.getName());
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
						Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "goto " + sender.getName() + " lobby");}, 40);;
					//LoginDB.getInstance().closeConnection();
					return true;
				}
				catch(Exception e) {
					sender.sendMessage(Main.getInstance().getConfig().getString("messages.register-error"));
					return false;
				}
			}
			else {
				sender.sendMessage(Main.getInstance().getConfig().getString("messages.register-missmatch"));
				return false;
			}
			
		}
		else {
			sender.sendMessage(Main.getInstance().getConfig().getString("messages.register-already"));
			return false;
		}
	}

}
