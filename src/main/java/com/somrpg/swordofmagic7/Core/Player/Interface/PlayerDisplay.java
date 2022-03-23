package com.somrpg.swordofmagic7.Core.Player.Interface;

import com.somrpg.swordofmagic7.Core.Generic.DecoContent;
import com.somrpg.swordofmagic7.Core.Player.Container.PlayerEntityContainer;
import com.somrpg.swordofmagic7.Core.SomCore;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static com.somrpg.swordofmagic7.Core.Generic.DecoFormat.ScaleDigit;

public interface PlayerDisplay {

    PlayerData getPlayerData();

    default Player getPlayer() {
        return getPlayerData().getPlayer();
    }

    void setSideBar(String key, List<String> data);
    void resetSideBar(String key);
    HashMap<String, List<String>> getSideBar();
    List<String> getScoreKey();
    default void startTickUpdate() {
        Player player = getPlayer();
        player.setHealthScale(20);
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective sidebarObject = board.registerNewObjective("Sidebar", "dummy", Component.text(DecoContent.decoDisplay("§bSword of Magic Ⅶ")));
        sidebarObject.setDisplaySlot(DisplaySlot.SIDEBAR);
        Team team = board.registerNewTeam(player.getName());
        team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.FOR_OWN_TEAM);
        team.addEntry(player.getName());
        team.setCanSeeFriendlyInvisibles(true);

        PlayerEntity playerEntity = getPlayerData().getPlayerEntity();

        SomCore.getSomTask().AsyncTaskTimer(() -> {
            String actionBar =
                    "§e§l《" + getPlayerData().getNick() + " Lv" + getPlayerData().getLevel() + "》" +
                    "§c§l《Health: " + ScaleDigit(getPlayerData().getHealth()) + "/" + ScaleDigit(getPlayerData().getMaxHealth()) + "》" +
                    "§b§l《Mana: " + ScaleDigit(getPlayerData().getMana()) + "/" + ScaleDigit(getPlayerData().getMaxMana()) + "》" +
                    "§a§l《Exp: " + getPlayerData().getExpPercentString() + "》";

            playerEntity.addHealth(getPlayerData().getHealthRegen()/20d);
            playerEntity.addMana(getPlayerData().getManaRegen()/20d);

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

        PotionEffect jumpPotion = new PotionEffect(PotionEffectType.JUMP, 21, 0, false, false, false);
        SomCore.getSomTask().SyncTaskTimer(() -> {
            player.addPotionEffect(jumpPotion);
            AttributeInstance maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
            if (maxHealth != null) maxHealth.setBaseValue(getPlayerData().getMaxHealth());
            player.setHealth(Math.max(0.5, Math.min(getPlayerData().getMaxHealth(), getPlayerData().getHealth())));
            player.setFoodLevel((int) Math.max(0, Math.min(20, Math.floor(getPlayerData().getMana()/getPlayerData().getMaxMana()*20))));
            getPlayerData().getPlayerStatistics().addPlayTime();
        }, 20);
    }

    static void setSideBar(Collection<Player> players, String key, List<String> data) {
        for (Player player : players) {
            if (player.isOnline()) {
                PlayerData.getData(player).getPlayerDisplay().setSideBar(key, data);
            }
        }
    }

    static void resetSideBar(Collection<Player> players, String key) {
        for (Player player : players) {
            if (player.isOnline()) {
                PlayerData.getData(player).getPlayerDisplay().resetSideBar(key);
            }
        }
    }
}
