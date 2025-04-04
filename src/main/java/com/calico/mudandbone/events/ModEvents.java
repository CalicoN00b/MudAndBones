package com.calico.mudandbone.events;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.server.command.ConfigCommand;

import com.calico.mudandbone.MudAndBone;
import com.calico.mudandbone.commands.KitArmorCommand;

@EventBusSubscriber(modid = MudAndBone.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new KitArmorCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }
    
}
