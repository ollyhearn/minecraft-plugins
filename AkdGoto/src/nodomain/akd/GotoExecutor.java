package nodomain.akd;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;


public class GotoExecutor extends AbstractCommand{

	public GotoExecutor() {
		super("goto");
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if(sender.hasPermission("AkdGoto.manager")) {
			switch (args.length) {
				case 0:
					sender.sendMessage("§dВы не указали мир! Используйте /goto <мир>");
					return false;
				case 1:
					if(isWorld(args[0], sender)) {
						send(sender.getName(), args[0]);
						return true;
					}
					else {
						sender.sendMessage("§dТакого мира нет! §6Можете создать, используя /genworld <название мира>");
						return false;
					}
			case 2:
					if(isWorld(args[1], sender)) {
						if (sender.getClass() == org.bukkit.entity.Player.class) {
							Bukkit.getPlayer(args[0]).sendMessage("§2You are going to §eB§br§6a§cs§fi§5l §f:)");
						}
						send(args[0], args[1]);
						return true;
					}
					else {
						sender.sendMessage("§dТакого мира нет! §6Можете создать, используя /genworld <название мира>");
						return false;
					}
			default:
					sender.sendMessage("§cСлишком много аргументов");
					return false;
						
			}
		}
		else {
			sender.sendMessage("§cПиська у тебя еще маловата, щенок");
			return false;
		}
			
	}

	
	public boolean isWorld(String world, CommandSender sender) {
		if (Files.isDirectory(Paths.get(world))) {
			sender.sendMessage("§6Мир найден");
			Bukkit.getServer().createWorld(new WorldCreator(world));
		}
		boolean found = false;
		List<World> wl = Bukkit.getWorlds();
		for(int i=0; i < wl.size(); i++ ) {
			System.out.println(wl.get(i).getName());
			if ( wl.get(i).getName().equals(world) ) {
				found = true;
			}
		}
		return found;
	}
	
	public void send(String player, String world) {
		Double x = null;
		Double y = null;
		Double z = null;
		try {
			x = Main.getInstance().getConfig().getConfigurationSection("spawnpoints").getConfigurationSection(world).getDouble("x");
			y = Main.getInstance().getConfig().getConfigurationSection("spawnpoints").getConfigurationSection(world).getDouble("y");
			z = Main.getInstance().getConfig().getConfigurationSection("spawnpoints").getConfigurationSection(world).getDouble("z");
		}
		catch( Exception e ) {
			System.out.println("Spawnpoint didnt found, set default");
			x = 0.0;
			y = 0.0;
			z = 0.0;
			
		}
		Location point = new Location(Bukkit.getWorld(world), x, y, z);
		Bukkit.getPlayer(player).teleport(point);
		
	}
}
