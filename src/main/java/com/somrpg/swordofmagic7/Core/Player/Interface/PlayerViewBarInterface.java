package com.somrpg.swordofmagic7.Core.Player.Interface;

import com.somrpg.swordofmagic7.Core.Generic.DecoContent;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerDataInterface;
import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import com.somrpg.swordofmagic7.Core.Player.PlayerEntity;
import com.somrpg.swordofmagic7.Core.SomCore;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public interface PlayerViewBarInterface extends PlayerDataInterface {

    void setSideBar(String key, List<String> data);
    void resetSideBar(String key);
    HashMap<String, List<String>> getSideBar();
    List<String> getScoreKey();
    default void startTickUpdate() {
        Player player = getPlayer();
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective sidebarObject = board.registerNewObjective("Sidebar", "dummy", DecoContent.decoDisplay("§bSword of Magic Ⅶ"));
        sidebarObject.setDisplaySlot(DisplaySlot.SIDEBAR);
        Team team = board.registerNewTeam(player.getName());
        team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.FOR_OWN_TEAM);
        team.addEntry(player.getName());
        team.setCanSeeFriendlyInvisibles(true);

        SomCore.getSomTask().AsyncTaskTimer(() -> {
            PlayerEntity playerEntity = getPlayerData().getPlayerEntity();
            double MaxHealth = playerEntity.getMaxHealth();
            double HealthRegen = playerEntity.getHealthRegen();
            double Health = playerEntity.getHealth();
            double MaxMana = playerEntity.getMaxMana();
            double ManaRegen = playerEntity.getManaRegen();
            double Mana = playerEntity.getMana();
            String actionBar = "§c§l《Health: " + Health + "/" + MaxHealth + "》"
                    + "§b§l《Mana: " + Mana + "/" + MaxMana + "》";

            playerEntity.addHealth(HealthRegen/100d);
            playerEntity.addHealth(ManaRegen/100d);

            player.sendActionBar(Component.text(actionBar));

            for (List<String> textList : getSideBar().values()) {
                getScoreKey().addAll(textList);
            }
            int i = 15;
            for (String scoreName : getScoreKey()) {
                Score sidebarScore = sidebarObject.getScore(scoreName);
                sidebarScore.setScore(i);
                i--;
                if (i < 0) break;
            }
            player.setScoreboard(board);
        }, 2);
    }

    static void setSideBar(Collection<Player> players, String key, List<String> data) {
        for (Player player : players) {
            if (player.isOnline()) {
                PlayerData.getData(player).getPlayerViewBar().setSideBar(key, data);
            }
        }
    }

    static void resetSideBar(Collection<Player> players, String key) {
        for (Player player : players) {
            if (player.isOnline()) {
                PlayerData.getData(player).getPlayerViewBar().resetSideBar(key);
            }
        }
    }
}
