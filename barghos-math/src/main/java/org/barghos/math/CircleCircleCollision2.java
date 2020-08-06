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

import java.util.ArrayList;
import java.util.List;

import org.barghos.math.geometry.Circle2;
import org.barghos.math.vector.vec2.Vec2;

/**
 * @author picatrix1899
 *
 */
public class CircleCircleCollision2
{
	
	public List<Vec2> getContactPoints(Circle2 c1, Circle2 c2)
	{
		List<Vec2> out = new ArrayList<>();
		
		float r0 = 5.0f;
		float a0 = 0.0f;
		float b0 = 2.0f;
		
		float r1 = 5.0f;
		float a1 = 0.0f;
		float b1 = 10.0f;
		
		float x = 3.0f;
		float y = 6.0f;
		
		boolean result = false;
		double res = 0.0f;
		
		result = Math.sqrt(-(p2((p2(b0) - p2(r0) + p2(r1) - p2(b1)) / (2*(b0-b1))) - 2*b0*(p2(b0) - p2(r0) + p2(r1) - p2(b1)) / (2*(b0-b1)) + p2(b0) -p2(r0)))  == x;
		
		res = Math.sqrt(-(p2(a0) + p2(y) - 2*b0*y + p2(b0) -p2(r0)));
		
		return out;
	}
	
	public static double p2(double in)
	{
		return Math.pow(in, 2);
	}
	
}
