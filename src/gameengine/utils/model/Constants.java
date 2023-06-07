package gameengine.utils.model;

public class Constants {
    private Constants() {}

    public static final int BLOCK_LENGHT = 32;

    public static final int CHARACTER_LENGHT = 64;

    // The sprite dimension constant
    public static final int SPRITE_DIM = 32;

    public static final int MAP_COLUMNS = 100;
    public static final int MAP_ROWS = 25;
    public static final int MAP_LENGTH = MAP_COLUMNS*BLOCK_LENGHT;
    public static final int MAP_HEIGHT = MAP_COLUMNS*MAP_ROWS;

    public static final int SCREEN_WIDTH = 1024;
    public static final int SCREEN_HEIGHT = 720;

    public static final int SPAWN_WIDTH = SCREEN_WIDTH/2;
    public static final int SPAWN_HEIGHT = SCREEN_HEIGHT/2;

}
