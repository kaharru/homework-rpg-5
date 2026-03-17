package com.narxoz.rpg;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.decorator.BasicAttack;
import com.narxoz.rpg.decorator.CriticalFocusDecorator;
import com.narxoz.rpg.decorator.FireRuneDecorator;
import com.narxoz.rpg.decorator.PoisonCoatingDecorator;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.facade.AdventureResult;
import com.narxoz.rpg.facade.DungeonFacade;
import com.narxoz.rpg.hero.HeroProfile;

public class Main {
    public static void main(String[] args) {

        // 1. make a hero and boss
        HeroProfile hero = new HeroProfile("Arthur the Knight", 150);
        BossEnemy boss = new BossEnemy("Ancient Dragon", 250, 20);

        // 2. use a decor

        // Комбинация А: basic attack
        AttackAction basic = new BasicAttack("Sword Strike", 20);

        // Комбинация Б: attack with attack
        AttackAction fireAttack = new FireRuneDecorator(basic);

        // Комбинация В: (Critical + Poison + Basic)
        AttackAction deadlyAttack = new CriticalFocusDecorator(
                new PoisonCoatingDecorator(basic)
        );

        // Комбинация e: the best attack
        AttackAction ultimateAttack = new FireRuneDecorator(
                new PoisonCoatingDecorator(
                        new CriticalFocusDecorator(new BasicAttack("Ultimate Smash", 25))
                )
        );

        // --- Decorator Proof
        System.out.println("--- Decorator Power Showcase ---");
        printAttackDetails(basic);
        printAttackDetails(fireAttack);
        printAttackDetails(deadlyAttack);
        printAttackDetails(ultimateAttack);

        // 3. start Facade through Dungeon
        System.out.println("\n--- Facade: Entering the Dungeon ---");
        DungeonFacade facade = new DungeonFacade().setRandomSeed(12345L);

        // attack to dragon
        AdventureResult result = facade.runAdventure(hero, boss, ultimateAttack);


        System.out.println("\n--- Adventure Summary ---");
        System.out.println("Winner: " + result.getWinner());
        System.out.println("Rounds fought: " + result.getRounds());
        System.out.println("Reward earned: " + result.getReward());

        System.out.println("\n--- Full Battle Log ---");
        for (String line : result.getLog()) {
            System.out.println(line);
        }

        System.out.println("\n=== Demo Complete ===");
    }


    private static void printAttackDetails(AttackAction action) {
        System.out.println("[" + action.getActionName() + "]");
    }
}
