package net.rainneon.minedog;

import net.fabricmc.api.ClientModInitializer;
import net.rainneon.minedog.Block.ModBlocks;
import net.rainneon.minedog.item.ModItemGroups;
import net.rainneon.minedog.item.ModItems;

public class MineDogModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModItemGroups.registerItemGroups();

        ModItems.registerModItems();
        ModBlocks.registerModBlocks();

    }
}
