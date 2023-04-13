package net.somedom.pullback.enchantment;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.phys.Vec3;

public class PullbackEnchantment extends Enchantment {
    public PullbackEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public void doPostAttack(LivingEntity pAttacker, Entity pTarget, int pLevel) {
        if(!pAttacker.level.isClientSide) {
            ServerLevel world = ((ServerLevel) pAttacker.level);
            BlockPos position = pTarget.blockPosition();

            if (pLevel == 1) {

                Vec3 vector = pAttacker.position().subtract(pTarget.position()).normalize().scale(0.5D);
                pTarget.setDeltaMovement(vector.x(), vector.y(), vector.z());

            }

            if (pLevel == 2) {

                Vec3 vector = pAttacker.position().subtract(pTarget.position()).normalize().scale(1.D);
                pTarget.setDeltaMovement(vector.x(), vector.y(), vector.z());

            }

        }

        super.doPostAttack(pAttacker, pTarget, pLevel);
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }
}
