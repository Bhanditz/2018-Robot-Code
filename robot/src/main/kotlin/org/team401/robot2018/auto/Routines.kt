package org.team401.robot2018.auto

import org.team401.robot2018.auto.motion.RioProfileRunner
import org.team401.robot2018.auto.motionprofile.ArcProfileFollower
import org.team401.robot2018.auto.steps.AutoStep
import org.team401.robot2018.auto.steps.DelayStep
import org.team401.robot2018.auto.steps.LambdaStep
import org.team401.robot2018.auto.steps.ParallelSteps
import org.team401.robot2018.constants.Constants
import org.team401.robot2018.etc.LED
import org.team401.robot2018.etc.StepAdder
import org.team401.robot2018.subsystems.Drivetrain

/*
 * 2018-Robot-Code - Created on 3/3/18
 * Author: Cameron Earle
 * 
 * This code is licensed under the GNU GPL v3
 * You can find more info in the LICENSE file at project root
 */

/**
 * @author Cameron Earle
 * @version 3/3/18
 */
object Routines {
    lateinit var add: StepAdder

    fun drive(profile: String, vararg otherActions: AutoStep) {
        val step = ArcProfileFollower(Drivetrain)

        step.load("/home/lvuser/profiles/${profile}_C.csv")

        add(ParallelSteps(step, *otherActions))
    }

    fun drive(start: Any, end: Any, vararg otherActions: AutoStep) = drive("$start-$end", *otherActions)

    fun score() {
        add(Commands.ElevatorHolderUnclamp())
        add(Commands.ElevatorKickerScore())
        add(LambdaStep { LED.signalScoreCube() })
        add(DelayStep(0.5))
        add(Commands.ElevatorKickerRetract())
    }

    fun intake() {
        add(Commands.ElevatorToGround())
        add(Commands.IntakeWheelsRun())
        add(Commands.IntakeToGrab())
    }

    fun setup() {
        add(Commands.UnhomeElevator())
        add(Commands.ResetHeading())
        add(Commands.IntakeToStow()) //This actually homes the intake, not stow
    }
}