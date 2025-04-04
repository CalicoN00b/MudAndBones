package com.calico.mudandbone.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

public class KitArmorCommand {

    public KitArmorCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
            Commands.literal("kitArmor").requires((adminLevel) -> adminLevel.hasPermission(2))
                .then(Commands.argument("kitLevel", StringArgumentType.word())
                    .executes(
                        (command) -> kit(command.getSource(), StringArgumentType.getString(command, "kitLevel"))
                    )
                )
        );
    }

    private int kit(CommandSourceStack source, String kLevel) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayer();

        HolderLookup.Provider A = source.getLevel().registryAccess();
        HolderLookup.RegistryLookup<Enchantment> B = A.lookupOrThrow(Registries.ENCHANTMENT);

        ItemStack helmet = new ItemStack(
            switch(kLevel) {
                case "netherite" -> Items.NETHERITE_HELMET;
                case "diamond" -> Items.DIAMOND_HELMET;
                case "iron" -> Items.IRON_HELMET;
                case "chainmail" -> Items.CHAINMAIL_HELMET;
                case "gold" -> Items.GOLDEN_HELMET;
                default -> Items.LEATHER_HELMET;
            }
        );
        helmet.enchant(B.get(Enchantments.MENDING).get(), 1);
        helmet.enchant(B.get(Enchantments.PROTECTION).get(), 4);
        helmet.enchant(B.get(Enchantments.UNBREAKING).get(), 3);
        helmet.enchant(B.get(Enchantments.RESPIRATION).get(), 3);
        helmet.enchant(B.get(Enchantments.AQUA_AFFINITY).get(), 1);
        helmet.enchant(B.get(Enchantments.THORNS).get(), 3);

        ItemStack chestplate = new ItemStack(
            switch(kLevel) {
                case "netherite" -> Items.NETHERITE_CHESTPLATE;
                case "diamond" -> Items.DIAMOND_CHESTPLATE;
                case "iron" -> Items.IRON_CHESTPLATE;
                case "chainmail" -> Items.CHAINMAIL_CHESTPLATE;
                case "gold" -> Items.GOLDEN_CHESTPLATE;
                default -> Items.LEATHER_CHESTPLATE;
            }
        );
        chestplate.enchant(B.get(Enchantments.MENDING).get(), 1);
        chestplate.enchant(B.get(Enchantments.PROTECTION).get(), 4);
        chestplate.enchant(B.get(Enchantments.UNBREAKING).get(), 3);
        chestplate.enchant(B.get(Enchantments.THORNS).get(), 3);

        ItemStack leggings = new ItemStack(
            switch(kLevel) {
                case "netherite" -> Items.NETHERITE_LEGGINGS;
                case "diamond" -> Items.DIAMOND_LEGGINGS;
                case "iron" -> Items.IRON_LEGGINGS;
                case "chainmail" -> Items.CHAINMAIL_LEGGINGS;
                case "gold" -> Items.GOLDEN_LEGGINGS;
                default -> Items.LEATHER_LEGGINGS;
            }
        );
        leggings.enchant(B.get(Enchantments.MENDING).get(), 1);
        leggings.enchant(B.get(Enchantments.PROTECTION).get(), 4);
        leggings.enchant(B.get(Enchantments.UNBREAKING).get(), 3);
        leggings.enchant(B.get(Enchantments.THORNS).get(), 3);
        leggings.enchant(B.get(Enchantments.SWIFT_SNEAK).get(), 3);

        ItemStack boots = new ItemStack(
            switch(kLevel) {
                case "netherite" -> Items.NETHERITE_BOOTS;
                case "diamond" -> Items.DIAMOND_BOOTS;
                case "iron" -> Items.IRON_BOOTS;
                case "chainmail" -> Items.CHAINMAIL_BOOTS;
                case "gold" -> Items.GOLDEN_BOOTS;
                default -> Items.LEATHER_BOOTS;
            }
        );
        boots.enchant(B.get(Enchantments.MENDING).get(), 1);
        boots.enchant(B.get(Enchantments.PROTECTION).get(), 4);
        boots.enchant(B.get(Enchantments.FEATHER_FALLING).get(), 4);
        boots.enchant(B.get(Enchantments.UNBREAKING).get(), 3);
        boots.enchant(B.get(Enchantments.DEPTH_STRIDER).get(), 3);
        boots.enchant(B.get(Enchantments.SOUL_SPEED).get(), 3);
        boots.enchant(B.get(Enchantments.THORNS).get(), 3);

        player.addItem(helmet);
        player.addItem(chestplate);
        player.addItem(leggings);
        player.addItem(boots);

        return 1;
    }
    
}
