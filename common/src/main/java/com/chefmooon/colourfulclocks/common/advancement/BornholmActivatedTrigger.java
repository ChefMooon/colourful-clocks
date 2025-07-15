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

public class BornholmActivatedTrigger extends SimpleCriterionTrigger<BornholmActivatedTrigger.TriggerInstance> {
    @Override
    public Codec<BornholmActivatedTrigger.TriggerInstance> codec() {
        return BornholmActivatedTrigger.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, BornholmActivatedTrigger.TriggerInstance::test);
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance
    {
        public static final Codec<BornholmActivatedTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
                builder -> builder.group(
                                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(BornholmActivatedTrigger.TriggerInstance::player))
                        .apply(builder, BornholmActivatedTrigger.TriggerInstance::new)
        );
        public static Criterion<BornholmActivatedTrigger.TriggerInstance> simple() {
            return ColourfulClocksAdvancements.BORNHOLM_ACTIVATED_TRIGGER.get().createCriterion(
                    new BornholmActivatedTrigger.TriggerInstance(Optional.empty())
            );
        }

        public boolean test() {
            return true;
        }
    }
}
