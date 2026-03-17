package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

import java.util.Random;

public class BattleService {
    private Random random = new Random(1L);

    public BattleService setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public AdventureResult battle(HeroProfile hero, BossEnemy boss, AttackAction action) {
        AdventureResult result = new AdventureResult();
        int round = 1;

        result.addLine("Battle Start" + hero.getName() + " vs " + boss.getName() + "===");

        while (hero.isAlive() && boss.isAlive() && round <= 20) {
            result.addLine("\n[Round " + round + "]");

            // 1. hero star the battle
            int heroDamage = action.getDamage();
            boss.takeDamage(heroDamage);
            result.addLine(hero.getName() + " uses " + action.getActionName() +
                    "dealing" + heroDamage + "damage. (" + action.getEffectSummary());

            if (!boss.isAlive()) break;

            //  boss starts battle
            int bossDamage = boss.getAttackPower();
            if (random.nextInt(10) > 7) {
                bossDamage += 10;
                result.addLine("critical " + boss.getName() + " strikes back with rage");
            }

            hero.takeDamage(bossDamage);
            result.addLine(boss.getName() + "attacks" + hero.getName() + "for" + bossDamage + "damage");

            round++;
        }

        //  who win
        result.setRounds(round - 1);
        if (hero.isAlive() && !boss.isAlive()) {
            result.setWinner(hero.getName());
            result.addLine("\nVICTORY! The boss has been defeated!");
        } else {
            result.setWinner(boss.getName());
            result.addLine("\nDEFEAT! " + hero.getName() + "has fallen in battle...");
        }

        return result;
    }
}
