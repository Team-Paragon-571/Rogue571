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

import java.util.Map;
import java.util.function.DoubleSupplier;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class DriveCommand extends CommandBase {

    private final DoubleSupplier speed;
    private final DoubleSupplier twist;
    private final Drive drive;
    private double speedValue;
    private double twistValue;

    public DriveCommand(Drive drive, DoubleSupplier speed, DoubleSupplier twist) {

        this.speed = speed;
        this.twist = twist;
        this.drive = drive;

        addRequirements(drive);

        final ShuffleboardLayout driveStickLayout = Shuffleboard.getTab("Commands").getLayout("DriveStick",
                BuiltInLayouts.kList);
        driveStickLayout.withProperties(Map.of("Label position", "LEFT"));

        driveStickLayout.addNumber("Speed", speed);
        driveStickLayout.addNumber("Twist", twist);

    }
    
    // Called just before this Command runs the first time
    @Override
    public void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        speedValue = speed.getAsDouble();
        twistValue = twist.getAsDouble();

        drive.arcadeDrive(speedValue, twistValue);
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
