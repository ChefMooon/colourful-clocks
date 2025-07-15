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

public class InsertPocketWatchTrigger extends SimpleCriterionTrigger<InsertPocketWatchTrigger.TriggerInstance> {
    @Override
    public Codec<InsertPocketWatchTrigger.TriggerInstance> codec() {
        return InsertPocketWatchTrigger.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, InsertPocketWatchTrigger.TriggerInstance::test);
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance
    {
        public static final Codec<InsertPocketWatchTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
                builder -> builder.group(
                                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(InsertPocketWatchTrigger.TriggerInstance::player))
                        .apply(builder, InsertPocketWatchTrigger.TriggerInstance::new)
        );
        public static Criterion<InsertPocketWatchTrigger.TriggerInstance> simple() {
            return ColourfulClocksAdvancements.INSERT_POCKET_WATCH_TRIGGER.get().createCriterion(
                    new InsertPocketWatchTrigger.TriggerInstance(Optional.empty())
            );
        }

        public boolean test() {
            return true;
        }
    }
}
