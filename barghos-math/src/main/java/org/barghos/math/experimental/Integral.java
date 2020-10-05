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

package org.barghos.math.experimental;

import java.util.function.Function;

/**
 * @author picatrix1899
 *
 */
public class Integral
{
	public static float intSimpsonf(float min, float max, int iterations, Function<Float,Float> f)
	{
		int steps = iterations + 1;
		
		float deltaX = (max - min) / steps;
		
		float res = f.apply(min) + f.apply(max);
		
		int z = 4;
		
		for(int i = 1; i < steps; i++)
		{
			res = res + f.apply(min + i * deltaX);
			
			z = 6 - z;
		}
		
		res = (deltaX / 3.0f) * res;
		
		return res;
	}
	
	public static double intSimpson(double min, double max, int iterations, Function<Double,Double> f)
	{
		int steps = iterations + 1;
		
		double deltaX = (max - min) / steps;
		
		double res = f.apply(min) + f.apply(max);
		
		int z = 4;
		
		for(int i = 1; i < steps; i++)
		{
			res = res + z * f.apply(min + i * deltaX);
			
			z = 6 - z;
		}
		
		res = (deltaX / 3.0) * res;
		
		return res;
	}
}
