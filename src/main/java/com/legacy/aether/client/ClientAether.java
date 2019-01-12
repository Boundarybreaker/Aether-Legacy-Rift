package com.legacy.aether.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;
import net.fabricmc.fabric.events.client.ClientTickEvent;
import net.fabricmc.fabric.events.client.SpriteEvent;
import net.minecraft.block.Block;
import net.minecraft.item.ItemProvider;

import com.legacy.aether.Aether;
import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.client.gui.GuiFactoryAether;
import com.legacy.aether.client.rendering.color.AetherColor;
import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.network.ClientNetworkAether;

public class ClientAether implements ClientModInitializer
{

	@Override
	public void onInitializeClient()
	{
		GuiFactoryAether.registerGUIs();
		ClientNetworkAether.initializePacketHandler();

		this.registerSprite("ring");
		this.registerSprite("pendant");
		this.registerSprite("cape");
		this.registerSprite("shield");
		this.registerSprite("gloves");
		this.registerSprite("misc");

		this.registerBlockColors(BlocksAether.blue_aercloud, BlocksAether.golden_aercloud, 
    			BlocksAether.white_dyed_aercloud, BlocksAether.orange_dyed_aercloud, BlocksAether.magenta_dyed_aercloud, BlocksAether.light_blue_dyed_aercloud,
    			BlocksAether.yellow_dyed_aercloud, BlocksAether.lime_dyed_aercloud, BlocksAether.pink_dyed_aercloud, BlocksAether.grey_dyed_aercloud, 
    			BlocksAether.light_grey_dyed_aercloud, BlocksAether.cyan_dyed_aercloud, BlocksAether.purple_dyed_aercloud, BlocksAether.blue_dyed_aercloud, 
    			BlocksAether.brown_dyed_aercloud, BlocksAether.green_dyed_aercloud, BlocksAether.red_dyed_aercloud, BlocksAether.black_dyed_aercloud);

		this.registerItemColors(ItemsAether.moa_egg, ItemsAether.zanite_helmet, ItemsAether.zanite_chestplate, ItemsAether.zanite_leggings, ItemsAether.zanite_boots,
    	    	ItemsAether.gravitite_helmet, ItemsAether.gravitite_chestplate, ItemsAether.gravitite_leggings, ItemsAether.gravitite_boots,
    	    	ItemsAether.neptune_helmet, ItemsAether.neptune_chestplate, ItemsAether.neptune_leggings, ItemsAether.neptune_boots,
    	    	ItemsAether.obsidian_helmet, ItemsAether.obsidian_chestplate, ItemsAether.obsidian_leggings, ItemsAether.obsidian_boots);

		ClientTickEvent.CLIENT.register(mineCraftClient -> ClientTickHandler.clientTick(mineCraftClient));
	}

	private void registerSprite(String name)
	{
		SpriteEvent.PROVIDE.register(registry -> registry.register(Aether.locate("item/sprite/" + name)));
	}

	private void registerBlockColors(Block... blocks)
	{
		AetherColor color = new AetherColor();

		ColorProviderRegistry.BLOCK.register(color, blocks);
		ColorProviderRegistry.ITEM.register(color, blocks);
	}

	private void registerItemColors(ItemProvider... items)
	{
		AetherColor color = new AetherColor();

		ColorProviderRegistry.ITEM.register(color, items);
	}

}