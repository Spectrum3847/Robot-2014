package framework;

/**
 * The HW is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class HW {
    /**DRIVEBASE MOTOR ASSIGNMENTS (PWM)**/
    public static final int REAR_RDRIVE_MOTOR = 1;
    public static final int FRONT_RDRIVE_MOTOR = 2;
    public static final int FRONT_LDRIVE_MOTOR = 9;
    public static final int REAR_LDRIVE_MOTOR = 10;
    
    /**NON-DRIVEBASE MOTOR ASSIGNMENTS (PWM)**/
    public static final int LAUNCHER_MOTOR_1 = 4;
    public static final int LAUNCHER_MOTOR_2 = 5;
    public static final int LAUNCHER_MOTOR_3 = 6;
    public static final int LAUNCHER_MOTOR_4 = 7;
    
    public static final int COLLECTOR_RIGHT = 3;
    public static final int COLLECTOR_LEFT = 8;
    
    /**DIGITAL SENSOR ALLOCATIONS**/
    /**IMU**/
    public static final int IMU_PORT = 1;
    /**Drivebase Encoders**/
    /**Launcher Encoder**/
    public static final int LAUNCHER_ENCODER = 2; // and 3
    /**Buttons**/
    public static final int LAUNCHER_STOP = 4;
    public static final int LAUNCHER_TOP = 6;
    /**Pneumatics**/
    public static final int PRESSURE_SENSOR = 5;
    
    
    /**ANALOG SENSOR ALLOCATIONS**/ 
    /**Gyroscope**/
    public static final int GYRO = 1;
    /**Potentiometers**/
    public static final int LAUNCHER_POT = 2;
    /**IR Sensors**/
    public static final int BALL_SENSOR = 3;
    
    /**RELAY ALLOCATIONS**/
    public static final int COMPRESSOR = 1;
    public static final int LIGHT_RING = 2;
   
    /**SOLENOID ALLOCATIONS**/
    public static final int DRIVESHIFT = 1; // and 2
    public static final int SIPPINGBIRD = 3; // and 4
    public static final int WINGS = 5; // and 6
    
    
    /**PID CONSTANTS**/
    /**Turn Controller PID**/
    public static final double TURN_KP = 0.0;
    public static final double TURN_KI = 0.0;
    public static final double TURN_KD = 0.0;
    /**Launcher Velocity PID**/
    public static final double LAUNCHER_KP = 0.0;
    public static final double LAUNCHER_KI = 0.0;
    public static final double LAUNCHER_KD = 0.0;
    public static final double LAUNCHER_KF = 1.54;
    /**Launcher Position PID**/
    /*
    public static final double LAUNCHER_POS_KP = 0.015;
    public static final double LAUNCHER_POS_KI = 0.23/1000;
    public static final double LAUNCHER_POS_KD = 0.015;
    */
    public static final double LAUNCHER_POS_KP = 0.007;
    public static final double LAUNCHER_POS_KI = 0.0;
    public static final double LAUNCHER_POS_KD = 0.020;
    
    /**JOYSTICKS/GAMEPAD ASSIGNMENTS**/
    public static final int usbPort_one = 1;
    public static final int usbPort_two = 2;
}
