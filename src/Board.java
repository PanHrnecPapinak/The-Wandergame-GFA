import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

public class Board extends JComponent implements KeyListener {

    int testBoxX;
    int testBoxY;
    Map newMap;
    Tile HeroTileSaved;
    Hero myHero;
    boolean battleActive = false;
    Enemy enemy = null;
    Boss enemyBoss = null;
    int level = 1;
    String heroStatsToPrint;


    public Board() {
        testBoxX = 0;
        testBoxY = 720; // coordinates of test box

        // set the size of your draw board
        setPreferredSize(new Dimension(720, 792));
        setVisible(true);


        makeNewMap(level);
        HeroTileSaved = getNewMap().getHeroLocation();
        this.myHero = new Hero();
        heroStatsToPrint = myHero.toString();


    }

    public void makeNewMap(int level) {
        this.newMap = new Map();
        this.newMap.fillTheMap();
        this.newMap.fillRandomMap(level);
        this.newMap.fillTheEnemy(level);
        this.newMap.addHPpotion(level);
        HeroTileSaved = getNewMap().getHeroLocation();
        battleActive = false;
        enemyBoss = null;
        enemy = null;
    }


    public void setNewMap(Map newMap) {
        this.newMap = newMap;
    }

    public Map getNewMap() {
        return newMap;
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);


        graphics.fillRect(testBoxX, testBoxY, 720, 100); // size of test box

        Font font = new Font("Serif", Font.PLAIN, 30);
        graphics.setFont(font);
        graphics.setColor(Color.WHITE);
        graphics.drawString(heroStatsToPrint, 20, 765);
        // here you have a 720x720 canvas
        // you can create and draw an image using the class below e.g.

        //PositionedImage image = new PositionedImage("src/yourimage.png", 500, 500);
        //image.draw(graphics);


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                PositionedImage image = new PositionedImage(newMap.playingMap[i][j].getTileStatusString(), newMap.playingMap[i][j].getPosX(), newMap.playingMap[i][j].getPosY());
                image.draw(graphics);
                if (newMap.playingMap[i][j].getTileStatus() != TileStatus.EMPTY || newMap.playingMap[i][j].getTileStatus() != TileStatus.FILLED_WALL) { // when I draw something else than a wall or empty Tile I draw floor under it.
                    PositionedImage image2 = new PositionedImage("src/images/floor.png", newMap.playingMap[i][j].getPosX(), newMap.playingMap[i][j].getPosY());
                    image2.draw(graphics);
                    PositionedImage image3 = new PositionedImage(newMap.playingMap[i][j].getTileStatusString(), newMap.playingMap[i][j].getPosX(), newMap.playingMap[i][j].getPosY());
                    image3.draw(graphics);
                }
            }
        }
    }


    public static void main(String[] args) {
        // Here is how you set up a new window and adding our board to it

        JFrame frame = new JFrame("RPG Game");
        Board board = new Board();
        frame.add(board);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        // Here is how you can add a key event listener
        // The board object will be notified when hitting any key
        // with the system calling one of the below 3 methods
        frame.addKeyListener(board);
        // Notice (at the top) that we can only do this
        // because this Board class (the type of the board object) is also a KeyListener


    }


    // To be a KeyListener the class needs to have these 3 methods in it
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    // But actually we can use just this one for our goals here
    @Override
    public void keyReleased(KeyEvent e) {
        // When the up or down keys hit, we change the position of our box
        if (e.getKeyCode() == KeyEvent.VK_UP) {

            if (checkHeroForBordersUP() == true) {
                if (battleActive) {
                    System.out.println("Cant move must FIGHT");
                } else if (newMap.playingMap[(HeroTileSaved.getPosX()) / 72][(HeroTileSaved.getPosY()) / 72 - 1].tileStatus == TileStatus.FILLED_ENEMY) {
                    System.out.println("FIGHT");
                    battleActive = true;
                    enemy = new Enemy(level, Direction.UP);
                    newMap.getPlayingMap()[(HeroTileSaved.getPosX()) / 72][(HeroTileSaved.getPosY()) / 72].setTileStatus(TileStatus.FILLED_HERO_UP);
                } else if (newMap.playingMap[(HeroTileSaved.getPosX()) / 72][(HeroTileSaved.getPosY()) / 72 - 1].tileStatus == TileStatus.FILLED_BOSS) {
                    System.out.println("FIGHT");
                    battleActive = true;
                    enemyBoss = new Boss(level, Direction.UP);
                    newMap.getPlayingMap()[(HeroTileSaved.getPosX()) / 72][(HeroTileSaved.getPosY()) / 72].setTileStatus(TileStatus.FILLED_HERO_UP);

                } else if (newMap.playingMap[(HeroTileSaved.getPosX()) / 72][(HeroTileSaved.getPosY()) / 72 - 1].tileStatus == TileStatus.HEAL_POTION) {
                    myHero.heal();
                    heroStatsToPrint = myHero.toString();
                    newMap.moveUp();
                    enemyMoves();
                } else if (newMap.playingMap[(HeroTileSaved.getPosX()) / 72][(HeroTileSaved.getPosY()) / 72 - 1].tileStatus == TileStatus.EMPTY) {
                    newMap.moveUp();
                    enemyMoves();
                }
            }
            HeroTileSaved = getNewMap().getHeroLocation();

        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

            if (checkHeroForBordersDOWN() == true) {
                if (battleActive) {
                    System.out.println("Cant move must FIGHT");
                } else if (newMap.playingMap[(HeroTileSaved.getPosX()) / 72][(HeroTileSaved.getPosY()) / 72 + 1].tileStatus == TileStatus.FILLED_ENEMY) {
                    System.out.println("FIGHT");
                    battleActive = true;
                    enemy = new Enemy(level, Direction.DOWN);
                    newMap.getPlayingMap()[(HeroTileSaved.getPosX()) / 72][(HeroTileSaved.getPosY()) / 72].setTileStatus(TileStatus.FILLED_HERO_DOWN);

                } else if (newMap.playingMap[(HeroTileSaved.getPosX()) / 72][(HeroTileSaved.getPosY()) / 72 + 1].tileStatus == TileStatus.FILLED_BOSS) {
                    System.out.println("FIGHT");
                    battleActive = true;
                    enemyBoss = new Boss(level, Direction.DOWN);
                    newMap.getPlayingMap()[(HeroTileSaved.getPosX()) / 72][(HeroTileSaved.getPosY()) / 72].setTileStatus(TileStatus.FILLED_HERO_DOWN);

                } else if (newMap.playingMap[(HeroTileSaved.getPosX()) / 72][(HeroTileSaved.getPosY()) / 72 + 1].tileStatus == TileStatus.HEAL_POTION) {
                    myHero.heal();
                    newMap.moveDown();
                    enemyMoves();
                    heroStatsToPrint = myHero.toString();
                } else if (newMap.playingMap[(HeroTileSaved.getPosX()) / 72][(HeroTileSaved.getPosY()) / 72 + 1].tileStatus == TileStatus.EMPTY) {
                    newMap.moveDown();
                    enemyMoves();
                }
            }
            HeroTileSaved = getNewMap().getHeroLocation();

        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

            if (checkHeroForBordersRIGHT() == true) {
                if (battleActive) {
                    System.out.println("Cant move must FIGHT");
                } else if (newMap.playingMap[(HeroTileSaved.getPosX()) / 72 + 1][(HeroTileSaved.getPosY()) / 72].tileStatus == TileStatus.FILLED_ENEMY) {
                    System.out.println("FIGHT");
                    battleActive = true;
                    this.enemy = new Enemy(level, Direction.RIGHT);
                    newMap.getPlayingMap()[(HeroTileSaved.getPosX()) / 72][(HeroTileSaved.getPosY()) / 72].setTileStatus(TileStatus.FILLED_HERO_RIGHT);

                } else if (newMap.playingMap[(HeroTileSaved.getPosX()) / 72 + 1][(HeroTileSaved.getPosY()) / 72].tileStatus == TileStatus.FILLED_BOSS) {
                    System.out.println("FIGHT");
                    battleActive = true;
                    enemyBoss = new Boss(level, Direction.RIGHT);
                    newMap.getPlayingMap()[(HeroTileSaved.getPosX()) / 72][(HeroTileSaved.getPosY()) / 72].setTileStatus(TileStatus.FILLED_HERO_RIGHT);

                } else if (newMap.playingMap[(HeroTileSaved.getPosX()) / 72 + 1][(HeroTileSaved.getPosY()) / 72].tileStatus == TileStatus.HEAL_POTION) {
                    myHero.heal();
                    heroStatsToPrint = myHero.toString();
                    newMap.moveRight();
                    enemyMoves();
                } else if (newMap.playingMap[(HeroTileSaved.getPosX()) / 72 + 1][(HeroTileSaved.getPosY()) / 72].tileStatus == TileStatus.EMPTY) {
                    newMap.moveRight();
                    enemyMoves();
                }
            }
            HeroTileSaved = getNewMap().getHeroLocation();

        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (checkHeroForBordersLEFT() == true) {
                if (battleActive) {
                    System.out.println("Cant move must FIGHT");
                } else if (newMap.playingMap[(HeroTileSaved.getPosX()) / 72 - 1][(HeroTileSaved.getPosY()) / 72].tileStatus == TileStatus.FILLED_ENEMY) {
                    System.out.println("FIGHT");
                    battleActive = true;
                    enemy = new Enemy(level, Direction.LEFT);
                    newMap.getPlayingMap()[(HeroTileSaved.getPosX()) / 72][(HeroTileSaved.getPosY()) / 72].setTileStatus(TileStatus.FILLED_HERO_LEFT);

                } else if (newMap.playingMap[(HeroTileSaved.getPosX()) / 72 - 1][(HeroTileSaved.getPosY()) / 72].tileStatus == TileStatus.FILLED_BOSS) {
                    System.out.println("FIGHT");
                    battleActive = true;
                    enemyBoss = new Boss(level, Direction.LEFT);
                    newMap.getPlayingMap()[(HeroTileSaved.getPosX()) / 72][(HeroTileSaved.getPosY()) / 72].setTileStatus(TileStatus.FILLED_HERO_LEFT);

                } else if (newMap.playingMap[(HeroTileSaved.getPosX()) / 72 - 1][(HeroTileSaved.getPosY()) / 72].tileStatus == TileStatus.HEAL_POTION) {
                    myHero.heal();
                    heroStatsToPrint = myHero.toString();
                    newMap.moveLeft();
                    enemyMoves();
                } else if (newMap.playingMap[(HeroTileSaved.getPosX()) / 72 - 1][(HeroTileSaved.getPosY()) / 72].tileStatus == TileStatus.EMPTY) {
                    newMap.moveLeft();
                    enemyMoves();
                }
            }
            HeroTileSaved = getNewMap().getHeroLocation();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {


            if (battleActive == true) {
                if (enemyBoss != null) {
                    System.out.println("FIGHT BOSS");
                    myHero.fightBoss(enemyBoss);
                    heroStatsToPrint = myHero.toString();
                } else {
                    System.out.println("FIGHT THE SKELETON");
                    myHero.fight(enemy);
                    heroStatsToPrint = myHero.toString();
                }
            }
            if (enemy != null) {
                if (enemy.getHP() == 0) {
                    newMap.clearEnemy(enemy.getDirection());
                    battleActive = false;
                    enemy = null;
                }
            }
            if (enemyBoss != null) {
                if (enemyBoss.getHP() == 0) {
                    newMap.clearEnemy(enemyBoss.getDirection());
                    battleActive = false;
                    enemyBoss = null;
                    level++;
                    makeNewMap(level);
                }
            }
        }
        // and redraw to have a new picture with the new coordinates
        repaint();

    }

    private void enemyMoves() {

        newMap.moveEnemiesAroundMap(newMap.getEnemyTiles());
    }

    private boolean checkHeroForBordersRIGHT() {
        int Xcheck = HeroTileSaved.getPosX() / 72;
        if (Xcheck == 9) {
            return false;
        } else {
            return true;
        }
    }

    private boolean checkHeroForBordersLEFT() {
        int Xcheck = HeroTileSaved.getPosX() / 72;
        if (Xcheck == 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean checkHeroForBordersUP() {
        int Ycheck = HeroTileSaved.getPosY() / 72;
        if (Ycheck == 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean checkHeroForBordersDOWN() {
        int Ycheck = HeroTileSaved.getPosY() / 72;
        if (Ycheck == 9) {
            return false;
        } else {
            return true;
        }
    }


}