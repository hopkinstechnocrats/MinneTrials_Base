// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.PhotonVision;

public class DriveSubsystem extends SubsystemBase {
  // Creating all our variables, we will initialize them and set their values later
  WPI_TalonFX leftLeader;
  WPI_TalonFX leftFollower;
  WPI_TalonFX rightLeader;
  WPI_TalonFX rightFollower;
  DifferentialDrive drive;
  public DigitalInput limitSwitch;
  PhotonVision m_PhotonVision = new PhotonVision();

  public DriveSubsystem() {

    //initialize motor controllers
    leftLeader = new WPI_TalonFX(Constants.leftLeaderCANID);
    leftFollower = new WPI_TalonFX(Constants.leftFollowerCANID);
    rightLeader = new WPI_TalonFX(Constants.rightLeaderCANID);
    rightFollower = new WPI_TalonFX(Constants.rightFollowerCANID);
    leftLeader.configFactoryDefault();
    leftFollower.configFactoryDefault();
    rightLeader.configFactoryDefault();
    rightFollower.configFactoryDefault();
    //set motors to default to braking
    leftLeader.setNeutralMode(NeutralMode.Brake);
    rightLeader.setNeutralMode(NeutralMode.Brake);
    leftFollower.setNeutralMode(NeutralMode.Brake);
    rightFollower.setNeutralMode(NeutralMode.Brake);

    // takes in a value for left speed and right speed, can also change to arcade drive for forward speed and turn
    drive = new DifferentialDrive(
      leftLeader,
      rightLeader
    );

    //Makes follower motors do the same thing as the leaders so that we don't have to pass arguments for all four
    leftFollower.follow(leftLeader);
    rightFollower.follow(rightLeader);

    // inverts left motors from the right motors because they are inverted 180 degrees
    leftFollower.setInverted(true);
    leftLeader.setInverted(true);
    
  }

  public void drive(double left, double right) {
    drive.tankDrive(left, right);
    //readable log printed to roboRIO log accessable from VScode
    System.out.println("left: "+ left+ ", right: "+ right);
  }

  public void drive1 (double x_target, double y_target){
    Transform2d Actual_TF = m_PhotonVision.GetCamData();
    double x_actual = Actual_TF.getX();
    //double y_actual = Actual_TF.getY();
    //double kp_apriltag = 1.0 / 10.0; // 100% power for a 10 inch error
    //double fwdbkwd = (x_actual - x_target) * kp_apriltag;
    //double leftright = (y_actual - y_target);
    if (x_actual>0){
      drive.tankDrive(x_target, y_target);
      System.out.println("April Tag Seen");
    } else {
      drive.tankDrive(0,0);
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
