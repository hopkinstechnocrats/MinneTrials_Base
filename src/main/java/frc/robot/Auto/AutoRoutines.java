package frc.robot.Auto;

import java.util.List;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import frc.robot.commands.DriveTime;
import frc.robot.subsystems.DriveSubsystem;

public class AutoRoutines {

    private final DriveSubsystem m_driveTrain;



    public AutoRoutines(DriveSubsystem m_driveTrain) {
        this.m_driveTrain = m_driveTrain;
    }

    public Command DriveForwards(){
        final DriveTime m_driveTime1 = new DriveTime(m_driveTrain, -0.55, 3);
        
        return new SequentialCommandGroup(
        m_driveTime1
    );

    }



}