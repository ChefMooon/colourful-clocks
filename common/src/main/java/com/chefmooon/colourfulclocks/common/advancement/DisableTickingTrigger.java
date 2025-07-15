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

public class DisableTickingTrigger extends SimpleCriterionTrigger<DisableTickingTrigger.TriggerInstance> {
    @Override
    public Codec<DisableTickingTrigger.TriggerInstance> codec() {
        return DisableTickingTrigger.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, DisableTickingTrigger.TriggerInstance::test);
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance
    {
        public static final Codec<DisableTickingTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
                builder -> builder.group(
                                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(DisableTickingTrigger.TriggerInstance::player))
                        .apply(builder, DisableTickingTrigger.TriggerInstance::new)
        );
        public static Criterion<DisableTickingTrigger.TriggerInstance> simple() {
            return ColourfulClocksAdvancements.DISABLE_TICKING_TRIGGER.get().createCriterion(
                    new DisableTickingTrigger.TriggerInstance(Optional.empty())
            );
        }

        public boolean test() {
            return true;
        }
    }
}