package dev.melncat.undecidedname;

import dev.melncat.undecidedname.fluid.MudFluidType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static dev.melncat.undecidedname.UndecidedName.MODID;

public class UndecidedNameFluids {
	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
	private static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, MODID);
	private static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, MODID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

	public static final RegistryObject<FluidType> MUD_FLUID_TYPE = FLUID_TYPES.register("mud", MudFluidType::new);

	public static final RegistryObject<ForgeFlowingFluid> MUD_FLUID = FLUIDS.register("mud_fluid", () -> new ForgeFlowingFluid.Source(mudFluidProperties()));

	public static final RegistryObject<ForgeFlowingFluid> MUD_FLUID_FLOWING = FLUIDS.register("mud_fluid_flowing", () -> new ForgeFlowingFluid.Flowing(mudFluidProperties()));

	public static final RegistryObject<LiquidBlock> MUD_FLUID_BLOCK = BLOCKS.register("mud_fluid_block",
		() -> new LiquidBlock(MUD_FLUID_FLOWING,
		BlockBehaviour.Properties.of().liquid().replaceable().noCollission().pushReaction(PushReaction.DESTROY).strength(100f).noLootTable())
	);

	public static final RegistryObject<Item> MUD_BUCKET = ITEMS.register("mud_bucket", () -> new BucketItem(MUD_FLUID, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));


	private static final ForgeFlowingFluid.Properties mudFluidProperties() {
		return new ForgeFlowingFluid.Properties(MUD_FLUID_TYPE, MUD_FLUID, MUD_FLUID_FLOWING).bucket(MUD_BUCKET).block(MUD_FLUID_BLOCK);
	}

	// Creates a creative tab with the id "examplemod:example_tab" for the example item, that is placed after the combat tab
	public static final RegistryObject<CreativeModeTab> FLUID_TAB = CREATIVE_MODE_TABS.register("fluid_tab", () -> CreativeModeTab.builder()
		.withTabsBefore(CreativeModeTabs.COMBAT)
		.icon(() -> MUD_BUCKET.get().getDefaultInstance())
		.displayItems((parameters, output) -> {
			output.accept(MUD_BUCKET.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
		}).build());

	// Add the example block item to the building blocks tab
	public static void addCreative(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
			event.accept(MUD_BUCKET);
		}
	}

	public static void register(IEventBus bus) {
		BLOCKS.register(bus);
		ITEMS.register(bus);
		CREATIVE_MODE_TABS.register(bus);
		FLUIDS.register(bus);
		FLUID_TYPES.register(bus);

	}
}
