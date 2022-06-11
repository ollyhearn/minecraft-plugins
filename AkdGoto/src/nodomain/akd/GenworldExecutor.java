package nodomain.akd;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;

public class GenworldExecutor extends AbstractCommand {

	public GenworldExecutor() {
		super("genworld");
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		
		if(sender.hasPermission("AkdGoto.manager")) {
			if (args.length < 1) {
				sender.sendMessage("§dВы не указали название мира! §6Используйте /genworld <мир>");
				return false;
			}
			else {
				if(isWorld(args[0], sender)) {
					sender.sendMessage("§dТакой мир уже есть!");
					return false;	
				}
				
				else {
					
					Main.getGeneratorsInstance().GenerateLimbo(args[0]);
					sender.sendMessage("§2Мир §b" + args[0] + " §2создан. Используйте /goto " + args[0] + ", чтобы попасть туда.");
				}
				
			}
			
			return false;
		}
		
		else {
			sender.sendMessage("§cПиська у тебя еще маловата, щенок");
			return false;
		}
		
	}
	
	public boolean isWorld(String world, CommandSender sender) {
		if (Files.isDirectory(Paths.get(world))) {
			sender.sendMessage("§6Мир найден, загружаю");
			Bukkit.getServer().createWorld(new WorldCreator(world));
		}
		boolean found = false;
		List<World> wl = Bukkit.getWorlds();
		for(int i=0; i < wl.size(); i++ ) {
			if ( wl.get(i).getName().equals(world) ) {
				found = true;
			}
		}
		return found;
	}

}
