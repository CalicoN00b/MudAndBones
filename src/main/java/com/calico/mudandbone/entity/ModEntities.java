package com.calico.mudandbone.entity;

import java.util.function.Supplier;

import com.calico.mudandbone.MudAndBone;
import com.calico.mudandbone.entity.custom.LightningScrollProjectileEntity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, MudAndBone.MOD_ID);

    public static final Supplier<EntityType<LightningScrollProjectileEntity>> LIGHTNING_SCROLL = 
        ENTITY_TYPES.register("lightning_scroll", () -> EntityType.Builder.<LightningScrollProjectileEntity>of(LightningScrollProjectileEntity::new, MobCategory.MISC)
            .sized(0.5f, 1.15f).build("lightning_scroll"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
    
}
