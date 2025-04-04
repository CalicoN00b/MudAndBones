package com.calico.mudandbone.item.custom;

import java.util.List;

import com.calico.mudandbone.entity.custom.LightningScrollProjectileEntity;
import com.calico.mudandbone.item.ModItems;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class LightningScrollItem extends Item implements ProjectileItem {

    public LightningScrollItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);

        if (!level.isClientSide()) {
            LightningScrollProjectileEntity scrollProjectile = new LightningScrollProjectileEntity(level, player);
            scrollProjectile.setItem(new ItemStack(ModItems.LIGHTNING_BOLT.get()));
            scrollProjectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 0F);
            level.addFreshEntity(scrollProjectile);
        }

        itemStack.consume(1, player);
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());

    }

    @Override
    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        LightningScrollProjectileEntity scrollProjectile = new LightningScrollProjectileEntity(level, pos.x(), pos.y(), pos.z());
        scrollProjectile.setItem(new ItemStack(ModItems.LIGHTNING_BOLT.get()));
        return scrollProjectile;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.mud_and_bone.lightning_scroll"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

}
