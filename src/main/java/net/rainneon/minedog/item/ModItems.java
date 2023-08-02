package net.rainneon.minedog.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.rainneon.minedog.MineDogMod;
import net.rainneon.minedog.item.custom.MetalDetectorItem;

public class ModItems {
    public static final Item DOGCOIN=registerItem("dogcoin",new Item(new FabricItemSettings()));
    public static final Item COCONUT=registerItem("coconut",new Item(new FabricItemSettings()));
    public static final Item RAW_MINEDOG=registerItem("raw_minedog",new Item(new FabricItemSettings()));
    public static final Item METAL_DETECTOR = registerItem("metal_detector",
            new MetalDetectorItem(new FabricItemSettings().maxDamage(64)));
    public static void addItemToIngredientTabItemGroup(FabricItemGroupEntries entries){

        entries.add(DOGCOIN);
        entries.add(COCONUT);
    }
    private  static Item registerItem(String name,Item item){
        return Registry.register(Registries.ITEM,new Identifier(MineDogMod.MOD_ID,name),item);
    }
    public static void registerModItems(){
        MineDogMod.LOGGER.info("Registering Mod Items for"+MineDogMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemToIngredientTabItemGroup);
    }
}
