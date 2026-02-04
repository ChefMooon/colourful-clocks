package com.chefmooon.colourfulclocks.common.util;

import com.chefmooon.colourfulclocks.client.renderer.BornholmTopBlockEntityRenderer;
import com.chefmooon.colourfulclocks.common.block.TallMantelClockBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class CopperWeatheringUtil {
    public static final int WEATHERED_THRESHOLD = 6000; // 5 min to weather // TODO: make all clock blocks use this

    public static void spawnPendulumUpdateParticles(Level level, BlockPos pos, Direction facing, ParticleOptions particleData, ClockPendulumParticleType clockPendulumParticleType) {
        Vec3 centerPos = pos.getCenter();

        // Basic particle - spawning near the bottom of the pendulum
        for (int i = 0; i < 4; i++) {
            double offsetX = clockPendulumParticleType.baseOffsetX + centerPos.x();
            double offsetY = clockPendulumParticleType.baseOffsetY + centerPos.y();
            double offsetZ = clockPendulumParticleType.baseOffsetZ + centerPos.z();

            if (clockPendulumParticleType == ClockPendulumParticleType.TALL_MANTEL_CLOCK) {
                switch (facing) {
                    case SOUTH:
                        offsetZ = offsetZ - clockPendulumParticleType.facingOffset;
                        break;
                    case EAST:
                        offsetX = offsetX - clockPendulumParticleType.facingOffset;
                        break;
                    case WEST:
                        offsetX = offsetX + clockPendulumParticleType.facingOffset;
                        break;
                    case NORTH:
                        offsetZ = offsetZ + clockPendulumParticleType.facingOffset;
                        break;
                    default:
                        break;
                }
            }

            offsetX += (level.random.nextDouble() - 0.5) * 0.3;
            offsetY += (level.random.nextDouble() - 0.5) * 0.3;
            offsetZ += (level.random.nextDouble() - 0.5) * 0.3;

            level.addParticle(particleData,
                    offsetX,
                    offsetY,
                    offsetZ,
                    0.0, 0.0, 0.0);
        }

        // Additional particles for Bornholm Middle Pendulum
        if (clockPendulumParticleType == ClockPendulumParticleType.BORNHOLM_MIDDLE) {
            for (int i = 0; i < 3; i++) {
                double offsetX = clockPendulumParticleType.baseOffsetX + centerPos.x();
                double offsetY = clockPendulumParticleType.baseOffsetY + centerPos.y() + 0.15;
                double offsetZ = clockPendulumParticleType.baseOffsetZ + centerPos.z();

                offsetX += (level.random.nextDouble() - 0.5) * 0.1;
                offsetY += (level.random.nextDouble() - 0.5) * 0.3;
                offsetZ += (level.random.nextDouble() - 0.5) * 0.1;

                level.addParticle(particleData,
                        offsetX,
                        offsetY,
                        offsetZ,
                        0.0, 0.0, 0.0);
            }
        }
    }

    public enum ClockPendulumParticleType {
        BORNHOLM_MIDDLE(0.0, -0.05, 0.0, 0.0),
        TALL_MANTEL_CLOCK(0.0, -0.15, 0.0, 0.1);

        public final double baseOffsetX;
        public final double baseOffsetY;
        public final double baseOffsetZ;
        public final double facingOffset;

        ClockPendulumParticleType(double x, double y, double z, double facingOffset) {
            this.baseOffsetX = x;
            this.baseOffsetY = y;
            this.baseOffsetZ = z;
            this.facingOffset = facingOffset;
        }
    }

    public static void spawnPocketWatchUpdateParticles(Level level, BlockPos pos, Direction facing, ParticleOptions particleData, ClockParticleType clockParticleType) {
        // Special Cases:
        // WALL_CLOCK_MEDIUM needs an additional offset of 0.5 in X or Z depending on facing
        // TALL_MANTEL_CLOCK has the option of being placed on the wall. If so, it needs to add additional facingOffset
        // TODO: Refine. Some clocks have interesting particle spawning patterns
        Vec3 bottomCenter = pos.getBottomCenter();
        for (int i = 0; i < clockParticleType.particleCount; i++) {
            double offsetX = clockParticleType.baseOffsetX + bottomCenter.x();
            double offsetY = clockParticleType.baseOffsetY + bottomCenter.y();
            double offsetZ = clockParticleType.baseOffsetZ + bottomCenter.z();

            boolean isTallMantelWallMounted = clockParticleType == ClockParticleType.TALL_MANTEL_CLOCK && isWallMounted(level.getBlockState(pos));
            double tallMantelWallOffset = 0.32;

            switch (facing) {
                case SOUTH:
                    offsetZ = offsetZ - clockParticleType.facingOffset;
                    if (clockParticleType == ClockParticleType.WALL_CLOCK_MEDIUM) offsetX = offsetX + 0.5;
                    if (isTallMantelWallMounted) offsetZ = offsetZ + tallMantelWallOffset;
                    break;
                case EAST:
                    offsetX = offsetX - clockParticleType.facingOffset;
                    if (clockParticleType == ClockParticleType.WALL_CLOCK_MEDIUM) offsetZ = offsetZ - 0.5;
                    if (isTallMantelWallMounted) offsetX = offsetX + tallMantelWallOffset;
                    break;
                case WEST:
                    offsetX = offsetX + clockParticleType.facingOffset;
                    if (clockParticleType == ClockParticleType.WALL_CLOCK_MEDIUM) offsetZ = offsetZ + 0.5;
                    if (isTallMantelWallMounted) offsetX = offsetX - tallMantelWallOffset;
                    break;
                case NORTH:
                    offsetZ = offsetZ + clockParticleType.facingOffset;
                    if (clockParticleType == ClockParticleType.WALL_CLOCK_MEDIUM) offsetX = offsetX - 0.5;
                    if (isTallMantelWallMounted) offsetZ = offsetZ - (tallMantelWallOffset - 0.03);
                    break;
                default:
                    break;
            }

            offsetX += (level.random.nextDouble() - 0.5) * 0.15;
            offsetY += (level.random.nextDouble() - 0.5) * 0.15;
            offsetZ += (level.random.nextDouble() - 0.5) * 0.15;

            level.addParticle(particleData,
                    offsetX,
                    offsetY,
                    offsetZ,
                    0.0, 0.0, 0.0);
        }
        if (clockParticleType.isComplex) {

            float minuteRotation = BornholmTopBlockEntityRenderer.getMinuteHandRotation(0); // extract from render class to a common util class to be reused?
            float hourRotation = BornholmTopBlockEntityRenderer.getHourHandRotation(0);

            spawnComplexPocketWatchUpdateParticles(level, bottomCenter, facing, particleData, clockParticleType, clockParticleType.particleCount * 2, Math.toRadians(-minuteRotation), clockParticleType.maxMinuteRadius);
            spawnComplexPocketWatchUpdateParticles(level, bottomCenter, facing, particleData, clockParticleType, clockParticleType.particleCount, Math.toRadians(-hourRotation), clockParticleType.maxHourRadius);
        }
    }

    private static boolean isWallMounted(BlockState state) {
        return state.getValue(TallMantelClockBlock.WALL);
    }

    private static void spawnComplexPocketWatchUpdateParticles(Level level, Vec3 center, Direction facing, ParticleOptions particleData, ClockParticleType clockParticleType, int count, double angle, double maxRadius) {
        for (int i = 1; i <= count; i++) {
            double radius = (maxRadius / count) * i;

            double localX = Math.sin(angle) * radius;
            double localY = Math.cos(angle) * radius;

            double worldX = 0;
            double worldZ = 0;

            switch (facing) {
                case SOUTH -> {
                    worldX = localX;
                    worldZ = -clockParticleType.facingOffset;
                    if (clockParticleType == ClockParticleType.WALL_CLOCK_MEDIUM) worldX += 0.5;
                }
                case NORTH -> {
                    worldX = -localX;
                    worldZ = clockParticleType.facingOffset;
                    if (clockParticleType == ClockParticleType.WALL_CLOCK_MEDIUM) worldX -= 0.5;
                }
                case EAST -> {
                    worldZ = -localX;
                    worldX = -clockParticleType.facingOffset;
                    if (clockParticleType == ClockParticleType.WALL_CLOCK_MEDIUM) worldZ -= 0.5;
                }
                case WEST -> {
                    worldZ = localX;
                    worldX = clockParticleType.facingOffset;
                    if (clockParticleType == ClockParticleType.WALL_CLOCK_MEDIUM) worldZ += 0.5;
                }
            }

            double offsetX = clockParticleType.baseOffsetX + center.x() + worldX;
            double offsetY = clockParticleType.baseOffsetY + center.y() + localY;
            double offsetZ = clockParticleType.baseOffsetZ + center.z() + worldZ;

            offsetX += (level.random.nextDouble() - 0.5) * 0.05;
            offsetY += (level.random.nextDouble() - 0.5) * 0.05;
            offsetZ += (level.random.nextDouble() - 0.5) * 0.05;

            level.addParticle(particleData, offsetX, offsetY, offsetZ, 0.0, 0.0, 0.0);
        }
    }

    public enum ClockParticleType {
        ALARM_CLOCK(0.0, 0.3, 0.0, 0.2, false, 0.15, 0.15, 2),
        BORNHOLM_TOP(0.0, 0.5, 0.0, 0.29, true, 0.15, 0.15, 2),
        MANTEL_CLOCK(0.0, 0.3, 0.0, 0.18, false, 0.15, 0.15, 2),
        TALL_MANTEL_CLOCK(0.0, 0.7, 0.0, 0.2, false, 0.2, 0.15, 2),
        WALL_CLOCK_SMALL(0.0, 0.5, 0.0, -0.4, true, 0.2, 0.15, 2),
        WALL_CLOCK_MEDIUM(0.0, 0.0, 0.0, -0.4, true, 0.45, 0.15, 2),
        WALL_CLOCK_LARGE(0.0, 0.5, 0.0, -0.4, true, 0.7, 0.35, 3);

        public final double baseOffsetX;
        public final double baseOffsetY;
        public final double baseOffsetZ;
        public final double facingOffset;
        public final boolean isComplex;
        public final double maxMinuteRadius;
        public final double maxHourRadius;
        public final int particleCount;

        ClockParticleType(double x, double y, double z, double facingOffset, boolean isComplex, double maxMinuteRadius, double maxHourRadius, int particleCount) {
            this.baseOffsetX = x;
            this.baseOffsetY = y;
            this.baseOffsetZ = z;
            this.facingOffset = facingOffset;
            this.isComplex = isComplex;
            this.maxMinuteRadius = maxMinuteRadius;
            this.maxHourRadius = maxHourRadius;
            this.particleCount = particleCount;
        }
    }

}
