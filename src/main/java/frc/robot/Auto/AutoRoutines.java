package frc.robot.Auto;

import java.util.List;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import frc.robot.commands.DriveTime;
import frc.robot.commands.SpinConveyor;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.DriveSubsystem;

public class AutoRoutines {

    private final DriveSubsystem m_driveTrain;
    private final ConveyorSubsystem m_ConveyorSubsystem;



    public AutoRoutines(DriveSubsystem m_driveTrain, ConveyorSubsystem m_ConveyorSubsystem) {
        this.m_driveTrain = m_driveTrain;
        this.m_ConveyorSubsystem = m_ConveyorSubsystem;
    }

    public Command ComplexDrive(){
        final DriveTime m_driveTime1 = new DriveTime(m_driveTrain, -0.55, -0.55, 0.5);
        final DriveTime m_spinTime1 = new DriveTime(m_driveTrain, 0.55, -0.55, 0.5);
        final DriveTime m_driveTime2 = new DriveTime(m_driveTrain, -0.55, -0.55, 1);
        final DriveTime m_spinTime2 = new DriveTime(m_driveTrain, -0.55, 0.55, 0.5);
        final DriveTime m_driveTime3 = new DriveTime(m_driveTrain, -0.55, -0.55, 0.5);
        final DriveTime m_spinTime3 = new DriveTime(m_driveTrain, 0.55, -0.55, 0.5);
        final SpinConveyor m_spinConveyorDump = new SpinConveyor(m_ConveyorSubsystem, 0, false);
        final DriveTime m_driveTime4 = new DriveTime(m_driveTrain, 0.55, 0.55, 0.5);
        return new SequentialCommandGroup(
      m_driveTime1, m_spinTime1, m_driveTime2, m_driveTime3, m_spinTime2, m_spinTime3, m_driveTime4, m_spinConveyorDump
    );
    }
    
    public Command DriveAndDump(){
        final DriveTime m_driveTime_Drive = new DriveTime(m_driveTrain, -0.55, -0.55, 2);
        final SpinConveyor m_spinConveyor_Dump = new SpinConveyor(m_ConveyorSubsystem, 2, false);
        final DriveTime m_driveTime_RunAway = new DriveTime(m_driveTrain, 0.55, 0.55, 0);
        return new SequentialCommandGroup( m_driveTime_Drive, m_spinConveyor_Dump, m_driveTime_RunAway
       
        );    
    }
    


}