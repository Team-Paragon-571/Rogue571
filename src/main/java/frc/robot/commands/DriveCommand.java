// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class DriveCommand extends CommandBase {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    private final DoubleSupplier speed;
    private final DoubleSupplier twist;
    private final Drive drive;
    private final NetworkTableEntry driveStickSpeedEntry, driveStickTwistEntry;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public DriveCommand(Drive drive, DoubleSupplier speed, DoubleSupplier twist) {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTByUILDER ID=VARIABLE_SETTING
        this.speed = speed;
        this.twist = twist;
        this.drive = drive;

        addRequirements(drive);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

        final ShuffleboardLayout driveStickLayout = Shuffleboard.getTab("Commands")
        .getLayout("DriveStick", BuiltInLayouts.kList);

        driveStickSpeedEntry = driveStickLayout
        .add("Speed", speed.getAsDouble())
        .getEntry();

        driveStickTwistEntry = driveStickLayout.add("Twist", twist.getAsDouble()).getEntry();
        
        
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        double speedValue = speed.getAsDouble();
        double twistValue = twist.getAsDouble();
        
        drive.arcadeDrive(speedValue, twistValue);
        driveStickSpeedEntry.setDouble(speedValue);
        driveStickTwistEntry.setDouble(twistValue);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        drive.arcadeDrive(0, 0);

    }

}
