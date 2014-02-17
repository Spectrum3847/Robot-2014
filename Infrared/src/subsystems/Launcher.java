package subsystems;

import commands.CommandBase;
import commands.launching.LauncherManual;
import commands.launching.LauncherZero;
import driver.Potentiometer;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;
import framework.HW;
import framework.Utilities;

/**
 *
 * @author matthew
 */
public final class Launcher extends PIDSubsystem {

    private final Victor v1, v2, v3, v4;
    private int invert1 = 1, invert2 = 1, invert3 = 1, invert4 = 1;
    private final Potentiometer pot;
    private final Encoder enc;
    //private final Counter enc;
    private final DigitalInput button;

    private final PIDController controller;

    private boolean isVelocity = false;

    private boolean stall = true;

    private double positionSetpoint = 0.0;
    private double velocitySetpoint = 0.0;

    private double PIDControllerOut;
    private final double tolerance = 3.5; //Percentage of error that the turn controller can be off and still be onTarget()
    final DoubleSolenoid wings;

    public Launcher() {
        super(HW.LAUNCHER_KP, HW.LAUNCHER_KI, HW.LAUNCHER_KD);
        wings = new DoubleSolenoid(HW.WINGS, HW.WINGS + 1);
        v1 = new Victor(HW.LAUNCHER_MOTOR_1);
        v2 = new Victor(HW.LAUNCHER_MOTOR_2); // CIM 4
        v3 = new Victor(HW.LAUNCHER_MOTOR_3); // CIM
        v4 = new Victor(HW.LAUNCHER_MOTOR_4);
        setInvert1(false);
        setInvert2(true);
        setInvert3(true);
        setInvert4(false);
        button = new DigitalInput(HW.LAUNCHER_STOP);
        pot = new Potentiometer(HW.LAUNCHER_POT);
        pot.setInvertAngle(true);
        enc = new Encoder(HW.LAUNCHER_ENCODER, HW.LAUNCHER_ENCODER + 1);
        //enc = new Counter(HW.LAUNCHER_ENCODER);
        controller = this.getPIDController();
        controller.setOutputRange(-0.5, 1);
        controller.setAbsoluteTolerance(tolerance);
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new LauncherZero());
        //setDefaultCommand(new LauncherManual());
    }

    public void setLauncherSpeed(double speed) {
        v1.set(speed * invert1);
        v2.set(speed * invert2);
        v3.set(speed * invert3);
        v4.set(speed * invert4);
    }

    public void setCIMSpeed(double s) {
        v2.set(s * invert2);
        v3.set(s * invert3);
    }

    public final void setInvert1(boolean isInverted) {
        invert1 = isInverted ? -1 : 1;
    }

    public final void setInvert2(boolean isInverted) {
        invert2 = isInverted ? -1 : 1;
    }

    public final void setInvert3(boolean isInverted) {
        invert3 = isInverted ? -1 : 1;
    }

    public final void setInvert4(boolean isInverted) {
        invert4 = isInverted ? -1 : 1;
    }

    public void stopLauncher() {
        setLauncherSpeed(0);
    }

    public void fastLauncher() {
        setLauncherSpeed(1);
    }

    public void slowLauncher() {
        setLauncherSpeed(-0.2);
    }

    public void stallLauncher() {
        setCIMSpeed(-0.2);
        v1.set(0);
        v4.set(0);
    }

    public void PIDStall(boolean b) {
        stall = b;
    }

    public void wingsClose() {
        wings.set(DoubleSolenoid.Value.kForward);
    }

    public void wingsOpen() {
        wings.set(DoubleSolenoid.Value.kReverse);
    }

    public boolean isDown() {
        return !button.get(); //Vex button sensor inverted
    }

    public double getArmAngle() {

        return 360.0 - (15.0 / 26.0) * pot.getAngle() - (SmartDashboard.getNumber(Dashboard.LAUNCHER_OFFSET, 183.5));
    }

    public Potentiometer getPot() {
        return pot;
    }

    public double getRate() {
        return enc.getRate();
    }

    public void enableEncoder() {
        enc.start();
    }

    public void disableEncoder() {
        enc.stop();
    }

    protected double returnPIDInput() {
        if (isVelocity) {
            return getRate();
        } else {
            return getArmAngle();
        }
    }

    protected void usePIDOutput(double d) {
        if (!isVelocity && atPosition() || CommandBase.sippingbird.isBall()) {
            stopLauncher();
        } else if (!isVelocity) {
            setLauncherSpeed(d);
        }
    }

    public double getPIDControllerOut() {
        return PIDControllerOut;
    }

    public void disablePID() {
        controller.reset();
    }

    public void PIDSetVelocity(double v) {
        velocitySetpoint = v;
        controller.setSetpoint(v);
    }

    public void enableVelocityPID() {
        controller.reset();
        isVelocity = true;
        controller.setPID(HW.LAUNCHER_KP, HW.LAUNCHER_KI, HW.LAUNCHER_KD);
        controller.setInputRange(-1000, 1000);
        controller.setContinuous(false);
        controller.setSetpoint(0);
        controller.enable();
    }

    public void setVelocityPID(double p, double i, double d) {
        controller.setPID(p, i, d);
    }

    public boolean atVelocity() {
        return velocitySetpoint - getRate() <= tolerance;
    }

    public void PIDSetPosition(double v) {
        positionSetpoint = v;
        controller.setSetpoint(v);
    }

    public void enablePositionPID() {
        controller.reset();
        isVelocity = false;
        controller.setPID(HW.LAUNCHER_POS_KP, HW.LAUNCHER_POS_KI, HW.LAUNCHER_POS_KD);
        controller.setInputRange(-3, 180);
        controller.setContinuous(false);
        controller.setSetpoint(0);
        controller.enable();
    }

    public void setPositionPID(double p, double i, double d) {
        controller.setPID(p, i, d);
    }

    public void setPositionPID(double p, double i, double d, double f) {
        controller.setPID(p, i, d, f);
    }

    public boolean atPosition() {
        return Utilities.abs(positionSetpoint - getArmAngle()) <= tolerance || this.isDown();
    }
}
