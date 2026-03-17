package com.narxoz.rpg.facade;

public class RewardService {
    public String determineReward(AdventureResult battleResult) {
        if (battleResult == null || battleResult.getWinner() == null) {
            return "No reward for an unfinished battle.";
        }

        if (!battleResult.getWinner().toLowerCase().contains("boss")) {
            return "Epic Loot: [Sword of Patterns] and 500 Gold!";
        } else {
            return "Nothing but a painful lesson.";
        }
    }
}
