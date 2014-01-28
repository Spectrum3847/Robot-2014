package framework;

/**
 * The HW is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class HW {
    /**DRIVEBASE MOTOR ASSIGNMENTS (PWM)**/
    public static final int FRONT_RDRIVE_MOTOR = 2;
    public static final int REAR_RDRIVE_MOTOR = 1;
    public static final int FRONT_LDRIVE_MOTOR = 10;
    public static final int REAR_LDRIVE_MOTOR = 9;
    
    /**NON-DRIVEBASE MOTOR ASSIGNMENTS (PWM)**/
    public static final int SHOOTER_MOTOR_1 = 3;
    public static final int SHOOTER_MOTOR_2 = 4;
    public static final int SHOOTER_MOTOR_3 = 5;
    public static final int SHOOTER_MOTOR_4 = 6;
    
    /**DIGITAL SENSOR ALLOCATIONS**/   
    /**Pneumatics**/
    public static final int PRESSURE_SENSOR = 5;
    
    /**ANALOG SENSOR ALLOCATIONS**/ 
    /**Gyroscope**/
    public static final int GYRO = 1;
    
    /**RELAY ALLOCATIONS**/
    public static final int COMPRESSOR = 1;
   
    /**SOLENOID ALLOCATIONS**/
    public static final int OCTOCANUM = 1;
    
    
    /**PID CONSTANTS**/
    /**Turn Controller PID - Straight**/
    public static final double STRAIGHT_KP = 0.0;
    public static final double STRAIGHT_KI = 0.0;
    public static final double STRAIGHT_KD = 0.0;
    
    /**JOYSTICKS/GAMEPAD ASSIGNMENTS**/
    public static final int usbPort_one = 1;
    public static final int usbPort_two = 2;
}