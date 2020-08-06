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

public class FastMath
{

	public static double STEP = 0.00001;
	private static double[] sin_table;
	
	static
	{
		sin_table = new double[(int)(90 / STEP)];
		
		for(int d = 0; d < (int)(90 / STEP); d++)
		{
			sin_table[d] = Math.sin(d * STEP * Maths.DEG_TO_RAD);
		}
	}
	
	public static double sinDeg(double deg)
	{
		deg = deg % 360;
		deg = deg > 0 ? deg : 360 - deg;
		
		int phase = (int)(deg / 90);
		int sign = phase < 2 ? 1 : -1;
		boolean invert = phase == 1 || phase == 3;
		
		deg = deg % 90;
		
		deg = invert ? 90.0 - deg : deg;
		
		double recStep = 1 / STEP;
		
		double r = deg % STEP;
		
		
		if(r == 0)
		{
			return sign * sin_table[(int)(deg * recStep)];
		}
		else
		{
			int index1 = (int)((deg - r) * recStep);
			int index2 = index1 + 1;

			double val1 = sin_table[index1];
			double val2 = sin_table[index2];
			
			double rest = r * recStep;
			
			return sign * (val1 * (1 - rest) + val2 * rest);
		}
	}
}
