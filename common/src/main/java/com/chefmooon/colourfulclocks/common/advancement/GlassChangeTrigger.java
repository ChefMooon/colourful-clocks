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

public class GlassChangeTrigger extends SimpleCriterionTrigger<GlassChangeTrigger.TriggerInstance> {
    @Override
    public Codec<GlassChangeTrigger.TriggerInstance> codec() {
        return GlassChangeTrigger.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, GlassChangeTrigger.TriggerInstance::test);
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance
    {
        public static final Codec<GlassChangeTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
                builder -> builder.group(
                                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(GlassChangeTrigger.TriggerInstance::player))
                        .apply(builder, GlassChangeTrigger.TriggerInstance::new)
        );
        public static Criterion<GlassChangeTrigger.TriggerInstance> simple() {
            return ColourfulClocksAdvancements.GLASS_CHANGE_TRIGGER.get().createCriterion(
                    new GlassChangeTrigger.TriggerInstance(Optional.empty())
            );
        }

        public boolean test() {
            return true;
        }
    }
}
