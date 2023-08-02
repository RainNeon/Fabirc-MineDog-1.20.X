package net.rainneon.minedog.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.rainneon.minedog.Block.ModBlocks;
import net.rainneon.minedog.MineDogMod;

public class ModItemGroups {
    public static final ItemGroup MINEDOG_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(MineDogMod.MOD_ID, "minedog"),
            //使用 FabricItemGroup.builder()创建一个物品组构建器对象
            //displayName() 设置名字
            //Text.translatable("itemgroup.minedog")创建一个名为itemgroup.minedog的可翻译对象
            //.icon()设置图标，这里写了lambda表达式创建一个ItemStack对象，给构造函数里传入了一个可转换物品参数
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.minedog"))
                    .icon(() -> new ItemStack(ModItems.DOGCOIN)).entries((displayContext, entries) -> {
                        //普通物品
                        entries.add(ModItems.DOGCOIN);
                        entries.add(ModItems.COCONUT);

                        //矿物
                        entries.add(ModBlocks.DEEP_MINEDOG_ORE);
                        entries.add(ModBlocks.MINEDOG_ORE);
                        entries.add(ModBlocks.END_MINEDOG_ORE);
                        entries.add(ModBlocks.NETHER_MINEDOG_ORE);

                        entries.add(ModBlocks.MINEDOG_BLOCK);
                        entries.add(ModBlocks.RAW_MINEDOG_BLOCK);
                        entries.add(ModItems.RAW_MINEDOG);
                    }).build());
    public static void registerItemGroups(){
        MineDogMod.LOGGER.info("Registering Item Groups for"+MineDogMod.MOD_ID);
    }
}
