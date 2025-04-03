package com.calico.mudandbone.item.custom;

import java.util.List;
import java.util.function.Predicate;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class LightningScrollItem extends Item {

    private static final Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);

    public LightningScrollItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        
        Level level = context.getLevel();
        
        if (!(level instanceof ServerLevel)) {
            return InteractionResult.FAIL;
        } else {

            ItemStack itemStack = context.getItemInHand();
            BlockPos blockPos = context.getClickedPos();

            EntityType.LIGHTNING_BOLT.spawn((ServerLevel) level, blockPos, MobSpawnType.TRIGGERED);
            itemStack.shrink(1);
            return InteractionResult.CONSUME;

        }

    }

}
