package com.somrpg.swordofmagic7.Core.Player.Container;

import com.somrpg.swordofmagic7.Core.Generic.DecoContent;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerDisplay;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerEntity;
import com.somrpg.swordofmagic7.Core.SomCore;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static com.somrpg.swordofmagic7.Core.Generic.DecoFormat.ScaleDigit;

public class PlayerDisplayContainer implements PlayerDisplay {

    private final PlayerData playerData;
    private final Player player;
    private final List<String> ScoreKey = new ArrayList<>();
    private final HashMap<String, List<String>> SideBar = new HashMap<>();

    PlayerDisplayContainer(PlayerData playerData) {
        this.playerData = playerData;
        player = playerData.getPlayer();

        startTickUpdate();
    }

    @Override
    public PlayerData getPlayerData() {
        return playerData;
    }

    @Override
    public void setSideBar(String key, List<String> data) {
        SideBar.put(key, data);
    }

    @Override
    public void resetSideBar(String key) {
        SideBar.remove(key);
    }

    @Override
    public HashMap<String, List<String>> getSideBar() {
        return SideBar;
    }

    @Override
    public void startTickUpdate() {
        Player player = getPlayer();
        player.setHealthScale(20);
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective sidebarObject = board.registerNewObjective("Sidebar", "dummy", Component.text(DecoContent.decoDisplay("§bSword of Magic Ⅶ")));
        sidebarObject.setDisplaySlot(DisplaySlot.SIDEBAR);
        player.setScoreboard(board);
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


            for (String scoreName : ScoreKey) {
                board.resetScores(scoreName);
            }
            ScoreKey.clear();
            setSideBar("Mel", Collections.singletonList(DecoContent.decoLore("所持金") + getPlayerData().getMel() + "メル"));
            int i = 15;
            for (List<String> textList : getSideBar().values()) {
                ScoreKey.addAll(textList);
            }
            for (String scoreName : ScoreKey) {
                Score sidebarScore = sidebarObject.getScore(scoreName);
                sidebarScore.setScore(i);
                i--;
                if (i < 0) break;
            }
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
}
