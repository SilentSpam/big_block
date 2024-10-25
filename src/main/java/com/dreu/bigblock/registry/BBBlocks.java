package com.dreu.bigblock.registry;

import com.dreu.bigblock.BigBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class BBBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, BigBlock.MODID);

    public static final RegistryObject<Block> BIG_BLOCK = registerBlockAndItem("big_block",
            () -> new DirectionalBlock(BlockBehaviour.Properties.copy(Blocks.STONE)) {
                @Override
                public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
                    return this.defaultBlockState().setValue(FACING, context.getNearestLookingDirection().getOpposite());
                }

                @Override
                protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
                    builder.add(FACING);
                }
            }
            , CreativeModeTab.TAB_BUILDING_BLOCKS
            );

    private static <T extends Block> RegistryObject<T> registerBlockAndItem(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> registeredBlock = BLOCKS.register(name, block);
        BBItems.ITEMS.register(name, () -> new BlockItem(registeredBlock.get(), new Item.Properties().tab(tab)));
        return registeredBlock;
    }
}
