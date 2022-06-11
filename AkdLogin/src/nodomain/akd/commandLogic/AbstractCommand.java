package nodomain.akd.commandLogic;

import javax.annotation.Nonnull;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;

import nodomain.akd.Main;

public abstract class AbstractCommand implements CommandExecutor {

	public AbstractCommand(String command) {
		PluginCommand pc = Main.getInstance().getCommand(command);
		pc.setExecutor(this);
	}
	
	public abstract boolean execute(CommandSender sender, String label, String[] args);
	
	 @Override
	    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {
	        
	        return execute(sender, label, args);
	    }

}
