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
import frc.robot.subsystems.Conveyor;

/**
 *
 */
public class BallIntake extends CommandBase {

    private Conveyor conveyor;
    private boolean isFinished;
    private double intakeRollerSpeed = 0.5;
    private double conveyorSpeed = 0.5;

    public BallIntake(Conveyor conveyor) {
        this.conveyor = conveyor;
        addRequirements(conveyor);
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        if (conveyor.isBallOut()) {
            isFinished = true;
        } else {
            manageIntakeRollers();
            manageConveyors();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
    }

    private void manageIntakeRollers() {
        //right now we are assuming that it is OK for the intake rollers to turn as long as there is 
        //not a ball at the conveyor entrance. 
        if (conveyor.isBallIn()) {
            conveyor.turnIntakeRollers(0);
        } else {
            conveyor.turnIntakeRollers(intakeRollerSpeed);
        }
    }

    private void manageConveyors() {
        if(conveyor.isBallIn()) {
            conveyor.turnConveyor(conveyorSpeed);
        } else {
            conveyor.turnConveyor(0);
        }
    }

   
}
