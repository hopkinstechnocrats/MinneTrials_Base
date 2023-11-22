// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.time.Duration;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

public class DriveTime extends CommandBase {
  private DriveSubsystem subsystem;

  private final double m_duration;
  private final double m_speedL;
  private final double m_speedR;
  private long m_startTime;

/** Creates a new DriveToWall. */
  public DriveTime(DriveSubsystem subsystem, double speedL, double speedR, double duration) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
    this.subsystem = subsystem;
    //multiplying duration by 1000 to change input seconds into output of milliseconds
    m_duration = duration * 1000;
    m_speedL = speedL;
    m_speedR = speedR;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_startTime = System.currentTimeMillis();
    
  }


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    subsystem.drive(m_speedL, m_speedR);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // stops driving forwards when the code has been running for longer than duration inputed
    return (System.currentTimeMillis() - m_startTime) >= m_duration;
  }
}
