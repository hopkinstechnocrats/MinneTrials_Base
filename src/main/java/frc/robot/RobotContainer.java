// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Auto.AutoRoutines;
import frc.robot.commands.DriveTime;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();

  private final ConveyorSubsystem m_conveyorSubsystem = new ConveyorSubsystem();

  private final XboxController driveController = new XboxController(Constants.XboxControllerPort);
  
  private final XboxController operatorController = new XboxController(1);

  private final AutoRoutines m_autoRoutines = new AutoRoutines(driveSubsystem, m_conveyorSubsystem);

  private SendableChooser<Command> m_autoChooser = new SendableChooser<>();

  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    m_autoChooser.setDefaultOption("Drive And Dump Auto", m_autoRoutines.DriveAndDump());
    configureButtonBindings();
    driveSubsystem.setDefaultCommand(
            new RunCommand(
                    () -> {
                      //controllers give negative output for forwards
                      driveSubsystem.drive(-Constants.maxMotorOutput*driveController.getLeftY(), 
                      -Constants.maxMotorOutput*driveController.getRightY());
                    }
            , driveSubsystem)
    );
  
    m_conveyorSubsystem.setDefaultCommand(
      new RunCommand(
              () -> {
                
                m_conveyorSubsystem.Spin(null);
              }
      , m_conveyorSubsystem)
    );
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton aButton = new JoystickButton(operatorController, 1);
    JoystickButton bButton = new JoystickButton(operatorController, 2);
    JoystickButton aDriverButton = new JoystickButton(driveController, 1);
    JoystickButton bDriverButton = new JoystickButton(driveController, 2);
    
    // makes it so that the A button spins the conveyor forwards and B spins it backwards
    aButton.whileTrue(new RunCommand(() -> m_conveyorSubsystem.Spin(true), m_conveyorSubsystem));
    bButton.whileTrue(new RunCommand(() -> m_conveyorSubsystem.Spin(false), m_conveyorSubsystem));
  }
   
  

  public DriveSubsystem getDriveSubsystem() {
    return driveSubsystem;
  }
  
  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    
    return m_autoChooser.getSelected();
  }
}
