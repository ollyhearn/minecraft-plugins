package nodomain.akd;

import java.util.Random;

import javax.annotation.Nonnull;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.generator.ChunkGenerator;

public class Generators extends ChunkGenerator{
	@SuppressWarnings("deprecation")
	@Override
    @Nonnull
    public ChunkData generateChunkData(@Nonnull World world, @Nonnull Random random, int x, int z, @Nonnull BiomeGrid biome) {
        return createChunkData(world);
    }
	
	public void GenerateLimbo(String wn) {
		WorldCreator wc = new WorldCreator(wn);
        wc.generator(new Generators());
        wc.createWorld();
	}
}
