package com.legacy.aether.world.gen.feature;

import java.util.function.Supplier;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

import com.mojang.datafixers.Dynamic;

public abstract class AetherFeature<T extends FeatureConfig> extends Feature<T>
{

	private final Supplier<T> supplier;

	public AetherFeature(T config, boolean notifyNeighbors)
	{
		super(null, notifyNeighbors);

		this.supplier = () -> config;
	}

	@Override
	public T deserializeConfig(Dynamic<?> dynamic_1)
	{
		return this.supplier.get();
	}

}