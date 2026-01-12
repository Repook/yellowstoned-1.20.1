package repook.yellowstoned.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import repook.yellowstoned.Yellowstoned;
import repook.yellowstoned.entity.custom.BeaverEntity;
import repook.yellowstoned.entity.custom.BisonEntity;
import repook.yellowstoned.entity.custom.MooseEntity;
import repook.yellowstoned.entity.custom.ReindeerEntity;

public class ModEntities {
    public static final EntityType<BeaverEntity> BEAVER = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Yellowstoned.MOD_ID, "beaver"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE,BeaverEntity::new)
                    .dimensions(EntityDimensions.fixed(0.7F, 0.7F)).build());

    public static final EntityType<ReindeerEntity> REINDEER = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Yellowstoned.MOD_ID, "reindeer"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE,ReindeerEntity::new)
                    .dimensions(EntityDimensions.fixed(0.7F, 1.8F)).build());

    public static final EntityType<MooseEntity> MOOSE = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Yellowstoned.MOD_ID, "moose"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE,MooseEntity::new)
                    .dimensions(EntityDimensions.fixed(1.0F, 2.0F)).build());

    public static final EntityType<BisonEntity> BISON = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Yellowstoned.MOD_ID, "bison"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE,BisonEntity::new)
                    .dimensions(EntityDimensions.fixed(1.5F, 1.8F)).build());
}
