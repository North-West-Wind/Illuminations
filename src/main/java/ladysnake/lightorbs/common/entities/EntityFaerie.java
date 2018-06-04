package ladysnake.lightorbs.common.entities;

import elucent.albedo.lighting.ILightProvider;
import ladysnake.lightorbs.common.config.LightOrbsConfig;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;

@Optional.Interface(iface = "elucent.albedo.lighting.ILightProvider", modid = "albedo", striprefs = true)
public class EntityFaerie extends EntityWillOWisp implements ILightProvider {

    // Constructors
    public EntityFaerie(World world) {
        super(world);
        this.setSize(0.5f, 0.5f);
    }

    public EntityFaerie(World world, double x, double y, double z) {
        this(world);
        this.setPosition(x, y, z);
    }

    // Properties
    @Override
    public boolean getCanSpawnHere() {
        return LightOrbsConfig.spawnFaeries;
    }

    // Behaviour
    @Override
    public elucent.albedo.lighting.Light provideLight() {
        return elucent.albedo.lighting.Light.builder().pos(this).radius(6).color(250, 80, 170, 0.01f).build();
    }

}
