// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ConveyorSubsystem extends SubsystemBase {
 
    WPI_TalonFX conveyorLeader;
    WPI_TalonFX conveyorFollower;

  public ConveyorSubsystem() {
    conveyorLeader = new WPI_TalonFX(Constants.conveyorLeaderCANID);
    conveyorFollower = new WPI_TalonFX(Constants.conveyorFollowerCANID);

    conveyorLeader.configFactoryDefault();
    conveyorFollower.configFactoryDefault();

    conveyorFollower.follow(conveyorLeader);
    conveyorFollower.setInverted(true);


  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
