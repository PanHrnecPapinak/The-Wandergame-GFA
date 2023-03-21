import java.util.Random;

public class Hero extends Units{
    Random random = new Random();
    int level;

    Direction enemyDirection;

    public Hero() {
        maxHP = 20 + random.nextInt(3,19);
        HP = maxHP;
        defence = random.nextInt(2,13);
        attack = 5 + random.nextInt(1,7);
        this.level = 1;

    }

    public void fight(Enemy enemy){
        System.out.println("bum bum bum!");
        int myAttack = attack + random.nextInt(2,13);
        if (myAttack > enemy.getDefence()){
            int dmgDone = myAttack - enemy.getDefence();
            if (enemy.getHP() - dmgDone <= 0){
                enemyDirection = enemy.die();
                enemy.setHP(0);
            } else {
                enemy.setHP(getHP() - dmgDone);
            }
        } else {
            System.out.println("He defends!");
        }

        //if enemy is alive, he strikes back

        if (enemy.isAlive()){
            int enemyAttack = enemy.getAttack() + random.nextInt(2,13);
            if (enemyAttack > defence){
              int dmgDone = enemyAttack - defence;
              if (HP - dmgDone <= 0){
                  die();
              } else {
                  HP = HP - dmgDone;
              }
            } else {
                System.out.println("Enemy missed!");
            }


        } else {
            System.out.println("Dead meat can't strike back");
        }
    }
    public void fightBoss(Boss enemy){
        System.out.println("bum bum bum!");
        int myAttack = attack + random.nextInt(2,13);
        if (myAttack > enemy.getDefence()){
            int dmgDone = myAttack - enemy.getDefence();
            if (enemy.getHP() - dmgDone <= 0){
                enemyDirection = enemy.die();
                enemy.setHP(0);
                levelUP();
            } else {
                enemy.setHP(getHP() - dmgDone);
            }
        } else {
            System.out.println("He defends!");
        }

        //if enemy is alive, he strikes back

        if (enemy.isAlive()){
            int enemyAttack = enemy.getAttack() + random.nextInt(2,13);
            if (enemyAttack > defence){
              int dmgDone = enemyAttack - defence;
              if (HP - dmgDone <= 0){
                  die();
              } else {
                  HP = HP - dmgDone;
              }
            } else {
                System.out.println("Enemy missed!");
            }


        } else {
            System.out.println("Dead meat can't strike back");
        }
    }

    private void levelUP() {
        level++;
        maxHP = maxHP + random.nextInt(1,7);
        attack = attack + random.nextInt(1,7);
        defence = defence + random.nextInt(1,7);
        heal();
    }

    protected void heal() {
        int heal = random.nextInt(1,11);
        if (heal == 1){
            HP = maxHP;
            System.out.println("heal max");
        }
        if (heal > 6){
            HP = HP + ((100/3 * maxHP)/100);
            System.out.println("heal 1/3");
        } else if (heal != 1) {
            HP = HP + ((100/10 * maxHP)/100);
            System.out.println("heal 1/10");
        }
        if (HP > maxHP){
            HP = maxHP;
        }
    }

    @Override
    public String toString() {
        return "Hero level: " + level + "   HP:" + HP + "/" + maxHP + "   defence:" + defence + "   attack:" + attack;
    }
}
