package com.calico.mudandbone.item.custom;

import java.util.List;

import com.calico.mudandbone.entity.custom.LightningBoltProjectileEntity;
import com.calico.mudandbone.item.ModItems;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class LightningScrollItem extends Item implements ProjectileItem {

    public LightningScrollItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);

        if (!level.isClientSide()) {

            if (!player.isCrouching() || !player.onGround()) {
                LightningBoltProjectileEntity boltProjectile = new LightningBoltProjectileEntity(level, player);
                boltProjectile.setItem(new ItemStack(ModItems.LIGHTNING_BOLT.get()));
                boltProjectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 0F);
                level.addFreshEntity(boltProjectile);   
            } else {
                BlockPos playerPos = player.blockPosition();

                for (int i = 0; i < 10; i++) {
                    int dx = (int) (Math.random() * 21) - 10;
                    int dz = (int) (Math.random() * 21) - 10;

                    EntityType.LIGHTNING_BOLT.spawn((ServerLevel) level, playerPos.offset(dx, 0, dz), MobSpawnType.TRIGGERED);
                }
            }

        }

        itemStack.consume(1, player);
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());

    }

    @Override
    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        LightningBoltProjectileEntity boltProjectile = new LightningBoltProjectileEntity(level, pos.x(), pos.y(), pos.z());
        boltProjectile.setItem(new ItemStack(ModItems.LIGHTNING_BOLT.get()));
        return boltProjectile;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.mud_and_bone.lightning_scroll"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

}
