package org.team401.robot2018.auto

import org.snakeskin.dsl.machine
import org.team401.robot2018.Constants
import org.team401.robot2018.Sequences
import org.team401.robot2018.auto.steps.AutoStep
import org.team401.robot2018.auto.steps.LambdaStep
import org.team401.robot2018.auto.steps.StateStep
import org.team401.robot2018.subsystems.*

/*
 * 2018-Robot-Code - Created on 1/23/18
 * Author: Cameron Earle
 * 
 * This code is licensed under the GNU GPL v3
 * You can find more info in the LICENSE file at project root
 */

/**
 * @author Cameron Earle
 * @version 1/23/18
 */

object Commands {
    val DeployElevator = LambdaStep(Sequences::deployElevator)
    val HomeElevator = object : AutoStep() {
        override fun entry() {
            ElevatorSubsystem.machine(ELEVATOR_MACHINE).setState(ElevatorStates.HOMING)
        }

        override fun action() {
            done = Elevator.homed
        }

        override fun exit() {}
    }

    val HoldElevator = StateStep(ElevatorSubsystem, ELEVATOR_MACHINE, ElevatorStates.HOLD_POS_UNKNOWN)

    val ElevatorToGround = StateStep(ElevatorSubsystem, ELEVATOR_MACHINE, ElevatorStates.POS_COLLECTION)
    val ElevatorToSwitch = StateStep(ElevatorSubsystem, ELEVATOR_MACHINE, ElevatorStates.POS_SWITCH)
    val ElevatorToScale = StateStep(ElevatorSubsystem, ELEVATOR_MACHINE, ElevatorStates.POS_SCALE_HIGH)
    val ElevatorKickerScore = StateStep(ElevatorSubsystem, ELEVATOR_KICKER_MACHINE, ElevatorKickerStates.KICK)
    val ElevatorKickerRetract = StateStep(ElevatorSubsystem, ELEVATOR_KICKER_MACHINE, ElevatorKickerStates.STOW)
    val ElevatorHolderClamp = StateStep(ElevatorSubsystem, ELEVATOR_CLAMP_MACHINE, ElevatorClampStates.CLAMPED)
    val ElevatorHolderUnclamp = StateStep(ElevatorSubsystem, ELEVATOR_CLAMP_MACHINE, ElevatorClampStates.UNCLAMPED)
}