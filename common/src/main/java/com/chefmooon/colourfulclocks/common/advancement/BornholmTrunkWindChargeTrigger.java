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

public class BornholmTrunkWindChargeTrigger extends SimpleCriterionTrigger<BornholmTrunkWindChargeTrigger.TriggerInstance> {
    @Override
    public Codec<BornholmTrunkWindChargeTrigger.TriggerInstance> codec() {
        return BornholmTrunkWindChargeTrigger.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, TriggerInstance::test);
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance
    {
        public static final Codec<BornholmTrunkWindChargeTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
                builder -> builder.group(
                                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(BornholmTrunkWindChargeTrigger.TriggerInstance::player))
                        .apply(builder, BornholmTrunkWindChargeTrigger.TriggerInstance::new)
        );
        public static Criterion<TriggerInstance> simple() {
            return ColourfulClocksAdvancements.BORNHOLM_TRUNK_WIND_CHARGE.get().createCriterion(
                    new BornholmTrunkWindChargeTrigger.TriggerInstance(Optional.empty())
            );
        }

        public boolean test() {
            return true;
        }
    }
}
