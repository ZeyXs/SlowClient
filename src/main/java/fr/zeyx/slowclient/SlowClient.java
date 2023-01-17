package fr.zeyx.slowclient;

import fr.zeyx.slowclient.event.KeyInputHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SlowClient implements ClientModInitializer {

	public static final String MODID = "slowclient";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitializeClient() {
		KeyInputHandler.register();
	}

}
