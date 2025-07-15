package com.chefmooon.colourfulclocks.common.advancement;

import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksAdvancements;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;

import java.util.Optional;

public class BornholmTrunkGlassChangeTrigger extends SimpleCriterionTrigger<BornholmTrunkGlassChangeTrigger.TriggerInstance>{
    @Override
    public Codec<BornholmTrunkGlassChangeTrigger.TriggerInstance> codec() {
        return BornholmTrunkGlassChangeTrigger.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, BornholmTrunkGlassChangeTrigger.TriggerInstance::test);
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance
    {
        public static final Codec<BornholmTrunkGlassChangeTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
                builder -> builder.group(
                                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(BornholmTrunkGlassChangeTrigger.TriggerInstance::player))
                        .apply(builder, BornholmTrunkGlassChangeTrigger.TriggerInstance::new)
        );
        public static Criterion<BornholmTrunkGlassChangeTrigger.TriggerInstance> simple() {
            return ColourfulClocksAdvancements.BORNHOLM_TRUNK_GLASS_CHANGE.get().createCriterion(
                    new BornholmTrunkGlassChangeTrigger.TriggerInstance(Optional.empty())
            );
        }

        public boolean test() {
            return true;
        }
    }
}
