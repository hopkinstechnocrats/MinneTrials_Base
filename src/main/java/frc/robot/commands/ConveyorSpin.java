package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ConveyorSubsystem;

public class ConveyorSpin extends CommandBase {
  private long m_startTime;
  private final double m_duration;
  private ConveyorSubsystem m_ConveyorSubsystem;

/** Creates a new ConveyorSpin. */
  public ConveyorSpin(ConveyorSubsystem subsystem, double duration) {
    m_duration = duration;
    m_ConveyorSubsystem = subsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_startTime = System.currentTimeMillis();
  }


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   m_ConveyorSubsystem.ConveyorWheels();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
   return (System.currentTimeMillis() - m_startTime) >= m_duration;
  }
}