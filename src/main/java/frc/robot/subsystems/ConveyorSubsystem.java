// Copyright (c) FIRST and other WPILib contributors.
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
  //instantiate variables
  WPI_TalonFX conveyorMotor;

  public ConveyorSubsystem() {
    //initialize variables
    conveyorMotor = new WPI_TalonFX(Constants.conveyerMotorID);
    conveyorMotor.configFactoryDefault();
    conveyorMotor.setNeutralMode(NeutralMode.Brake);


    
  }
  
  
  public void Spin(Boolean direction){
    //if true spin conveyer forwards
    if(direction){
      conveyorMotor.set(Constants.conveyorSpeed);
    }
    //if false spin conveyer backwards
    else{
      conveyorMotor.set(-Constants.conveyorSpeed);
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
