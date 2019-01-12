package com.legacy.aether.world.gen.feature;

import java.util.Random;
import java.util.Set;

import com.legacy.aether.blocks.BlocksAether;

import net.minecraft.class_3747;
import net.minecraft.util.math.BlockPos;

public class GoldenOakTreeFeature extends AetherTreeFeature
{

	@Override
	protected boolean generateTree(Set<BlockPos> posListIn, class_3747 worldIn, Random randomIn, BlockPos posIn)
	{
		int height = 9;

		if (worldIn.method_16358(posIn.down(), (ground) -> ground.getBlock() != BlocksAether.aether_grass))
        {
            return false;
        }

		for(int x1 = posIn.getX() - 3; x1 < posIn.getX() + 4; x1++)
		{
			for(int y1 = posIn.getY() + 5; y1 < posIn.getY() + 12; y1++)
			{
				for(int z1 = posIn.getZ() - 3; z1 < posIn.getZ() + 4; z1++)
				{
					BlockPos newPos = new BlockPos(x1, y1, z1);

					if((x1 - posIn.getX()) * (x1 - posIn.getX()) + (y1 - posIn.getY() - 8) * (y1 - posIn.getY() - 8) + (z1 - posIn.getZ()) * (z1 - posIn.getZ()) < 12 + randomIn.nextInt(5) && worldIn.method_16358(newPos, (state) -> canGrowInto(state)))
					{
						this.addBlockState(worldIn, newPos, BlocksAether.golden_oak_leaves.getDefaultState());
					}
				}
			}		
		}

		for(int n = 0; n < height; n++)
		{
			if(n > 4)
			{
				if(randomIn.nextInt(3) > 0)
				{
					branch(worldIn, randomIn, posIn.up(n), n / 4 - 1);
				}
			}

			this.addBlockState(worldIn, posIn.up(n), BlocksAether.golden_oak_log.getDefaultState());
		}

        return true;
	}

	public boolean branch(class_3747 worldIn, Random random, BlockPos posIn, int slant)
    {
		int directionX = random.nextInt(3) - 1;
		int directionY = slant;
		int directionZ = random.nextInt(3) - 1;
		int x = posIn.getX();
		int y = posIn.getY();
		int z = posIn.getZ();
		int i = x;
		int k = z;

		for(int n = 0; n < random.nextInt(2); n++)
		{
			x += directionX;
			y += directionY;
			z += directionZ;
			i -= directionX;
			k -= directionZ;

			if(worldIn.method_16358(posIn, (state) -> canGrowInto(state)))
			{
				this.addBlockState(worldIn, new BlockPos(x, y, z), BlocksAether.golden_oak_log.getDefaultState());
				this.addBlockState(worldIn, new BlockPos(i, y, k), BlocksAether.golden_oak_log.getDefaultState());
			}
		}

		return true;
	}

}