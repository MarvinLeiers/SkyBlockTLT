package de.marvinleiers.skyblocktlt.worldgeneration;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SkyBlockWorldGenerator extends ChunkGenerator
{
    @Override
    public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome)
    {
        ChunkData chunkData = super.createChunkData(world);

        for (int xx = 0; xx < 16; xx++)
        {
            for (int zz = 0; zz < 16; zz++)
            {
                biome.setBiome(xx, 1, zz, Biome.PLAINS);
            }
        }

        return chunkData;
    }

    @Override
    public boolean canSpawn(World world, int x, int z)
    {
        return true;
    }
}
