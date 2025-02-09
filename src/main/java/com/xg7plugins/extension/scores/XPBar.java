package com.xg7plugins.extension.xg7scores.scores;

import com.xg7plugins.XG7Plugins;
import com.xg7plugins.boot.Plugin;
import com.xg7plugins.extension.XG7ScoresExtension;
import com.xg7plugins.extension.xg7scores.Score;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public class XPBar extends Score {

    public XPBar(long delay, List<String> numbers, String id, Function<Player, Boolean> condition, Plugin plugin) {
        super(delay, numbers, id, condition, plugin);
        XG7ScoresExtension.getInstance().registerScore(this);
    }

    @Override
    public void update() {
            for (UUID id : super.getPlayers()) {
                Player player = Bukkit.getPlayer(id);
                if (player == null) continue;

                String level = updateText.get(indexUpdating).split(", ")[0];
                String progress = updateText.get(indexUpdating).split(", ")[1];

                if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                    level = PlaceholderAPI.setPlaceholders(player, level);
                    progress = PlaceholderAPI.setPlaceholders(player, progress);
                }

                player.setLevel(Integer.parseInt(level));
                player.setExp(Float.parseFloat(progress));
            }
    }
}
