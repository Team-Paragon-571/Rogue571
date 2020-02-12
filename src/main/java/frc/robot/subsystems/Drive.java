/**
 * The drive subsystem.
 * This contains methods for the arcade
 * @author Levi the Super Genius
 */

package frc.robot.subsystems;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class Drive extends SubsystemBase {

    private final DifferentialDrive differentialDrive;
    private final SpeedController left = new SpeedControllerGroup(new WPI_TalonSRX(1), new WPI_TalonSRX(2));
    private final SpeedController right = new SpeedControllerGroup(new WPI_TalonSRX(3), new WPI_TalonSRX(4));
    public AHRS navx;
    private final ShuffleboardLayout distance;

    public Drive() {

        differentialDrive = new DifferentialDrive(left, right);
        addChild("Differential Drive", differentialDrive);
        differentialDrive.setSafetyEnabled(true);
        differentialDrive.setExpiration(0.1);
        differentialDrive.setMaxOutput(1.0);

        // init navx
        try {
            navx = new AHRS(SPI.Port.kMXP);
            navx.zeroYaw();
        } catch (RuntimeException exe) {
            DriverStation.reportError("NavX init failed- some autonomous components may not work right!", true);
        }

        distance = Shuffleboard.getTab("Commands").getLayout("Distances", BuiltInLayouts.kList);
        putToDashboard(); // put values onto dashboard

    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    /**
     * arcade drive for teleop period
     * 
     * @param speed joystick y
     * @param twist joystick z
     */

    public void arcadeDrive(double speed, double twist) {
        differentialDrive.arcadeDrive(speed, twist);
    }

    /**
     * tank drive for autonomous
     * 
     * @param left  speed of left side
     * @param right speed of right side
     */

    public void tankDrive(double left, double right) {
        differentialDrive.tankDrive(left, right);
    }

    /**
     * returns the navx heading
     * 
     * @return navx yaw
     */

    public double getHeading() {
        return navx.getYaw();
    }

    /**
     * calculates distance using pythagorean theorem
     * 
     * @return distance since last reset
     */
    public double getDistance() {
        double up = navx.getDisplacementY(); // leg 1
        double acr = navx.getDisplacementX(); // leg 2
        double dist = Math.sqrt((up * up) + (acr * acr)); // hypotinuse
        return dist;
    }

    /**
     * calculates acceleration using pythagorean theorem
     * 
     * @return acceleration
     */
    public double getAcceleration() {
        float up = navx.getWorldLinearAccelX(); // leg 1
        float acr = navx.getWorldLinearAccelY(); // leg 2
        double accel = (double) Math.sqrt((up * up) + (acr * acr)); // hypotinuse
        return accel;
    }

    /**
     * Puts navX values on dashboard
     */
    private void putToDashboard() {
        final DoubleSupplier distanceSupplier = () -> getDistance();
        final DoubleSupplier yawSupplier = () -> navx.getYaw();
        final DoubleSupplier speedSupplier = () -> getAcceleration();
        final BooleanSupplier connectionSupplier = () -> navx.isConnected();
        final BooleanSupplier calibrationSupplier = () -> navx.isCalibrating();

        distance.addNumber("distance", distanceSupplier);
        distance.addNumber("yaw", yawSupplier);
        distance.addNumber("acceleration", speedSupplier);
        distance.addBoolean("connected", connectionSupplier);
        distance.addBoolean("calibrating", calibrationSupplier);

    }

    public void stop() {
        tankDrive(0, 0);
    }

}
