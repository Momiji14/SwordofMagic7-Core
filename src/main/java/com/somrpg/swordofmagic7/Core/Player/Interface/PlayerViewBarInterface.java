package com.somrpg.swordofmagic7.Core.Player.Interface;

import com.somrpg.swordofmagic7.Core.Generic.DecoContent;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerDataInterface;
import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import com.somrpg.swordofmagic7.Core.Player.PlayerEntity;
import com.somrpg.swordofmagic7.Core.SomCore;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static com.somrpg.swordofmagic7.Core.Generic.DecoFormat.ScaleDigit;

public interface PlayerViewBarInterface extends PlayerDataInterface {

    void setSideBar(String key, List<String> data);
    void resetSideBar(String key);
    HashMap<String, List<String>> getSideBar();
    List<String> getScoreKey();
    default void startTickUpdate() {
        Player player = getPlayer();
        player.setHealthScale(40);
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective sidebarObject = board.registerNewObjective("Sidebar", "dummy", DecoContent.decoDisplay("§bSword of Magic Ⅶ"));
        sidebarObject.setDisplaySlot(DisplaySlot.SIDEBAR);
        Team team = board.registerNewTeam(player.getName());
        team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.FOR_OWN_TEAM);
        team.addEntry(player.getName());
        team.setCanSeeFriendlyInvisibles(true);

        PlayerEntity playerEntity = getPlayerData().getPlayerEntity();

        SomCore.getSomTask().AsyncTaskTimer(() -> {
            double maxHealth = playerEntity.getMaxHealth();
            double healthRegen = playerEntity.getHealthRegen();
            double health = playerEntity.getHealth();
            double maxMana = playerEntity.getMaxMana();
            double manaRegen = playerEntity.getManaRegen();
            double mana = playerEntity.getMana();
            String actionBar = "§c§l《Health: " + ScaleDigit(health) + "/" + ScaleDigit(maxHealth) + "》"
                    + "§b§l《Mana: " + ScaleDigit(mana) + "/" + ScaleDigit(maxMana) + "》";

            playerEntity.addHealth(healthRegen/10d);
            playerEntity.addMana(manaRegen/10d);

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

        SomCore.getSomTask().SyncTaskTimer(() -> {
            double maxHealth = playerEntity.getMaxHealth();
            double health = playerEntity.getHealth();
            double maxMana = playerEntity.getMaxMana();
            double mana = playerEntity.getMana();
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
            player.setHealth(Math.max(0.5, Math.min(maxHealth, health)));
            player.setFoodLevel((int) Math.max(0, Math.min(20, Math.floor(mana/maxMana))));
        }, 20);
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
