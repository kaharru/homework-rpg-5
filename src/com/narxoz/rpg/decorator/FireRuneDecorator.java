package com.narxoz.rpg.decorator;

public class FireRuneDecorator extends ActionDecorator {
    public FireRuneDecorator(AttackAction wrappedAction) {
        super(wrappedAction);
    }
    @Override
    public String getActionName() {
        return "Flaming " + super.getActionName();
    }

    @Override
    public int getDamage() {
        // +15 hp to basic attack
        return super.getDamage() + 15;
    }

    @Override
    public String getEffectSummary() {
        return super.getEffectSummary() + " + Burns enemy";
    }
}
