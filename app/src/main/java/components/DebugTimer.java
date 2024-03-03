package components;

public class DebugTimer extends Component {
    
    private int timer;
    private int maxTime;

    public void setCountdown(int time) {
        this.maxTime = time;
        this.timer = time;
    }

    @Override
    public void start() {
        this.timer = maxTime;
    }

    @Override
    public void update(float dt) {
        if (timer > 0) {
            timer--;
        } else if (timer == 0) {
            this.gameObject.setDead();
        }
    }
}
