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

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

import java.util.Map;
import java.util.function.BooleanSupplier;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 */
public class Conveyor extends SubsystemBase {

    private final WPI_TalonSRX intakeMotor = new WPI_TalonSRX(7);
    private final WPI_TalonSRX beltMotor1 = new WPI_TalonSRX(9);
    private final WPI_TalonSRX beltMotor2 = new WPI_TalonSRX(10);
    private final SpeedController beltMotors = new SpeedControllerGroup(beltMotor1, beltMotor2);

    // pneumatics
    /*private final Solenoid drawbridge = new Solenoid(0);

    public void openDrawBridge() {
        drawbridge.set(false);
    }

    public void closeDrawBridge() {
        drawbridge.set(true);
    }*/

    // break beam sensors
    private final DigitalInput bottom = new DigitalInput(1);
    private final DigitalInput intake = new DigitalInput(0);
    private final DigitalInput top = new DigitalInput(2);

    private boolean previousBallIn = false;
    private boolean previousBallOut = false;
    private boolean previousBallStop = false;
    private int maxLimit = 4;

    public Conveyor() {
        addChild("intakeMotor", intakeMotor);
        addChild("beltMotor1", beltMotor1);
        addChild("beltMotor2", beltMotor2);
        addChild("beltMotors", (SpeedControllerGroup)beltMotors);
        addChild("BottomBeam", bottom);
        addChild("IntakeBeam", intake);
        addChild("TopBeam", top);

        final ShuffleboardLayout layout = Shuffleboard.getTab("Subsystems").getLayout("Conveyor", BuiltInLayouts.kList);
        layout.withProperties(Map.of("Label position", "LEFT"));
        layout.addBoolean("intake sensor", () -> isBallIn());
        layout.addBoolean("bottom sensor", () -> isBallBottom());
        layout.addBoolean("top sensor", () -> isBallTop());
        
        /*final ShuffleboardLayout drawbridgeLayout = Shuffleboard.getTab("Subsystems").getLayout("Draw Bridge");
        drawbridgeLayout.addBoolean("is drawbridge open?", drawbridge::get);*/
    }

    @Override
    public void periodic() {
    }   

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void turnIntakeRollers(final double speed) {
        intakeMotor.set(speed);
    }

    public void turnConveyor(final double speed) {
        beltMotors.set(speed);
    }

    public boolean isBallIn() {
        return !intake.get();
    }


    public boolean isBallBottom() {
        return !bottom.get();
    }

    public boolean isBallTop() {
        return !top.get();
    }


    public boolean isFull() {
        return isBallTop();
    }

    public void stop() {
        turnConveyor(0);
        turnIntakeRollers(0);
    }
}
