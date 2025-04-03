package com.calico.mudandbone.item.custom;

import com.calico.mudandbone.entity.custom.LightningScrollProjectileEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class LightningScrollItem extends Item implements ProjectileItem {

    public LightningScrollItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        
        Level level = context.getLevel();
        
        if (!level.isClientSide()) {

            ItemStack itemStack = context.getItemInHand();
            BlockPos blockPos = context.getClickedPos();

            EntityType.LIGHTNING_BOLT.spawn((ServerLevel) level, blockPos, MobSpawnType.TRIGGERED);
            itemStack.shrink(1);

        }

        return InteractionResult.sidedSuccess(level.isClientSide());

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);

        if (!level.isClientSide()) {
            LightningScrollProjectileEntity scrollProjectile = new LightningScrollProjectileEntity(level, player);
            scrollProjectile.setItem(itemStack);
            scrollProjectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 0F);
            level.addFreshEntity(scrollProjectile);
        }

        itemStack.consume(1, player);
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());

    }

    @Override
    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        LightningScrollProjectileEntity scrollProjectile = new LightningScrollProjectileEntity(level, pos.x(), pos.y(), pos.z());
        scrollProjectile.setItem(stack);
        return scrollProjectile;
    }

}
