package com.legacy.aether.mixin;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketSpawnObject;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.legacy.aether.entities.block.EntityFloatingBlock;
import com.legacy.aether.entities.passive.EntityMiniCloud;
import com.legacy.aether.entities.projectile.EntityDart;
import com.legacy.aether.entities.projectile.EntityEnchantedDart;
import com.legacy.aether.entities.projectile.EntityGoldenDart;
import com.legacy.aether.entities.projectile.EntityPhoenixArrow;
import com.legacy.aether.entities.projectile.EntityPoisonDart;
import com.legacy.aether.entities.projectile.EntityPoisonNeedle;
import com.legacy.aether.entities.projectile.crystal.EntityCrystal;

@Mixin(EntityTrackerEntry.class)
public class MixinEntityTrackerEntry 
{

    @Shadow @Final private Entity trackedEntity;

    @Inject(method = "createSpawnPacket", at = @At("INVOKE"), cancellable = true)
	private void createAetherSpawnPacket(CallbackInfoReturnable<Packet<?>> info)
	{
		if (this.trackedEntity instanceof EntityFloatingBlock)
		{
			EntityFloatingBlock floatingBlock = (EntityFloatingBlock) this.trackedEntity;
			info.setReturnValue(new SPacketSpawnObject(this.trackedEntity, 583, Block.getStateId(floatingBlock.getBlockstate())));
		}
		else if (this.trackedEntity instanceof EntityGoldenDart)
		{
			EntityDart dart = ((EntityDart) this.trackedEntity);
			info.setReturnValue(new SPacketSpawnObject(this.trackedEntity, 584, 1 + (dart.func_212360_k() == null ? this.trackedEntity.getEntityId() : dart.func_212360_k().getEntityId())));
		}
		else if (this.trackedEntity instanceof EntityEnchantedDart)
		{
			EntityDart dart = ((EntityDart) this.trackedEntity);
			info.setReturnValue(new SPacketSpawnObject(this.trackedEntity, 585, 1 + (dart.func_212360_k() == null ? this.trackedEntity.getEntityId() : dart.func_212360_k().getEntityId())));
		}
		else if (this.trackedEntity instanceof EntityPoisonNeedle)
		{
			EntityDart dart = ((EntityDart) this.trackedEntity);
			info.setReturnValue(new SPacketSpawnObject(this.trackedEntity, 586, 1 + (dart.func_212360_k() == null ? this.trackedEntity.getEntityId() : dart.func_212360_k().getEntityId())));
		}
		else if (this.trackedEntity instanceof EntityPoisonDart)
		{
			EntityDart dart = ((EntityDart) this.trackedEntity);
			info.setReturnValue(new SPacketSpawnObject(this.trackedEntity, 587, 1 + (dart.func_212360_k() == null ? this.trackedEntity.getEntityId() : dart.func_212360_k().getEntityId())));
		}
		else if (this.trackedEntity instanceof EntityMiniCloud)
		{
			EntityMiniCloud cloud = ((EntityMiniCloud) this.trackedEntity);
			info.setReturnValue(new SPacketSpawnObject(this.trackedEntity, 588 + cloud.getDirection(), 1 + (cloud.getOwner() == null ? this.trackedEntity.getEntityId() : cloud.getOwner().getEntityId())));
		}
		else if (this.trackedEntity instanceof EntityCrystal)
		{
			EntityCrystal crystal = ((EntityCrystal) this.trackedEntity);
			info.setReturnValue(new SPacketSpawnObject(this.trackedEntity, 590 + crystal.getCrystalType().getId(), 1 + (crystal.getAttackTarget() == null ? this.trackedEntity.getEntityId() : crystal.getAttackTarget().getEntityId())));
		}
		else if (this.trackedEntity instanceof EntityPhoenixArrow)
		{
			EntityPhoenixArrow arrow = ((EntityPhoenixArrow) this.trackedEntity);
			info.setReturnValue(new SPacketSpawnObject(this.trackedEntity, 593, 1 + (arrow.func_212360_k() == null ? this.trackedEntity.getEntityId() : arrow.func_212360_k().getEntityId())));
		}
	}

}