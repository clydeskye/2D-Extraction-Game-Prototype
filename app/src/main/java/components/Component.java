package components;
import game.GameObject;

public abstract class Component {
    private int uid = -1;
    private static int ID_COUNTER = 0;
    

    public transient GameObject gameObject = null;

    public void start() {
        
    }

    public void update(float dt) {

    }

    public void editorUpdate(float dt) {
        
    }

    public void generateId() {
        if(this.uid == -1) {
            this.uid = ID_COUNTER++;
        }
    }

    public int getUid() {
        return this.uid;
    }

    public static void init(int maxId) {
        ID_COUNTER = maxId;
    }

    private <T extends Enum<T>> String[] getEnumValues(Class<T> enumType) {
        String[] enumValues = new String[enumType.getEnumConstants().length];
        int i = 0;
        for (T enumIntValue : enumType.getEnumConstants()) {
            enumValues[i] = enumIntValue.name();
            i++;
        }
        return enumValues;
    }

    private int indexOf(String str, String[] strArr) {
        for (int i = 0; i < strArr.length; i++) {
            if (str.equals(strArr[i])) {
                return i;
            }
        }
        return -1;
    }

    public void destroy() {
    }


}
