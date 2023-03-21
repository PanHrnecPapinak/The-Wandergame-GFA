import java.util.ArrayList;
import java.util.Random;

public class Map {
    Random random = new Random();
    boolean doesItAlreadyRun;
    Tile[][] playingMap = new Tile[10][10];
    //playingMap[0][0] =
    boolean upClear;
    boolean downClear;
    boolean leftClear;
    boolean rightClear;

    public void fillTheMap() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                playingMap[j][i] = new Tile(j, i);

            }
        }
        playingMap[3][0].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[3][1].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[5][1].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[7][1].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[8][1].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[1][2].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[2][2].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[3][2].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[5][2].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[7][2].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[8][2].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[5][3].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[0][4].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[1][4].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[2][4].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[3][4].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[5][4].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[6][4].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[7][4].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[8][4].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[1][5].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[5][5].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[3][5].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[6][1].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[3][6].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[5][6].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[6][6].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[8][6].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[5][7].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[6][7].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[8][7].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[1][8].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[2][8].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[3][8].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[8][8].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[3][9].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[5][9].setTileStatus(TileStatus.FILLED_WALL);
        playingMap[6][9].setTileStatus(TileStatus.FILLED_WALL);

        playingMap[0][0].setTileStatus(TileStatus.FILLED_HERO_DOWN);
        playingMap[9][9].setTileStatus(TileStatus.FILLED_BOSS);


    }

    public void fillRandomMap(int level) {
        int X = 0;
        int Y = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                playingMap[j][i].setTileStatus(TileStatus.FILLED_WALL);
            }
        }
//        while (X != 9 && Y != 9){
//            int direction = random.nextInt(1, 3);
//            if (direction == 1 && X < 9) {
//                X++;
//            }
//
//            if (direction == 2 && Y < 9) {
//                Y++;
//            }
//            playingMap[X][Y].setTileStatus(TileStatus.EMPTY);
//        }

        for (int i = 0; i < 40; i++) {
            int direction = random.nextInt(1, 3);
            if (direction == 1 && X < 9) {
                X++;
            }

            if (direction == 2 && Y < 9) {
                Y++;
            }
            playingMap[X][Y].setTileStatus(TileStatus.EMPTY);
        }
        for (int i = 0; i < level*2 + 10; i++) {
            playingMap[random.nextInt(0, 10)][random.nextInt(0, 10)].setTileStatus(TileStatus.EMPTY);
        }
        playingMap[0][0].setTileStatus(TileStatus.FILLED_HERO_DOWN);
        playingMap[9][9].setTileStatus(TileStatus.FILLED_BOSS);

    }


    public void fillTheEnemy(int level) {
        for (int i = 0; i < level + 2; i++) {
            while (true) {
                int x = random.nextInt(0, 10);
                int y = random.nextInt(0, 10);
                if (checkLocaton(x, y) == true) {
                    playingMap[x][y].setTileStatus(TileStatus.FILLED_ENEMY);
                    break;
                }
            }
        }
    }

    public void addHPpotion(int level) {
        for (int i = 0; i < level / 2; i++) {
            while (true) {
                int x = random.nextInt(0, 10);
                int y = random.nextInt(0, 10);
                if (checkLocaton(x, y) == true) {
                    playingMap[x][y].setTileStatus(TileStatus.HEAL_POTION);
                    break;
                }
            }
        }
    }


    private boolean checkLocaton(int X, int Y) {
        if (TileStatus.EMPTY == playingMap[X][Y].getTileStatus()) {
            return true;
        } else {
            return false;
        }
    }

    public Tile getHeroLocation() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (playingMap[j][i].tileStatus == TileStatus.FILLED_HERO_DOWN || playingMap[j][i].tileStatus == TileStatus.FILLED_HERO_UP || playingMap[j][i].tileStatus == TileStatus.FILLED_HERO_LEFT || playingMap[j][i].tileStatus == TileStatus.FILLED_HERO_RIGHT) {
                    return playingMap[j][i];
                }
            }
        }
        return null;
    }

    public Tile[][] getPlayingMap() {
        return playingMap;
    }

    public void setPlayingMap(Tile[][] playingMap) {
        this.playingMap = playingMap;
    }

    public void moveDown() {
        int heroX = getHeroLocation().getPosX() / 72;
        int heroY = getHeroLocation().getPosY() / 72;

        playingMap[heroX][heroY].setTileStatus(TileStatus.EMPTY);
        playingMap[heroX][heroY + 1].setTileStatus(TileStatus.FILLED_HERO_DOWN);

    }

    public void moveUp() {
        int heroX = getHeroLocation().getPosX() / 72;
        int heroY = getHeroLocation().getPosY() / 72;

        playingMap[heroX][heroY].setTileStatus(TileStatus.EMPTY);
        playingMap[heroX][heroY - 1].setTileStatus(TileStatus.FILLED_HERO_UP);

    }

    public void moveLeft() {
        int heroX = getHeroLocation().getPosX() / 72;
        int heroY = getHeroLocation().getPosY() / 72;

        playingMap[heroX][heroY].setTileStatus(TileStatus.EMPTY);
        playingMap[heroX - 1][heroY].setTileStatus(TileStatus.FILLED_HERO_LEFT);

    }

    public void moveRight() {
        int heroX = getHeroLocation().getPosX() / 72;
        int heroY = getHeroLocation().getPosY() / 72;

        playingMap[heroX][heroY].setTileStatus(TileStatus.EMPTY);
        playingMap[heroX + 1][heroY].setTileStatus(TileStatus.FILLED_HERO_RIGHT);

    }

    public void clearEnemy(Direction direction) {
        int heroX = getHeroLocation().getPosX() / 72;
        int heroY = getHeroLocation().getPosY() / 72;

        if (direction == Direction.UP) {
            heroY--;
        }
        if (direction == Direction.DOWN) {
            heroY++;
        }
        if (direction == Direction.LEFT) {
            heroX--;
        }
        if (direction == Direction.RIGHT) {
            heroX++;
        }
        playingMap[heroX][heroY].setTileStatus(TileStatus.EMPTY);

    }

    public ArrayList<Tile> getEnemyTiles() {
        ArrayList<Tile> allEnemyList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (playingMap[j][i].tileStatus == TileStatus.FILLED_ENEMY || playingMap[j][i].tileStatus == TileStatus.FILLED_BOSS) {
                    allEnemyList.add(playingMap[j][i]);
                }
            }
        }
        return allEnemyList;
    }

    public void moveEnemiesAroundMap(ArrayList<Tile> allEnemyList) {
        for (Tile enemyTile : allEnemyList) {
            int x = enemyTile.posX / 72;
            int y = enemyTile.posY / 72;
            checkLocationsAroundMe(x, y);
            boolean running = true;
            if (upClear == true || downClear == true || leftClear == true || rightClear == true)
                while (running) {
                    int rollDice = random.nextInt(1, 5);
                    if (rollDice == 1 && upClear) {
                        //todo move up
                        System.out.println("enemy moves up");
                        enemyMoveUp(x, y);
                        running = false;
                    }
                    if (rollDice == 2 && downClear) {
                        //todo move down
                        System.out.println("enemy moves down");
                        enemyMoveDown(x, y);
                        running = false;
                    }
                    if (rollDice == 3 && leftClear) {
                        //todo move left
                        System.out.println("enemy moves left");
                        enemyMoveLeft(x, y);
                        running = false;
                    }
                    if (rollDice == 4 && rightClear) {
                        //todo move right
                        System.out.println("enemy moves right");
                        enemyMoveRight(x, y);
                        running = false;
                    }
                }


        }
    }

    private void enemyMoveLeft(int x, int y) {
        playingMap[x - 1][y].setTileStatus(playingMap[x][y].getTileStatus());
        playingMap[x][y].setTileStatus(TileStatus.EMPTY);
    }

    private void enemyMoveRight(int x, int y) {
        playingMap[x + 1][y].setTileStatus(playingMap[x][y].getTileStatus());
        playingMap[x][y].setTileStatus(TileStatus.EMPTY);
    }

    private void enemyMoveDown(int x, int y) {
        playingMap[x][y + 1].setTileStatus(playingMap[x][y].getTileStatus());
        playingMap[x][y].setTileStatus(TileStatus.EMPTY);
    }

    private void enemyMoveUp(int x, int y) {
        playingMap[x][y - 1].setTileStatus(playingMap[x][y].getTileStatus());
        playingMap[x][y].setTileStatus(TileStatus.EMPTY);

    }

    private void checkLocationsAroundMe(int x, int y) {
        this.upClear = false;
        this.downClear = false;
        this.leftClear = false;
        this.rightClear = false;


        if (x != 0) {
            if (playingMap[x - 1][y].getTileStatus() == TileStatus.EMPTY) {
                System.out.println("Left clear");
                this.leftClear = true;
            }
        }
        if (x != 9) {
            if (playingMap[x + 1][y].getTileStatus() == TileStatus.EMPTY) {
                System.out.println("Right clear");
                this.rightClear = true;
            }
        }
        if (y != 0) {
            if (playingMap[x][y - 1].getTileStatus() == TileStatus.EMPTY) {
                System.out.println("Up clear");
                this.upClear = true;
            }
        }
        if (y != 9) {
            if (playingMap[x][y + 1].getTileStatus() == TileStatus.EMPTY) {
                System.out.println("Down clear");
                this.downClear = true;
            }
        }
    }


}
