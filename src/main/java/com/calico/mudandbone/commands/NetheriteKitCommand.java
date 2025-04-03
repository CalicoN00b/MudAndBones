package com.calico.mudandbone.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class NetheriteKitCommand {

    public NetheriteKitCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("kit").then(Commands.literal("netherite").executes((command) -> {
            return kitNetherite(command.getSource());
        })));
    }

    private int kitNetherite(CommandSourceStack source) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayer();

        player.addItem(new ItemStack(Items.NETHERITE_HELMET));
        player.addItem(new ItemStack(Items.NETHERITE_CHESTPLATE));
        player.addItem(new ItemStack(Items.NETHERITE_LEGGINGS));
        player.addItem(new ItemStack(Items.NETHERITE_BOOTS));

        return 1;
    }
    
}
