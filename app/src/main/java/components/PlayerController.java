package components;

import game.KeyInputListener;
import game.Window;
import org.joml.Vector2f;
import physics2d.components.Rigidbody2D;
import static java.awt.event.KeyEvent.*;
import static org.lwjgl.glfw.GLFW.*;

public class PlayerController extends Component {
    public float walkSpeed = 100.0f;
    public float jumpBoost = 1.0f;
    public float jumpImpulse = 3.0f;
    public float slowDownForce = 60.0f;
    public Vector2f terminalVelocity = new Vector2f(130.0f, 600.0f);

    public transient boolean onGround = false;
    private transient float groundDebounce = 0.0f;
    private transient float groundDebounceTime = 0.1f;
    private transient Rigidbody2D rb;
    private transient StateMachine stateMachine;
    private transient float bigJumpBoostFactor = 1.05f;
    private transient float playerWidth = 1f;
    private transient int jumpTime = 0;
    private transient Vector2f acceleration = new Vector2f();
    private transient Vector2f velocity = new Vector2f();
    private transient boolean isDead = false;
    private transient int enemyBounce = 0;
    private transient float speedOffset = 5f;

    @Override
    public void start() {
        this.rb = gameObject.getComponent(Rigidbody2D.class);
        this.stateMachine = gameObject.getComponent(StateMachine.class);
        this.rb.setGravityScale(0.0f);
    }

    @Override
    public void update(float dt) {
        if (KeyInputListener.isKeyHold(VK_RIGHT) || KeyInputListener.isKeyHold(VK_D)) {
            this.gameObject.transform.scale.x = playerWidth;
            this.acceleration.x = walkSpeed;

            if (this.velocity.x < 0) {
                this.velocity.x += slowDownForce;
            } else {
                this.stateMachine.trigger("startRunning");
            }
        } else if (KeyInputListener.isKeyHold(VK_LEFT) || KeyInputListener.isKeyHold(VK_A)) {
            this.gameObject.transform.scale.x = -playerWidth;
            this.acceleration.x = -walkSpeed;

            if (this.velocity.x > 0) {
                this.velocity.x -= slowDownForce;
            } else {
                this.stateMachine.trigger("startRunning");
            }
        } else {
            this.acceleration.x = 0;
            if (this.velocity.x > 0) {
                this.velocity.x = Math.max(0, this.velocity.x - slowDownForce);
            } else if (this.velocity.x < 0) {
                this.velocity.x = Math.min(0, this.velocity.x + slowDownForce);
            }

            if (this.velocity.x == 0) {
                this.stateMachine.trigger("stopRunning");
            }
        }

        this.acceleration.y = Window.getPhysics().getGravity().y * 0.7f;

        this.velocity.x += this.acceleration.x * dt * speedOffset;
        this.velocity.y += this.acceleration.y * dt;
        this.velocity.x = Math.max(Math.min(this.velocity.x, this.terminalVelocity.x), -this.terminalVelocity.x);
        this.velocity.y = Math.max(Math.min(this.velocity.y, this.terminalVelocity.y), -this.terminalVelocity.y);
        this.rb.setVelocity(this.velocity);
        this.rb.setAngularVelocity(0);
    }
}