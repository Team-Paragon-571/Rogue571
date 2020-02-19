// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.subsystems;

import java.util.Map;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 *
 */
public class Leveling extends SubsystemBase {

    private final WPI_TalonSRX levelWheels;

    public Leveling() {

        levelWheels = new WPI_TalonSRX(6);
        addChild("Level", levelWheels);

        final ShuffleboardLayout layout = Shuffleboard.getTab("Subsystems").getLayout("Level Wheels",
                BuiltInLayouts.kList);
        layout.withProperties(Map.of("Label position", "LEFT"));
        layout.addNumber("Speed", () -> levelWheels.get());

    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}
