package net.jcup.testmod.entity.client;

import net.jcup.testmod.JcupsMod;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer BRENIUM_BOMB =
            new EntityModelLayer(new Identifier(JcupsMod.MOD_ID, "brenium_bomb"), "main");
    public static final EntityModelLayer KATE =
            new EntityModelLayer(new Identifier(JcupsMod.MOD_ID, "kate"), "main");
    public static final EntityModelLayer JOJO =
            new EntityModelLayer(new Identifier(JcupsMod.MOD_ID, "jojo"), "main");
}
