package hellfirepvp.astralsorcery.common.item.block;

import hellfirepvp.astralsorcery.common.block.network.BlockCollectorCrystalBase;
import hellfirepvp.astralsorcery.common.constellation.IConstellation;
import hellfirepvp.astralsorcery.common.constellation.IMajorConstellation;
import hellfirepvp.astralsorcery.common.entities.EntityItemHighlighted;
import hellfirepvp.astralsorcery.common.item.base.ItemHighlighted;
import hellfirepvp.astralsorcery.common.registry.RegistryItems;
import hellfirepvp.astralsorcery.common.util.nbt.NBTHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * This class is part of the Astral Sorcery Mod
 * The complete source code for this mod can be found on github.
 * Class: ItemCollectorCrystal
 * Created by HellFirePvP
 * Date: 01.08.2016 / 13:10
 */
public class ItemCollectorCrystal extends ItemBlockCustomName implements ItemHighlighted {

    public ItemCollectorCrystal(BlockCollectorCrystalBase collectorCrystalBase) {
        super(collectorCrystalBase);
        setMaxStackSize(1);
        setCreativeTab(RegistryItems.creativeTabAstralSorceryTunedCrystals);
    }

    @Override
    public boolean hasCustomEntity(ItemStack stack) {
        return true;
    }

    @Override
    public Entity createEntity(World world, Entity entity, ItemStack itemstack) {
        EntityItemHighlighted ei = new EntityItemHighlighted(world, entity.posX, entity.posY, entity.posZ, itemstack);
        BlockCollectorCrystalBase.CollectorCrystalType type = getType(itemstack);
        ei.applyColor(type.displayColor);
        ei.setPickupDelay(40);
        ei.motionX = entity.motionX;
        ei.motionY = entity.motionY;
        ei.motionZ = entity.motionZ;
        return ei;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        BlockCollectorCrystalBase.CollectorCrystalType type = getType(stack);
        if(type == BlockCollectorCrystalBase.CollectorCrystalType.CELESTIAL_CRYSTAL) {
            return RegistryItems.rarityCelestial;
        }
        return super.getRarity(stack);
    }

    public static void setType(ItemStack stack, BlockCollectorCrystalBase.CollectorCrystalType type) {
        NBTHelper.getPersistentData(stack).setInteger("collectorType", type.ordinal());
    }

    public static BlockCollectorCrystalBase.CollectorCrystalType getType(ItemStack stack) {
        return BlockCollectorCrystalBase.CollectorCrystalType.values()[NBTHelper.getPersistentData(stack).getInteger("collectorType")];
    }

    public static void setConstellation(ItemStack stack, IMajorConstellation constellation) {
        constellation.writeToNBT(NBTHelper.getPersistentData(stack));
    }

    public static IMajorConstellation getConstellation(ItemStack stack) {
        return (IMajorConstellation) IConstellation.readFromNBT(NBTHelper.getPersistentData(stack));
    }

}
