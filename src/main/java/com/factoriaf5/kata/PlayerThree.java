package com.factoriaf5.kata;

public class PlayerThree {
    private int health;
    int level;
    private boolean alive;
    private int range;

    public PlayerThree(String type) {
        this.health = 1000;
        this.level = 1;
        this.alive = true;

        if (type.equalsIgnoreCase("Melee")) {
            this.range = 2; 
        } else if (type.equalsIgnoreCase("Ranged")) {
            this.range = 20; 
        } else {
            throw new IllegalArgumentException("Tipo de personaje no válido: use 'Melee' o 'Ranged'");
        }
    }

    public void Damage(PlayerThree target, int damage, int distance) {
        if (target == this || distance > this.range) {
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

    public int getRange() {
        return range;
    }
}
