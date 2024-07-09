package dev.melncat.undecidedname.fluid;

import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;

public class MudFluidType extends FluidType {
	public MudFluidType() {
		super(FluidType.Properties.create()
			.canDrown(true)
			.canSwim(true)
			.canConvertToSource(true)
			.canExtinguish(true)
			.canHydrate(false)
			.lightLevel(0)
			.density(2000)
			.viscosity(2000)
			.temperature(2000)
			.fallDistanceModifier(0.5f)
			.sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
			.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
			.supportsBoating(true)
		);
	}
}
