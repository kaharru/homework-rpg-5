package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

public class PreparationService {
    public String prepare(HeroProfile hero, BossEnemy boss, AttackAction action) {
        if (hero == null || boss == null || action == null) {
            return "Preparation failed: Missing combatants or action!";
        }
        if (!hero.isAlive()) return "Preparation failed: Hero is already fallen!";

        return "Preparation complete: " + hero.getName() + " is ready to use " +
                action.getActionName() + " against " + boss.getName() + ".";
    }
}
