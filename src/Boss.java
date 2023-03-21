public class Boss extends Enemy {
    public Boss(int level, Direction direction) {
        super(level, direction);
        maxHP = level * random.nextInt(2, 12) + random.nextInt(1, 6);
        HP = maxHP;
        defence = level * random.nextInt(1, 3) + (random.nextInt(1, 6) / 6);
        attack = level * random.nextInt(1, 6) + level;
    }

}
