package net.jcup.testmod.sound;

import net.jcup.testmod.JcupsMod;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;


public class ModSounds {

    public static final SoundEvent BRENIUM_BOMB_EXPLOSION = registerSoundEvent("brenium_bomb_explosion");
    public static final SoundEvent BRENIUM_BOMB_FUSE = registerSoundEvent("brenium_bomb_fuse");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(JcupsMod.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
    public static void registerSounds() {
        JcupsMod.LOGGER.info("Registering sounds for " + JcupsMod.MOD_ID);
    }
}
