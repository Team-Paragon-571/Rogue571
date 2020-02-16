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

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorSubsystem;
import edu.wpi.first.wpilibj.util.Color;

/**
 *
 */
public class SpinToColor extends CommandBase {

    private final Color targetColor;
    private final ColorSubsystem colorSubsystem;
    private Color currentColor;
    private int correctReading;

    public SpinToColor(ColorSubsystem colorSubsystem, Color targetColor) {
        this.targetColor = targetColor;
        this.colorSubsystem = colorSubsystem;
        this.correctReading = 0;
        addRequirements(colorSubsystem);

    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        colorSubsystem.spin(.7);

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        currentColor = colorSubsystem.getCurrentColor();
        if (currentColor != targetColor) {
            return false;
        } else {
            correctReading++;
            if (correctReading == 3) {
                return true;
            } else {
                return false;
            }
        }

    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        colorSubsystem.stop();
    }

}
