package com.anim.mcustodio.animations;

import android.databinding.InverseMethod;


/* Por algum motivo essa classe não funciona em Kotlin.
 * Tentei usar a solução do link abaixo, mas não compila, gerando o erro:
 * error: cannot generate view binders java.lang.IllegalStateException: StaticIdentifierExpr is not invertible
 * https://stackoverflow.com/questions/47857875/how-to-make-a-two-way-converter-in-kotlin-working-in-java */
public class Converter {

    @InverseMethod("toLong")
    public static String toString(long value) {
        return "" + value;
    }
    public static long toLong(String value) {
        return Long.valueOf(value);
    }

    @InverseMethod("toFloat")
    public static String toString(float value) {
        return "" + value;
    }
    public static float toFloat(String value) {
        return Float.valueOf(value);
    }
}
