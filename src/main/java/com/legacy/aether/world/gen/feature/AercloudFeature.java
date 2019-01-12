package com.legacy.aether.world.gen.feature;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;

import com.legacy.aether.world.gen.config.feature.AercloudConfig;

public class AercloudFeature extends AetherFeature<AercloudConfig>
{

	public AercloudFeature()
	{
		super(null, true);
	}

	public AercloudFeature(AercloudConfig config)
	{
		super(config, true);
	}

	@Override
	public boolean generate(IWorld worldIn, ChunkGenerator<? extends ChunkGeneratorSettings> var2, Random randIn, BlockPos posIn, AercloudConfig configIn)
	{
		BlockPos origin = new BlockPos(posIn.getX() - 14, (configIn.isFlat() ? 0 : randIn.nextInt(64)) + configIn.getY(), posIn.getZ() - 14);
		BlockPos position = new BlockPos(origin);

		for (int amount = 0; amount < configIn.cloudAmount(); ++amount)
		{
			boolean offsetY = ((randIn.nextBoolean() && !configIn.isFlat()) || (configIn.isFlat() && randIn.nextInt(10) == 0));

			int xOffset = randIn.nextInt(2);
			int yOffset = (offsetY ? randIn.nextInt(3) - 1 : 0);
			int zOffset = randIn.nextInt(2);

			position = position.add(xOffset, yOffset, zOffset);

			for (int x = position.getX(); x < position.getX() + randIn.nextInt(2) + 3 * configIn.cloudModifier(); ++x)
			{
				for (int y = position.getY(); y < position.getY() + randIn.nextInt(1) + 2; ++y)
				{
					for (int z = position.getZ(); z < position.getZ() + randIn.nextInt(2) + 3 * configIn.cloudModifier(); ++z)
					{
						BlockPos pos = new BlockPos(x, y, z);

						if (worldIn.isAir(pos))
						{
							if (Math.abs(x - position.getX()) + Math.abs(y - position.getY()) + Math.abs(z - position.getZ()) < 4 * configIn.cloudModifier() + randIn.nextInt(2))
							{
								this.addBlockState(worldIn, pos, configIn.getCloudState());
							}
						}
					}
				}
			}
		}

		return true;
	}

}