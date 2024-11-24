package com.factoriaf5.kata;

public class Player {
    private int health;
    int level;
    private boolean alive;

    public Player() {
        this.health = 1000;
        this.level = 1;
        this.alive = true;
    }

    public void Damage(Player target, int damage) {
        if (target == this) {
            return;
        }

        if (target.level >= this.level + 5) {
            damage /= 2;
        } else if (target.level <= this.level - 5) {
            damage = (int) (damage * 1.5);
        }

        target.health -= damage;
        if (target.health <= 0) {
            target.alive = false;
            target.health = 0;
        }
    }

    public void healSelf(int heal) {
        if (!this.alive) {
            return;
        }
        this.health = Math.min(this.health + heal, 1000);
    }

    public int getHealth() {
        return health;
    }

    public boolean isAlive() {
        return alive;
    }
}