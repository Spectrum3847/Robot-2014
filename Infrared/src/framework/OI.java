package framework;

import commands.SippingBirdCollect;
import commands.SippingBirdEject;
import driver.Gamepad;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    /*
     * Buttons Available:
     * Bumper L
     * Bumper R
     * Button X
     * Button Y
     * Button A
     * Button B
     * Up
     * Down
     * Left
     * Right
     */
    public static final Gamepad gamepad = new Gamepad(HW.usbPort_one);
    public static final Gamepad gamepad_aux = new Gamepad(HW.usbPort_two);
    
    public static final Button holo_drive_toggle = new JoystickButton(gamepad.getGamepad(), gamepad.A_BUTTON);
    public static final Button buttery_drive_toggle = new JoystickButton(gamepad.getGamepad(), gamepad.B_BUTTON);
    public static final Button gyro_reset = new JoystickButton(gamepad.getGamepad(), gamepad.Y_BUTTON);
    public static final Button sbCollector = new JoystickButton(gamepad.getGamepad(), gamepad.LEFT_BUMPER);
    public static final Button sbEject = new JoystickButton(gamepad.getGamepad(), gamepad.RIGHT_BUMPER);
    
    //Use this constructor to setup up button schedulers for commands
    public OI() {
        buttery_drive_toggle.toggleWhenPressed(Init.butteryflydrive);
        holo_drive_toggle.toggleWhenPressed(Init.holodrive);
        gyro_reset.whenPressed(Init.gryoreset);
        sbCollector.whileHeld(new SippingBirdCollect());
        sbEject.whileHeld(new SippingBirdEject());
    }
}
