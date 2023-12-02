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
import frc.robot.subsystems.IntakeSubsystem;
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

  private final ConveyorSubsystem conveyorSubsystem = new ConveyorSubsystem();

  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();

  private final AutoRoutines m_autoRoutines = new AutoRoutines(driveSubsystem);
  
  private final DriveTime m_DriveTime1 = new DriveTime(driveSubsystem, -0.55, -0.55, 0.5);

  private SendableChooser<Command> m_autoChooser = new SendableChooser<>();

  private final XboxController driveController = new XboxController(Constants.XboxControllerPort);
  
  private final XboxController operatorController = new XboxController(1);

  

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_autoChooser.setDefaultOption("DriveAutoSimple Auto", m_autoRoutines.DriveAutoSimple());
    // Configure the button bindings
    configureButtonBindings();
    driveSubsystem.setDefaultCommand(
            new RunCommand(
                    () -> {
                      driveSubsystem.drive(-Constants.maxMotorPower*driveController.getLeftY(), 
                      -Constants.maxMotorPower*driveController.getRightY());
                    }
            , driveSubsystem)
    );
    conveyorSubsystem.setDefaultCommand(new RunCommand(() -> {
      conveyorSubsystem.NoSpin();
    }, conveyorSubsystem));
    
    /*intakeSubsystem.setDefaultCommand(new RunCommand(() -> {
      intakeSubsystem.NoSpin();
    }, intakeSubsystem)); */
  
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
    JoystickButton xButton = new JoystickButton(operatorController, 3);
    JoystickButton aDriverButton = new JoystickButton(driveController, 1);
    JoystickButton bDriverButton = new JoystickButton(driveController, 2);

    aButton.whileTrue(new RunCommand(() -> conveyorSubsystem.ConveyorWheels()));
    bButton.whileTrue(new RunCommand(() -> intakeSubsystem.SpinIntake(true)));
    xButton.whileTrue(new RunCommand(() -> conveyorSubsystem.negConveyorWheels()));
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

     return new SequentialCommandGroup(m_DriveTime1);
    //return m_autoRoutines.DriveAutoSimple();
  }
}
