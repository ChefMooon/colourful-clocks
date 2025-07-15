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

public class CopperWaxOffTrigger extends SimpleCriterionTrigger<CopperWaxOffTrigger.TriggerInstance> {
    @Override
    public Codec<CopperWaxOffTrigger.TriggerInstance> codec() {
        return CopperWaxOffTrigger.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, CopperWaxOffTrigger.TriggerInstance::test);
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance
    {
        public static final Codec<CopperWaxOffTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
                builder -> builder.group(
                                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(CopperWaxOffTrigger.TriggerInstance::player))
                        .apply(builder, CopperWaxOffTrigger.TriggerInstance::new)
        );
        public static Criterion<CopperWaxOffTrigger.TriggerInstance> simple() {
            return ColourfulClocksAdvancements.COPPER_WAX_OFF_TRIGGER.get().createCriterion(
                    new CopperWaxOffTrigger.TriggerInstance(Optional.empty())
            );
        }

        public boolean test() {
            return true;
        }
    }
}