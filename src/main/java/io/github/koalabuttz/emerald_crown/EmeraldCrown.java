package io.github.koalabuttz.emerald_crown;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmeraldCrown implements ModInitializer {


	public static final String MOD_ID = "emerald_crown";
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final ArmorMaterial EMERALD_MATERIAL = new ArmorMaterial() {
		@Override
		public int getDurability(EquipmentSlot slot) {
			return 0;
		}

		@Override
		public int getProtectionAmount(EquipmentSlot slot) {
			return 2;
		}

		@Override
		public int getEnchantability() {
			return 0;
		}

		@Override
		public SoundEvent getEquipSound() {
			return Blocks.ANVIL.getSoundGroup(Blocks.ANVIL.getDefaultState()).getPlaceSound();
		}

		@Override
		public Ingredient getRepairIngredient() {
			return null;
		}
		@Override
		public String getName() {
			return "emerald_crown";
		}
		@Override
		public Identifier getTexture() {
			return new Identifier("emerald_crown", "textures/item/emerald_crown");
		}
		@Override
		public float getToughness() {
			return 0;
		}

		@Override
		public float getKnockbackResistance() {
			return 0;
		}



	};
	public static final Item EmeraldCrown_Item = new ArmorItem(EMERALD_MATERIAL, EquipmentSlot.HEAD, new QuiltItemSettings().maxCount(1).fireproof().maxDamage(0));


	@Override
	public void onInitialize(ModContainer mod) {
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), MOD_ID), EmeraldCrown_Item);
		Registry.register(Registries.STATUS_EFFECT, new Identifier(mod.metadata().id(), "RoyalPresenceEffect"), RoyalPresenceEffect.INSTANCE);

		/*ItemStack headSlot = player.getInventory().armor.get(EquipmentSlot.HEAD.getEntitySlotId());
		if (headSlot.getItem() == EmeraldCrown_Item) {
			player.addStatusEffect(new StatusEffectInstance(RoyalPresenceEffect.INSTANCE, Integer.MAX_VALUE));
			ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
				content.addAfter(Items.GOLDEN_LEGGINGS, EmeraldCrown_Item);
			});*/

		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());
	}
}
