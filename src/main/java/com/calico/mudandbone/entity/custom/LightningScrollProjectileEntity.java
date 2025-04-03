package com.calico.mudandbone.entity.custom;

import com.calico.mudandbone.entity.ModEntities;
import com.calico.mudandbone.item.ModItems;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class LightningScrollProjectileEntity extends ThrowableItemProjectile {

    public LightningScrollProjectileEntity(EntityType<? extends LightningScrollProjectileEntity> entityType, Level level) {
        super(entityType, level);
    }

    public LightningScrollProjectileEntity(Level level, LivingEntity shooter) {
        super(ModEntities.LIGHTNING_SCROLL.get(), shooter, level);
    }

    public LightningScrollProjectileEntity(Level level, double x, double y, double z) {
        super(ModEntities.LIGHTNING_SCROLL.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.LIGHTNING_SCROLL.get();
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        if (!this.level().isClientSide()) {
            EntityType.LIGHTNING_BOLT.spawn((ServerLevel) this.level(), result.getBlockPos(), MobSpawnType.TRIGGERED);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (!this.level().isClientSide()) {
            EntityType.LIGHTNING_BOLT.spawn((ServerLevel) this.level(), result.getEntity().blockPosition(), MobSpawnType.TRIGGERED);
        }
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide()) {
            this.discard();
        }
    }

    @Override
    public double getDefaultGravity() {
        return 0.01;
    }
    
}
