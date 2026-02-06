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

public class InsertHandbellTrigger extends SimpleCriterionTrigger<InsertHandbellTrigger.TriggerInstance> {
    @Override
    public Codec<InsertHandbellTrigger.TriggerInstance> codec() {
        return InsertHandbellTrigger.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, InsertHandbellTrigger.TriggerInstance::test);
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance
    {
        public static final Codec<InsertHandbellTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
                builder -> builder.group(
                                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(InsertHandbellTrigger.TriggerInstance::player))
                        .apply(builder, InsertHandbellTrigger.TriggerInstance::new)
        );
        public static Criterion<InsertHandbellTrigger.TriggerInstance> simple() {
            return ColourfulClocksAdvancements.INSERT_HANDBELL_TRIGGER.get().createCriterion(
                    new InsertHandbellTrigger.TriggerInstance(Optional.empty())
            );
        }

        public boolean test() {
            return true;
        }
    }
}
