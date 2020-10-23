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

import org.barghos.math.experimental.Integral;
import org.barghos.math.vector.vec2.Vec2f;

/**
 * @author picatrix1899
 *
 */
public class MainTest
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		float a = -1.0f;
		float b = 1.0f;
		
		float d = (b-a) / 3.0f;
		
		Math.abs(1.0f);
		
		float result = (d / 3.0f) * (f(a) + 4.0f*f(a+1.0f*d) + 2*f(a+2.0f*d) + f(b));
		System.out.println(result);
		
		System.out.println(Integral.intSimpsonf(-1.0f, 1.0f, 9, (x) -> { return f(x);}));
	}

	public static float f(float x)
	{
		return -1.0f * (float)Math.pow(x, 2) + 1.0f;
	}
}
