package com.calico.mudandbone.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class HeartScrollItem extends Item {

    public HeartScrollItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);        
        float currentMaxHealth = player.getMaxHealth();

        if (currentMaxHealth < 60) {
            if (!level.isClientSide()) {
            
                AttributeMap playerAttributes = player.getAttributes();
    
                playerAttributes.getInstance(Attributes.MAX_HEALTH).setBaseValue(currentMaxHealth + 2);
    
            }        

            itemStack.consume(1, player);
            return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
        } else {
            return InteractionResultHolder.pass(itemStack);
        }

    }
    
}
