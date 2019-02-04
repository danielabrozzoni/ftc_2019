package org.pizzapastarobottino.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


public class Configs {
    public static final String motorRuotaPosterioreDX = "posteriore_destra";
    public static final String motorRuotaPosterioreSX = "posteriore_sinistra";
    public static final String motorRuotaAnterioreDX = "anteriore_destra";
    public static final String motorRuotaAnterioreSX = "anteriore_sinistra";
    public static final String colorSensor = "sensore_colore";

    //coefficenti di rotazione dei motori, impostandoli ad un valore minore di 1 il motore girerà meno di quanto impostato
    public static final double ruotaPosterioreDXrotationFactor = 1.0;
    public static final double ruotaPosterioreSXrotationFactor = 1.0;
    public static final double ruotaAnterioreDXrotationFactor = 1.0;
    public static final double ruotaAnterioreSXrotationFactor = 1.0;

    public static final double pinzaSXDirectionFactor = -1.0;
    public static final double pinzaDXDirectionFactor = 1.0;

    public static final double pinzaSXPartenzaFactor = 1.0;
    public static final double pinzaDXPartenzaFactor = 0.0;

    public static final AngleUnit unit = AngleUnit.DEGREES;

    //1 oppure -1, determina se invertire il senso di sterzata
    public static final int xDirectionFactor = 1;
    public static final int yDirectionFactor = -1;

    public static final DcMotorSimple.Direction avanti = DcMotorSimple.Direction.FORWARD;
    public static final DcMotorSimple.Direction indietro = DcMotorSimple.Direction.REVERSE;

}