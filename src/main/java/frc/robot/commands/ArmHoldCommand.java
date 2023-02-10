package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class ArmHoldCommand extends CommandBase {
    Arm arm;

    public ArmHoldCommand(Arm arm) {
        addRequirements(arm);
        this.arm = arm;
    }

    public void execute() {
        arm.armHold();
    }

} // <-- keepy brace
