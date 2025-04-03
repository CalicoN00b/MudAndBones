package com.calico.mudandbone.item;

import com.calico.mudandbone.MudAndBone;
import com.calico.mudandbone.item.custom.HeartScrollItem;
import com.calico.mudandbone.item.custom.LightningScrollItem;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MudAndBone.MOD_ID);

    public static final DeferredItem<Item> LIGHTNING_SCROLL = ITEMS.register("lightning_scroll", 
        () -> new LightningScrollItem(new Item.Properties().stacksTo(16)));
    
    public static final DeferredItem<Item> HEART_SCROLL = ITEMS.register("heart_scroll", 
        () -> new HeartScrollItem(new Item.Properties().stacksTo(16)));
        
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
