package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp()
public class Carp_Elem_Teleop extends OpMode {

    // defining variables used
    DcMotor leftFront;
    DcMotor leftBack;
    DcMotor rightFront;
    DcMotor rightBack;
    Servo leftClaw = null;
    Servo rightClaw = null;
    DcMotor linearSlideRight = null;

    // will be used for linear slides encoder
    int linearSlidePos = 0;


    public void init() {
        // telling the robot what it is called
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        leftClaw = hardwareMap.get(Servo.class, "leftClaw");
        rightClaw = hardwareMap.get(Servo.class, "rightClaw");
        linearSlideRight = hardwareMap.get(DcMotor.class, "linearSlideRight");

        linearSlideRight.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftClaw.setPosition(1);
        rightClaw.setPosition(0);


    }

    public void loop() {
        // variables for equation used for mecanum wheels
        double forward = gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x * 1.1;
        double rotation = -gamepad1.right_stick_x;

        // actual math for mecanum wheels
        double denominator = Math.max(Math.abs(forward) + Math.abs(strafe) + Math.abs(rotation), 1);
        double leftFrontPower = (forward + strafe + rotation) / denominator;
        double leftBackPower = (forward - strafe + rotation) / denominator;
        double rightFrontPower = (forward - strafe - rotation) / denominator;
        double rightBackPower = (forward + strafe - rotation) / denominator;

        // setting power based on the equation
        leftFront.setPower(leftFrontPower / 3);
        leftBack.setPower(leftBackPower / 3);
        rightFront.setPower(rightFrontPower / 3);
        rightBack.setPower(rightBackPower / 3);

        // closing the claw
        if (gamepad1.right_bumper) {
            leftClaw.setPosition(1);
            rightClaw.setPosition(0);


        }
        // opening the claw
        if (gamepad1.left_bumper) {
            leftClaw.setPosition(0.5);
            rightClaw.setPosition(0.5);

        }

        if (gamepad1.left_trigger > 0) {
            linearSlideRight.setPower(-gamepad1.left_trigger/2.5);

        }
        else if (gamepad1.right_trigger > 0) {
            linearSlideRight.setPower(gamepad1.right_trigger/2.5);
        }

        else if (gamepad1.right_trigger == 0) {
            linearSlideRight.setPower(0);
        }


    }

}
