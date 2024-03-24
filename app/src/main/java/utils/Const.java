package utils;

public class Const {

    public static final float O_SCALE = 1f;
    
    public class Img {
        private static final String SPRITES_PARENT_FOLDER = "app/assets/sprites/";

        public static final String TILES = SPRITES_PARENT_FOLDER + "terrain/ground/Tiles.png";
        public static final String DUNGEON_FLOOR =  SPRITES_PARENT_FOLDER + "terrain/ground/Dungeon_floor.png";
        public static final String DUNGEON_FLOOR_SPRITESHEET =  SPRITES_PARENT_FOLDER + "terrain/ground/Dungeon_floor_sprites.png";
        public static final String KNIGHT_IDLE = SPRITES_PARENT_FOLDER + "entities/knight/Idle-Sheet.png";
        public static final String WIZZARD_IDLE = SPRITES_PARENT_FOLDER + "entities/wizzard/Idle-Sheet.png";
        public static final String ROGUE_IDLE = SPRITES_PARENT_FOLDER + "entities/rogue/Idle-Sheet.png";
        public static final String BOOK = SPRITES_PARENT_FOLDER + "Book.png";
        public static final String GREEN_SOLDIER = SPRITES_PARENT_FOLDER + "entities/Green_Soldier.png";
        public static final String BLUE_SOLDIER = SPRITES_PARENT_FOLDER + "entities/Blue_Soldier.png";
        public static final String RED_SOLDIER = SPRITES_PARENT_FOLDER + "entities/Red_Soldier.png";

    }

    public class Sound {
        private static final String SOUNDS_PARENT_FOLDER = "app/assets/sounds/";

        public static final String WEAPON_SWING = SOUNDS_PARENT_FOLDER + "weapon_swing.ogg";
        public static final String EQUIP_ITEM = SOUNDS_PARENT_FOLDER + "equip_item.ogg";
    }

}
