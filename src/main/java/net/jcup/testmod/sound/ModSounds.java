package net.jcup.testmod.sound;

import net.jcup.testmod.JcupsMod;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;


public class ModSounds {

    public static final SoundEvent BRENIUM_BOMB_EXPLOSION = registerSoundEvent("brenium_bomb_explosion");
    public static final SoundEvent BRENIUM_BOMB_FUSE = registerSoundEvent("brenium_bomb_fuse");
    public static final SoundEvent KATE_AMBIENT_1 = registerSoundEvent("kate_idle_1");
    public static final SoundEvent KATE_AMBIENT_2 = registerSoundEvent("kate_idle_2");
    public static final SoundEvent KATE_ANGRY = registerSoundEvent("kate_angry");
    public static final SoundEvent KATE_DEATH = registerSoundEvent("kate_death");
    public static final SoundEvent KATE_HURT = registerSoundEvent("kate_hurt");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(JcupsMod.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
    public static void registerSounds() {
        JcupsMod.LOGGER.info("Registering sounds for " + JcupsMod.MOD_ID);
    }
}
