package com.xg7plugins.extension;

import com.xg7plugins.events.Listener;
import com.xg7plugins.events.bukkitevents.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class ScoreListener implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        XG7ScoresExtension.getInstance().removePlayer(event.getPlayer());
    }


    @Override
    public boolean isEnabled() {
        return true;
    }
}
