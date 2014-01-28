package commands.driving;

import com.sun.squawk.util.MathUtils;
import commands.CommandBase;
import edu.wpi.first.wpilibj.RobotDrive;
import framework.OI;
import framework.Utilities;


/*
 * @author Matthew
 */
public class HoloDrive extends CommandBase {
    public HoloDrive()
    {
        requires(CommandBase.drivebase);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
        pneumatics.engageHolo();
        drivebase.disableTurnController();
        drivebase.setHoloInversion(true);
        System.out.println("Holodrive, GO!");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double y = Utilities.haloDeadBand(OI.gamepad.getLeftY(), OI.gamepad.getLeftX(), .1, .13);
        double x = Utilities.haloDeadBand(OI.gamepad.getLeftX(), OI.gamepad.getLeftY(), .1, .13);
        
        int sign = (int)(Utilities.abs(y)/y);
        double magnitude = sign*Math.sqrt(x*x+y*y);
        double angle = MathUtils.atan2(x,y);
        double rotation = OI.gamepad.getTriggers();
        
        if (rotation != 0){
            drivebase.setHoloPolar(0, 0, rotation);
        } else{
            //drivebase.setHolo(magnitude, angle, 0);
            drivebase.setHoloCartesian(x, y, 0);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        drivebase.setArcade(0, 0);
        drivebase.setStandardInversion(true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}