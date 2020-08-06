/*
MIT License

Copyright (c) 2019 picatrix1899

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package org.barghos.math;

/**
 * This class contains mathematical methods and precalculated constants.
 */
public class Maths
{
	/**
	 * Contains the implementations for unsafe math operations like sqrt, sin, cos etc.
	 */
	public static UnsafeMath UNSAFE_MATH_IMPLEMENTATION = JavaUnsafeMath.getInstance();
	
	/**
	 * The number PI with 11 digits after point.
	 */
	public static final double PI = 3.14159265359;
	
	/**
	 * The inverse of PI (1 / PI).
	 */
	public static final double INV_PI = 0.31830988618;
	
	/** 
	 * This is the precalculated Half-PI (PI / 2). It's used in some trigonomic calculations.
	 */
	public static final double HALF_PI = 1.570796326795;
	
	/**
	 * This value is a precalculated scalar for conversation from degrees to radians.
	 * Just multiply it with an angle messured in degree and you get the angle in radians.
	 */
	public static final double DEG_TO_RAD = 0.017453292519944444;
	/**
	 * This value is a precalculated scalar for conversation from radians to degrees.
	 * Just multiply it with an angle messured in radians and you get the angle in degrees.
	 */
	public static final double RAD_TO_DEG = 57.29577951307855;
	
	/**
	 * This is a precalculated inversed square-root of the number 2 (1 / sqrt(2)).
	 * It's commonly used for precalculated diagonal normal vectors i.e. vec3(1,1,0).
	 */
	public static final double INV_SQRT2 = 0.7071067811865475;
	
	/**
	 * This is a precalculated inversed square-root of the number 2 (1 / sqrt(2)).
	 * It's commonly used for precalculated diagonal normal vectors i.e. vec3(1,1,0).
	 */
	public static final float INV_SQRT2f = 0.7071067811865475f;

	/**
	 * This is a precalculated inversed square-root of the number 3 (1 / sqrt(3)).
	 * It's commonly used for precalculated diagonal normal vectors i.e. vec3(1,1,1).
	 */
	public static final double INV_SQRT3 = 0.5773502691896258;
	
	/**
	 * This is a precalculated inversed square-root of the number 3 (1 / sqrt(3)).
	 * It's commonly used for precalculated diagonal normal vectors i.e. vec3(1,1,1).
	 */
	public static final float INV_SQRT3f = 0.5773502691896258f;
	
	public static final double SQRT2 = 1.4142135623730951;
	
	public static final double SQRT3 = 1.7320508075688772;
	
	/** A very small number used for determining if a floating point number is technically zero. */
	public static final double SMALL_NUMBER = 1.e-8;

	/**
	 * A very small number used for determining if two floating point numbers
	 * are close enough together to count as equal.
	 */
	public static final double KINDA_SMALL_NUMBER = 1.e-4;
	
	/**
	 * Checks if the value is zero with given tolerance.
	 * @param value the value to check for 0.
	 * @param tolerance The threshold around 0.
	 * @return is the value 0.
	 */
	public static boolean isZero(double value, double tolerance)
	{
		return Math.abs(value) <= tolerance;
	}
	
	/**
	 * Checks if the value is zero with tolerance of SMALL_NUMBER(1.e-8f).
	 * @param value the value to check for 0.
	 * @return is the value 0.
	 */
	public static boolean isZero(double value)
	{
		return isZero(value, Maths.SMALL_NUMBER);
	}
	
	public static boolean isExactNaN(double value)
	{
		return Double.isNaN(value);
	}
	
	public static boolean isNaN(double value)
	{
		return !Double.isFinite(value);
	}
	
	public static boolean isExactZero(double value)
	{
		return value == 0.0;
	}
	
	public static double roundFromZero(double value)
	{
		return value > 0 ? Math.ceil(value) : value < 0 ? Math.floor(value) : 0;
	}
	
	public static double roundToZero(double value)
	{
		return value > 0 ? Math.floor(value) : value < 0 ? Math.ceil(value) : 0;
	}
	
	public static double roundToPosInf(double value)
	{
		return Math.ceil(value);
	}
	
	public static double roundToNegInf(double value)
	{
		return Math.floor(value);
	}
	
	public static byte clamp(byte value, byte min, byte max)
	{
		return value < min ? min : value > max ? max : value;
	}
	
	public static char clamp(char value, char min, char max)
	{
		return value < min ? min : value > max ? max : value;
	}
	
	public static long clamp(long value, long min, long max)
	{
		return value < min ? min : value > max ? max : value;
	}
	
	public static float clamp(float value, float min, float max)
	{
		return value < min ? min : value > max ? max : value;
	}
	
	public static int clamp(int value, int min, int max)
	{
		return value < min ? min : value > max ? max : value;
	}
	
	public static double clamp(double value, double min, double max)
	{
		return value < min ? min : value > max ? max : value;
	}

	public static byte clampMin(byte value, byte min)
	{
		return value < min ? min : value;
	}
	
	public static char clampMin(char value, char min)
	{
		return value < min ? min : value;
	}
	
	public static long clampMin(long value, long min)
	{
		return value < min ? min : value;
	}
	
	public static float clampMin(float value, float min)
	{
		return value < min ? min : value;
	}
	
	public static int clampMin(int value, int min)
	{
		return value < min ? min : value;
	}
	
	public static double clampMin(double value, double min)
	{
		return value < min ? min : value;
	}

	public static byte clampMax(byte value, byte max)
	{
		return value > max ? max : value;
	}
	
	public static char clampMax(char value, char max)
	{
		return value > max ? max : value;
	}
	
	public static long clampMax(long value, long max)
	{
		return value > max ? max : value;
	}
	
	public static float clampMax(float value, float max)
	{
		return value > max ? max : value;
	}
	
	public static int clampMax(int value, int max)
	{
		return value > max ? max : value;
	}
	
	public static double clampMax(double value, double max)
	{
		return value > max ? max : value;
	}
	
	public static byte selectByZero(byte comparand, byte valueGEZ, byte valueLZ)
	{
		return comparand >= 0 ? valueGEZ : valueLZ;
	}
	
	public static char selectByZero(char comparand, char valueGEZ, char valueLZ)
	{
		return comparand >= 0 ? valueGEZ : valueLZ;
	}
	
	public static long selectByZero(long comparand, long valueGEZ, long valueLZ)
	{
		return comparand >= 0 ? valueGEZ : valueLZ;
	}
	
	public static int selectByZero(int comparand, int valueGEZ, int valueLZ)
	{
		return comparand >= 0 ? valueGEZ : valueLZ;
	}
	
	public static float selectByZero(float comparand, float valueGEZ, float valueLZ)
	{
		return comparand >= 0.0f ? valueGEZ : valueLZ;
	}
	
	public static double selectByZero(double comparand, double valueGEZ, double valueLZ)
	{
		return comparand >= 0.0f ? valueGEZ : valueLZ;
	}
	
	public static boolean isMultipleOfTwo(int value)
	{
		return (value & value - 1) == 0;
	}
	
	public static double gridSnap(double value, double gridSize)
	{
		return Math.floor((value + 0.5 * gridSize) / gridSize) * gridSize;
	}
	
	
	public static float gridSnap(float value, float gridSize)
	{
		return (float) (Math.floor((value + 0.5f * gridSize) / gridSize) * gridSize);
	}
	
	public static double reciprocal(double value)
	{
		return 1.0 / value;
	}
	
	public static double reciprocalSafe(double value)
	{
		return isZero(value) ? 0.0 : 1.0 / value;
	}
	
	public static double reciprocalSafe(double value, double tolerance)
	{
		return isZero(value, tolerance) ? 0.0 : 1.0 / value;
	}
	
	public static double sin(double a)
	{
		return Maths.UNSAFE_MATH_IMPLEMENTATION.sin(a);
	}
	
	public static double cos(double a)
	{
		return Maths.UNSAFE_MATH_IMPLEMENTATION.cos(a);
	}
	
	public static double sqrt(double a)
	{
		return Maths.UNSAFE_MATH_IMPLEMENTATION.sqrt(a);
	}
}
