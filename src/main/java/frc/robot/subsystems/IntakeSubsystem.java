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

public class IntakeSubsystem extends SubsystemBase {
 
    WPI_TalonFX intakeLeader;
    WPI_TalonFX intakeFollower;

      public IntakeSubsystem() {
    intakeLeader = new WPI_TalonFX(Constants.intakeLeaderCANID);
    intakeFollower = new WPI_TalonFX(Constants.intakeLeaderCANID);

    intakeLeader.configFactoryDefault();
    intakeFollower.configFactoryDefault();

    intakeLeader.setNeutralMode(NeutralMode.Brake);
    intakeFollower.setNeutralMode(NeutralMode.Brake);

    //TODO invert? 
    intakeLeader.setInverted(false);
    intakeFollower.setInverted(true);
    intakeFollower.follow(intakeLeader);
  }

  public void SpinIntake(boolean direction){
    if(direction){
      intakeLeader.set(Constants.intakeSpeed);
    }
    else{
      intakeLeader.set(-Constants.intakeSpeed);
    }
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
