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

public class CopperWaxOnTrigger extends SimpleCriterionTrigger<CopperWaxOnTrigger.TriggerInstance> {
    @Override
    public Codec<CopperWaxOnTrigger.TriggerInstance> codec() {
        return CopperWaxOnTrigger.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, CopperWaxOnTrigger.TriggerInstance::test);
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance
    {
        public static final Codec<CopperWaxOnTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
                builder -> builder.group(
                                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(CopperWaxOnTrigger.TriggerInstance::player))
                        .apply(builder, CopperWaxOnTrigger.TriggerInstance::new)
        );
        public static Criterion<CopperWaxOnTrigger.TriggerInstance> simple() {
            return ColourfulClocksAdvancements.COPPER_WAX_ON_TRIGGER.get().createCriterion(
                    new CopperWaxOnTrigger.TriggerInstance(Optional.empty())
            );
        }

        public boolean test() {
            return true;
        }
    }
}