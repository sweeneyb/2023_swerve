package frc.robot.commands.auto.BlueAuto.LeftAuto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.armPositions.ArmConeHighCommand;
import frc.robot.commands.armPositions.ArmCubeHighCommand;
import frc.robot.commands.armIntake.IntakeArmConeExtractCommand;
import frc.robot.commands.armIntake.IntakeArmCubeCommand;
import frc.robot.commands.armIntake.IntakeArmCubeExtractCommand;
import frc.robot.commands.armIntake.IntakeArmStopCommand;
import frc.robot.commands.armPositions.ArmGoDown;
import frc.robot.commands.compressor.CompressorCommandExtend;
import frc.robot.commands.compressor.CompressorCommandRetract;
import frc.robot.commands.conveyor.ConveyorGoCommand;
import frc.robot.commands.conveyor.ConveyorStopCommand;
import frc.robot.commands.floorIntake.FloorIntakeCollectCommand;
import frc.robot.commands.floorIntake.FloorIntakeFartCommand;
import frc.robot.commands.floorIntake.FloorIntakeStopCommand;
import frc.robot.commands.swerve.MoveSwervePos;
import frc.robot.commands.swerve.QuickStrafe;
import frc.robot.commands.swerve.SetSwerveDrive;
import frc.robot.commands.swerve.SetSwerveIdleMode;
import frc.robot.commands.swerve.SetSwerveOdometry;
import frc.robot.commands.swerve.MoveSwerveBackPos;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.IntakeArm;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Compressor;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FloorIntake;
/*
* throttle goes straight or back
* strafe goes left rigth
*/

public class BL_OneCube extends SequentialCommandGroup {
    /*
     * 1. Arm High Drops Cone
     * 2. Arm Down & AIntake Off
     * 3. Swerve drives 60 meters
     * 4. Compressor and FIntake start running
     * 5. Collect Cube Conveyor and AIntake move
     * 6. Stop eveything
     * 7. Swerve move forward
     * 8. Get to Cube station
     */

    public BL_OneCube(DriveSubsystem swerveDrive, Arm arm, IntakeArm intakeArm, Compressor compressor, Conveyor conveyor, FloorIntake floorIntake){
        floorIntake.eyespy_coder.setPosition(0); 
        conveyor.sirSpyeye_coder.setPosition(0);

        addCommands(

            new ArmConeHighCommand(arm).andThen(new IntakeArmConeExtractCommand(intakeArm)).

            andThen(new IntakeArmStopCommand(intakeArm)).andThen(new ArmGoDown(arm)).

            andThen(new CompressorCommandExtend(compressor)).andThen(new FloorIntakeFartCommand(floorIntake)).

            andThen(new MoveSwervePos(swerveDrive)).andThen(new SetSwerveIdleMode(swerveDrive, true)).andThen(() -> swerveDrive.drive(0, 0, 0, false)).

            andThen(new CompressorCommandRetract(compressor)).andThen(new FloorIntakeStopCommand(floorIntake)).

            andThen(new MoveSwerveBackPos(swerveDrive)).andThen(new SetSwerveIdleMode(swerveDrive, true)).andThen(() -> swerveDrive.drive(0, 0, 0, false)).
            
            andThen(new QuickStrafe(swerveDrive)).andThen(new SetSwerveIdleMode(swerveDrive, true)).andThen(() -> swerveDrive.drive(0, 0, 0, false))
            // andThen(new ConveyorGoCommand(conveyor)).andThen(new IntakeArmCubeCommand(intakeArm)).

            // andThen(new ConveyorStopCommand(conveyor)).andThen(new IntakeArmStopCommand(intakeArm))

        );



    }

} // <--
