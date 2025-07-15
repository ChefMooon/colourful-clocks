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

public class EnableTickingTrigger extends SimpleCriterionTrigger<EnableTickingTrigger.TriggerInstance> {
    @Override
    public Codec<EnableTickingTrigger.TriggerInstance> codec() {
        return EnableTickingTrigger.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, EnableTickingTrigger.TriggerInstance::test);
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance
    {
        public static final Codec<EnableTickingTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
                builder -> builder.group(
                                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(EnableTickingTrigger.TriggerInstance::player))
                        .apply(builder, EnableTickingTrigger.TriggerInstance::new)
        );
        public static Criterion<EnableTickingTrigger.TriggerInstance> simple() {
            return ColourfulClocksAdvancements.ENABLE_TICKING_TRIGGER.get().createCriterion(
                    new EnableTickingTrigger.TriggerInstance(Optional.empty())
            );
        }

        public boolean test() {
            return true;
        }
    }
}
