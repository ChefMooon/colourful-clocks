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

public class InsertPendulumTrigger extends SimpleCriterionTrigger<InsertPendulumTrigger.TriggerInstance>{
    @Override
    public Codec<InsertPendulumTrigger.TriggerInstance> codec() {
        return InsertPendulumTrigger.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, InsertPendulumTrigger.TriggerInstance::test);
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance
    {
        public static final Codec<InsertPendulumTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
                builder -> builder.group(
                                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(InsertPendulumTrigger.TriggerInstance::player))
                        .apply(builder, InsertPendulumTrigger.TriggerInstance::new)
        );
        public static Criterion<InsertPendulumTrigger.TriggerInstance> simple() {
            return ColourfulClocksAdvancements.INSERT_PENDULUM_TRIGGER.get().createCriterion(
                    new InsertPendulumTrigger.TriggerInstance(Optional.empty())
            );
        }

        public boolean test() {
            return true;
        }
    }
}
