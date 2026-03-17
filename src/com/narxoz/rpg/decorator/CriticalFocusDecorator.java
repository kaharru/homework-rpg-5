package com.narxoz.rpg.decorator;

public class CriticalFocusDecorator extends ActionDecorator {
    public CriticalFocusDecorator(AttackAction wrappedAction) {
        super(wrappedAction);
    }

    @Override
    public String getActionName() {
        return "Critical " + super.getActionName();
    }

    @Override
    public int getDamage() {
        // 2x basic attack
        return super.getDamage() * 2;
    }

    @Override
    public String getEffectSummary() {
        return super.getEffectSummary() + "+ Critical Hit!";
    }
}
