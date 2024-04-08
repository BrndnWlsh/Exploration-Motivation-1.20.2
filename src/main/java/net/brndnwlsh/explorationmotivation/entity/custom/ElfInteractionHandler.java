package net.brndnwlsh.explorationmotivation.entity.custom;

import net.brndnwlsh.explorationmotivation.entity.ModEntities;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class ElfInteractionHandler {
    private static final Map<UUID, Long> cooldowns = new HashMap<>();
    private static final long COOLDOWN_DURATION = 2000; // Cooldown duration in milliseconds (e.g., 2 seconds)
    private static final Random random = new Random();
    private static final String[] dialogueOptions = {
            "Greetings from the elven realm.",
            "May the forest bless your journey.",
            "The trees whisper tales of your arrival.",
            "Welcome, traveler, to our sanctuary."
    };

    public void register() {
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (entity.getType() == ModEntities.ELF) {
                long currentTime = System.currentTimeMillis();
                long lastInteractionTime = cooldowns.getOrDefault(player.getUuid(), 0L);

                // Check if the cooldown has elapsed
                if (currentTime - lastInteractionTime >= COOLDOWN_DURATION) {
                    // If the cooldown has elapsed, handle the interaction
                    handleElfInteraction(player, entity);
                    // Update the cooldown for the player
                    cooldowns.put(player.getUuid(), currentTime);
                    return ActionResult.SUCCESS;
                } else {
                    return ActionResult.FAIL; // Indicate that the interaction failed due to cooldown
                }
            }
            return ActionResult.PASS; // Return PASS to allow other mods to handle the interaction
        });
    }

    private ActionResult handleElfInteraction(PlayerEntity player, net.minecraft.entity.Entity entity) {
        // Play a sound
        player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);

        // Get the display name of the elf entity
        Text elfName = entity.getCustomName();

        // Select a random dialogue option
        String randomDialogue = dialogueOptions[random.nextInt(dialogueOptions.length)];

        // Construct dialogue message with the elf's name (if available) and selected dialogue option
        Text message;
        if (elfName != null) {
            message = Text.of("<" + elfName.getString() + "> " + randomDialogue);
        } else {
            message = Text.of("<Elf> " + randomDialogue);
        }

        // Send the message to the player
        player.sendMessage(message, false);

        return ActionResult.SUCCESS; // Return SUCCESS if you handled the interaction
    }
}
