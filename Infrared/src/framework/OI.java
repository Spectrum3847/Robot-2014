package framework;

import commands.collection.SippingBirdCatch;
import commands.launching.LauncherManual;
import driver.Gamepad;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    public static final Gamepad gamepad = new Gamepad(HW.usbPort_one);
    public static final Gamepad gamepad_aux = new Gamepad(HW.usbPort_two);

    public static final Button drive_toggle = new JoystickButton(gamepad.getGamepad(), Gamepad.A_BUTTON);
    public static final Button collect = new JoystickButton(gamepad.getGamepad(), Gamepad.LEFT_BUMPER);
    public static final Button eject = new JoystickButton(gamepad.getGamepad(), Gamepad.RIGHT_BUMPER);
    public static final Button autocollect = new JoystickButton(gamepad.getGamepad(), Gamepad.Y_BUTTON);
    
    public static final Button launch = new JoystickButton(gamepad_aux.getGamepad(), Gamepad.A_BUTTON);
    public static final Button launch_manual = new JoystickButton(gamepad_aux.getGamepad(), Gamepad.LEFT_CLICK);
    public static final Button launch_ready = new JoystickButton(gamepad_aux.getGamepad(), Gamepad.LEFT_BUMPER);
    public static final Button degree_launch = new JoystickButton(gamepad_aux.getGamepad(), Gamepad.B_BUTTON);
    public static final Button PID_launch = new JoystickButton(gamepad_aux.getGamepad(), Gamepad.Y_BUTTON);

    //Use this constructor to setup up button schedulers for commands
    public OI() {
        drive_toggle.toggleWhenPressed(Init.cheesydrive);
        collect.whileHeld(Init.sippingbirdcollect);
        eject.whileHeld(Init.sippingbirdeject);
        autocollect.whenPressed(Init.sippingbirdcatch);
        
        launch_manual.toggleWhenPressed(Init.launchermanual);
        launch_ready.toggleWhenPressed(Init.sippingbirdlaunchready);
        degree_launch.whenPressed(Init.launcherdashboardfwd);
        PID_launch.whileHeld(Init.launcherdashboardfwdpid);
    }
}
