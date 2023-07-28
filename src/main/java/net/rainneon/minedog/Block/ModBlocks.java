package net.rainneon.minedog.Block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.rainneon.minedog.MineDogMod;

public class ModBlocks {
    //普通类
    public static final Block MINEDOG_BLOCK = registerBlock("minedog_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.AMETHYST_BLOCK)));
    public static final Block RAW_MINEDOG_BLOCK = registerBlock("raw_minedog_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    //矿石
    public static final Block MINEDOG_ORE = registerBlock("minedog_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.STONE).strength(2f),UniformIntProvider.create(2,5)));
    public static final Block DEEP_MINEDOG_ORE = registerBlock("deep_minedog_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.STONE).strength(2f),UniformIntProvider.create(2,5)));
    public static final Block NETHER_MINEDOG_ORE = registerBlock("nether_minedog_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.STONE).strength(2f),UniformIntProvider.create(2,5)));
    public static final Block END_MINEDOG_ORE = registerBlock("end_minedog_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.STONE).strength(2f),UniformIntProvider.create(2,5)));


    private static Block registerBlock(String name,Block block){
        registerBlockItem(name,block);
        return Registry.register(Registries.BLOCK,new Identifier(MineDogMod.MOD_ID,name),block);
    }
    private static Item registerBlockItem(String name,Block block){
        return Registry.register(Registries.ITEM,new Identifier(MineDogMod.MOD_ID,name),
                new BlockItem(block,new FabricItemSettings()));
    }
    public static void registerModBlocks(){
        MineDogMod.LOGGER.info("Registering ModBlocks for"+MineDogMod.MOD_ID);
    }
}
