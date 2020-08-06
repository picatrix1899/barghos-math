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

package org.barghos.math.matrix;

import org.barghos.core.tuple2.Tup2f;
import org.barghos.core.tuple2.api.Tup2fR;

public class Mat2
{
	public final float[][] m = new float[2][2];
	
	public Mat2() {  }
	public Mat2(Mat2 m) { set(m); }
	
	public Mat2 set(Mat2 m)
	{
		this.m[0][0] = m.m[0][0]; this.m[0][1] = m.m[0][1];
		this.m[1][0] = m.m[1][0]; this.m[1][1] = m.m[1][1];
		return this;
	}
	
	public Mat2 identity()
	{
		this.m[0][0] = 1.0f; this.m[0][1] = 0.0f;
		this.m[1][0] = 0.0f; this.m[1][1] = 1.0f;
		return this;
	}

	public Mat2 zero()
	{
		this.m[0][0] = 0.0f; this.m[0][1] = 0.0f;
		this.m[1][0] = 0.0f; this.m[1][1] = 0.0f;
		return this;
	}
	
	public Mat2 scale(Tup2fR t)
	{
		this.m[0][0] = t.getX(); this.m[0][1] = 0.0f;
		this.m[1][0] = 0.0f; this.m[1][1] = t.getY();
		return this;
	}
	
	public Mat2 scale(double x, double y)
	{
		this.m[0][0] = (float)x; this.m[0][1] = 0.0f;
		this.m[1][0] = 0.0f; this.m[1][1] = (float)y;
		return this;
	}
	
	public Mat2 setRow(int index, Tup2fR t)
	{
		this.m[index][0] = t.getX();
		this.m[index][1] = t.getY();
		return this;
	}
	
	public Mat2 setColumn(int index, Tup2fR t)
	{
		this.m[0][index] = t.getX();
		this.m[1][index] = t.getY();
		return this;
	}

	public Tup2f getRow(int index) { return new Tup2f(this.m[index][0], this.m[index][1]); }
	public Tup2f getColumn(int index) { return new Tup2f(this.m[0][index], this.m[1][index]); }
	
	public Tup2f getRow(int index, Tup2f t) { return t.set(this.m[index][0], this.m[index][1]); }
	public Tup2f getColumn(int index, Tup2f t) { return t.set(this.m[0][index], this.m[1][index]); }
	
	public static float determinant(Mat2 m)
	{
		return (float) MatUtils.det2x2( m.m[0][0], m.m[0][1],
								m.m[1][0], m.m[1][1]);
	}
	
	public static Mat2 transpose(Mat2 m, Mat2 res)
	{
		if(res == null) res = new Mat2();
		
		Tup2f r0 = m.getRow(0);
		Tup2f r1 = m.getRow(1);

		return res.setColumn(0, r0).setColumn(1, r1);
	}
}
