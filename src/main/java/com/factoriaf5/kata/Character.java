    package com.factoriaf5.kata;

public class Character {
    private int health;
    private int level;
    private boolean isAlive;

    

    public Character() {
        this.health = 1000;
        this.level = 1;
        this.isAlive = true;
    }

    public void Damage(int damage) {
        health -= damage;
        if (health <= 0) {
            isAlive = false;
        }
    }

    public void heal(int amount) {
        if (isAlive) {
            health = Math.min(health + amount, 1000);
        }
        
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
    
}
