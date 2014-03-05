package commands.launching;

import commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Dashboard;
import framework.Init;


/*
 * @author Matthew
 */
public class LauncherDashboardFWD extends CommandBase {
    private double wait;
    private double delay;

    public LauncherDashboardFWD() {
        requires(launcher);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        delay = SmartDashboard.getNumber(Dashboard.LAUNCHER_DROP_DELAY);
        wait = Timer.getFPGATimestamp();
        sippingbird.collectorDeploy();
        System.out.println("SHOOOT!");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (launcher.getArmAngle() < SmartDashboard.getNumber(Dashboard.LAUNCHER_ANGLE, 90.0) && Timer.getFPGATimestamp()-wait > delay ) {
            launcher.setLauncherSpeed(SmartDashboard.getNumber(Dashboard.LAUNCHER_SPEED));
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return launcher.getArmAngle() > SmartDashboard.getNumber(Dashboard.LAUNCHER_ANGLE, 90.0);
    }

    // Called once after isFinished returns true
    protected void end() {
        launcher.stopLauncher();
        sippingbird.collectorRetract();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
