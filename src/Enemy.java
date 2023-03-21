import java.util.Random;

public class Enemy extends Units {
    Random random = new Random();
    Direction direction;

    int level;

    public Enemy(int level, Direction direction) {
        this.level = level;
        maxHP = level * random.nextInt(2, 12);
        HP = maxHP;
        defence = level * random.nextInt(1, 3);
        attack = level * random.nextInt(1, 6);
        this.direction = direction;
        isAlive = true;
    }


    @Override
    public Direction die() {
        super.die();
        return direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public int getDefence() {
        return super.getDefence();
    }
}
