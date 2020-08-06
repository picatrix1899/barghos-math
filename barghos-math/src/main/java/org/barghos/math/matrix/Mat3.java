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

import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.tuple2.api.Tup2fR;
import org.barghos.core.tuple3.Tup3f;
import org.barghos.core.tuple3.api.Tup3fR;
import org.barghos.core.tuple3.api.Tup3fW;
import org.barghos.core.tuple3.pool.Tup3fPool;
import org.barghos.math.vector.quat.Quat;

public class Mat3
{
	public static final Mat3 IDENTITY = Mat3.identity();
	
	public static final int ROWS = 3;
	public static final int COLUMNS = 3;
	
	public final float[][] m = new float[3][3];
	
	public Mat3() {  }
	public Mat3(Mat3 m) { if(m == null) throw new ArgumentNullException("m"); set(m); }

	public Mat3 set(Mat3 m)
	{
		if(m == null) throw new ArgumentNullException("m");
	
		for(int r = 0; r < ROWS; r++)
			for(int c = 0; c < COLUMNS; c++)
				this.m[r][c] = m.m[r][c];

		return this;
	}
	
	public Mat3 initIdentity()
	{
		setRow(0, 1.0f, 0.0f, 0.0f);
		setRow(1, 0.0f, 1.0f, 0.0f);
		setRow(2, 0.0f, 0.0f, 1.0f);
		return this;
	}

	public Mat3 initZero()
	{
		setRow(0, 0.0f, 0.0f, 0.0f);
		setRow(1, 0.0f, 0.0f, 0.0f);
		setRow(2, 0.0f, 0.0f, 0.0f);

		return this;
	}
	
	public Mat3 initScaling(Tup3fR t)
	{
		if(t == null) throw new ArgumentNullException("t");
		return initScaling(t.getX(), t.getY(), t.getZ());
	}
	
	public Mat3 initScaling(float x, float y, float z)
	{
		setRow(0, x, 0.0f, 0.0f);
		setRow(1, 0.0f,	y, 0.0f);
		setRow(2, 0.0f,	0.0f, z);
		return this;
	}
	
	public Mat3 initRotation(Quat q)
	{
		if(q == null) throw new ArgumentNullException("q");
		
		this.m[0][0] = 1.0f - 2.0f	*	(q.getY() * q.getY() + q.getZ() * q.getZ());
		this.m[0][1] = 2.0f			*	(q.getX() * q.getY() - q.getW() * q.getZ());
		this.m[0][2] = 2.0f			*	(q.getX() * q.getZ() + q.getW() * q.getY());
		
		this.m[1][0] = 2.0f			*	(q.getX() * q.getY() + q.getW() * q.getZ());
		this.m[1][1] = 1.0f - 2.0f	*	(q.getX() * q.getX() + q.getZ() * q.getZ());
		this.m[1][2] = 2.0f			*	(q.getY() * q.getZ() - q.getW() * q.getX());
		
		this.m[2][0] = 2.0f			*	(q.getX() * q.getZ() - q.getW() * q.getY());
		this.m[2][1] = 2.0f			*	(q.getY() * q.getZ() + q.getW() * q.getX());
		this.m[2][2] = 1.0f - 2.0f	*	(q.getX() * q.getX() + q.getY() * q.getY());;

		return this;
	}
	
	public Mat3 setRow(int index, Tup2fR t, float z)
	{
		if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
		if(t == null) throw new ArgumentNullException("t");
		return setRow(index, t.getX(), t.getY(), z);
	}
	
	public Mat3 setRow(int index, float x, Tup2fR t)
	{
		if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
		if(t == null) throw new ArgumentNullException("t");
		return setRow(index, x, t.getX(), t.getY());
	}
	
	public Mat3 setRow(int index, Tup3fR t)
	{
		if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
		if(t == null) throw new ArgumentNullException("t");
		return setRow(index, t.getX(), t.getY(), t.getZ());
	}
	
	public Mat3 setRow(int index, float x, float y, float z)
	{
		if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
		this.m[index][0] = x;
		this.m[index][1] = y;
		this.m[index][2] = z;
		return this;
	}
	
	public Mat3 setColumn(int index, Tup2fR t, float z)
	{
		if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
		return setColumn(index, t.getX(), t.getY(), z);
	}
	
	public Mat3 setColumn(int index, float x, Tup2fR t)
	{
		if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
		return setColumn(index, x, t.getX(), t.getY());
	}
	
	public Mat3 setColumn(int index, Tup3fR t)
	{
		if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
		if(t == null) throw new ArgumentNullException("t");
		return setColumn(index, t.getX(), t.getY(), t.getZ());
	}

	public Mat3 setColumn(int index, float x, float y, float z)
	{
		if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
		this.m[0][index] = x;
		this.m[1][index] = y;
		this.m[2][index] = z;
		return this;
	}
	
	public Tup3f getRow(int index) { return new Tup3f(this.m[index][0], this.m[index][1], this.m[index][2]); }
	public Tup3f getColumn(int index) { return new Tup3f(this.m[0][index], this.m[1][index], this.m[2][index]); }
	
	public <T extends Tup3fW> T getRow(int index, T res)
	{
		if(res == null) throw new ArgumentNullException("res");
		res.set(this.m[index][0], this.m[index][1], this.m[index][2]);
		return res;
	}
	
	public <T extends Tup3fW> T getColumn(int index, T res)
	{
		if(res == null) throw new ArgumentNullException("res");
		res.set(this.m[0][index], this.m[1][index], this.m[2][index]);
		return res;
	}

	public float determinant()
	{
		assert(m != null);
		return (float) MatUtils.det3x3( this.m[0][0], this.m[0][1], this.m[0][2],
								this.m[1][0], this.m[1][1], this.m[1][2],
								this.m[2][0], this.m[2][1], this.m[2][2]);
	}
	
	public static Mat3 mul(Mat3 l, Mat3 r, Mat3 res)
	{
		if(l == null) throw new ArgumentNullException("l");
		if(r == null) throw new ArgumentNullException("r");
		
		if(res == null) res = new Mat3();
		
		float[][] m_ = new float[ROWS][COLUMNS];
		
		for(int row = 0; row < ROWS; row++)
		{
			m_[row][0] = l.m[row][0] * r.m[0][0] + l.m[row][1] * r.m[1][0] + l.m[row][2] * r.m[2][0];
			m_[row][1] = l.m[row][0] * r.m[0][1] + l.m[row][1] * r.m[1][1] + l.m[row][2] * r.m[2][1];
			m_[row][2] = l.m[row][0] * r.m[0][2] + l.m[row][1] * r.m[1][2] + l.m[row][2] * r.m[2][2];
		}
		
		for(int row = 0; row < ROWS; row++)
			for(int c = 0; c < COLUMNS; c++)
				res.m[row][c] = m_[row][c];
		
		return res;
	}
	
	public Mat3 mul(Mat3 r)
	{
		if(r == null) throw new ArgumentNullException("r");
		return Mat3.mul(this, r, this);
	}
	
	public Mat3 mul(Mat3 r, Mat3 res)
	{
		if(r == null) throw new ArgumentNullException("r");
		return Mat3.mul(this, r, res);
	}
	
	public Mat3 transpose()
	{
		Tup3f r0 = getRow(0, Tup3fPool.get());
		Tup3f r1 = getRow(1, Tup3fPool.get());
		Tup3f r2 = getRow(2, Tup3fPool.get());
		
		setColumn(0, r0).setColumn(1, r1).setColumn(2, r2);
		
		Tup3fPool.store(r0, r1, r2);
		
		return this;
	}
	
	public static <T extends Tup3fW> T transform(Mat3 l, Tup3fR r, T res)
	{
		if(l == null) throw new ArgumentNullException("l");
		if(r == null) throw new ArgumentNullException("r");
		if(res == null) throw new ArgumentNullException("res");

		float x_ = l.m[0][0] * r.getX() + l.m[0][1] * r.getY() + l.m[0][2] * r.getZ();
		float y_ = l.m[1][0] * r.getX() + l.m[1][1] * r.getY() + l.m[1][2] * r.getZ();
		float z_ = l.m[2][0] * r.getX() + l.m[2][1] * r.getY() + l.m[2][2] * r.getZ();

		res.set(x_, y_, z_);

		return res;
	}
	
	public <T extends Tup3fR & Tup3fW> T transform(T r)
	{
		if(r == null) throw new ArgumentNullException("r");

		return Mat3.transform(this, r, r);
	}
	
	public <T extends Tup3fW> T transform(Tup3fR r, T res)
	{
		if(r == null) throw new ArgumentNullException("r");
		
		return Mat3.transform(this, r, res);
	}
	
	public static <T extends Tup3fR & Tup3fW> T transform(Mat3 l, T r)
	{
		if(l == null) throw new ArgumentNullException("l");
		if(r == null) throw new ArgumentNullException("r");
		
		return Mat3.transform(l, r, r);
	}
	
	public static Mat3 identity() { return new Mat3().initIdentity(); }
	public static Mat3 zero() { return new Mat3().initZero(); }
	public static Mat3 scaling(Tup3fR t)
	{
		if(t == null) throw new ArgumentNullException("t");
		return scaling(t.getX(), t.getY(), t.getZ());
	}
	public static Mat3 scaling(float x, float y, float z) { return new Mat3().initScaling(x, y,z); }
	public static Mat3 rotation(Quat q) { if(q == null) throw new ArgumentNullException("q"); return new Mat3().initRotation(q); }
	
	public Mat3 scale(Tup3fR t) { if(t == null) throw new ArgumentNullException("t"); return Mat3.mul(Mat3.scaling(t), this, this); }
	public Mat3 scale(float x, float y, float z) { return Mat3.mul(Mat3.scaling(x, y, z), this, this); }
	public Mat3 rotate(Quat q) { if(q == null) throw new ArgumentNullException("q"); return Mat3.mul(Mat3.rotation(q), this, this); }

	public boolean isZeroMatrix()
	{
		return  this.m[0][0] == 0 && this.m[0][1] == 0 && this.m[0][2] == 0 &&
				this.m[1][0] == 0 && this.m[1][1] == 0 && this.m[1][2] == 0 &&
				this.m[2][0] == 0 && this.m[2][1] == 0 && this.m[2][2] == 0;
	}
	
	public boolean isIdentityMatrix()
	{
		return  this.m[0][0] == 1 && this.m[0][1] == 0 && this.m[0][2] == 0 &&
				this.m[1][0] == 0 && this.m[1][1] == 1 && this.m[1][2] == 0 &&
				this.m[2][0] == 0 && this.m[2][1] == 0 && this.m[2][2] == 1;
	}
	
	public String toString()
	{
		return 	"mat3f(" + this.m[0][0] + ", " + this.m[0][1] + ", " + this.m[0][2] + "\n"
			  + "      " + this.m[1][0] + ", " + this.m[1][1] + ", " + this.m[1][2] + "\n"
			  + "      " + this.m[2][0] + ", " + this.m[2][1] + ", " + this.m[2][2] + ")";
	}
	
	public Mat3 clone()
	{
		return new Mat3(this);
	}
}
