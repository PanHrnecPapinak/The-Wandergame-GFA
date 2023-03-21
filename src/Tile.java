public class Tile {
    TileStatus tileStatus;  // empty, filled - hero, enemy, wall
    int posX;
    int posY;

    public static final String jarFile = "src/";


    Tile(int x, int y){
    posX = x * 72;
    posY = y * 72;
    tileStatus = TileStatus.EMPTY;
    }
    public Tile(int x, int y, TileStatus tileStatus) {
        this.tileStatus = tileStatus;
        this.posX = x * 72;
        this.posY = y * 72;
    }

    //public getDrawStatus

    public TileStatus getTileStatus() {
        return tileStatus;
    }



    public String getTileStatusString() {
        if (tileStatus == TileStatus.EMPTY){
            return jarFile + "images/floor.png";


        }
        else if (tileStatus  == TileStatus.FILLED_WALL){
            return jarFile + "images/wall.png";
        }
        else if (tileStatus  == TileStatus.FILLED_ENEMY){
            return jarFile + "images/skeleton.png";
        }
        else if (tileStatus  == TileStatus.FILLED_BOSS){
            return jarFile + "images/boss.png";
        }
        else if (tileStatus  == TileStatus.FILLED_HERO_DOWN){
            return jarFile + "images/hero-down.png";
        }
        else if (tileStatus  == TileStatus.FILLED_HERO_UP){
            return jarFile + "images/hero-up.png";
        }
        else if (tileStatus  == TileStatus.FILLED_HERO_LEFT){
            return jarFile + "images/hero-left.png";
        }
        else if (tileStatus  == TileStatus.FILLED_HERO_RIGHT){
            return jarFile + "images/hero-right.png";
        }
        else if (tileStatus  == TileStatus.HEAL_POTION){
            return jarFile + "images/hppotion.png";
        }



        else {
            return tileStatus.toString();
        }


    }

    public void setTileStatus(TileStatus tileStatus) {
        this.tileStatus = tileStatus;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "tileStatus=" + tileStatus +
                ", posX=" + posX +
                ", posY=" + posY +
                '}';
    }


}
