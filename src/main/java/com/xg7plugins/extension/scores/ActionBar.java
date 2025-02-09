package com.xg7plugins.extension.scores;

import com.xg7plugins.XG7Plugins;
import com.xg7plugins.boot.Plugin;
import com.xg7plugins.extension.Score;
import com.xg7plugins.extension.XG7ScoresExtension;
import com.xg7plugins.utils.text.Text;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Getter
public class ActionBar extends Score {

    private static final List<UUID> sendActionBlackList = new ArrayList<>();

    public ActionBar(long delay, List<String> text, String id, Function<Player, Boolean> condition, Plugin plugin) {
        super(delay, text, id, condition, plugin);
        if (XG7Plugins.getMinecraftVersion() < 8) throw new RuntimeException("This version doesn't support ActionBar");
        XG7ScoresExtension.getInstance().registerScore(this);
    }

    @Override
    public void update() {
        for (UUID id : super.getPlayers()) {
            Player player = Bukkit.getPlayer(id);
            if (player == null) continue;
            if (containsPlayer(id)) continue;
            Text.detectLangs(player, plugin,super.updateText.get(indexUpdating)).join().send(player);
        }
    }

    public static boolean containsPlayer(UUID id) {
        return sendActionBlackList.contains(id);
    }
    public static void addToBlacklist(Player player) {
        sendActionBlackList.add(player.getUniqueId());
    }
    public static void removeFromBlacklist(UUID id) {
        sendActionBlackList.removeIf(id::equals);
    }



}
