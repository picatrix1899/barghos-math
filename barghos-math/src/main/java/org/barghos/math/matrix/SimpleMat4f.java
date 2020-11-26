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

import java.nio.FloatBuffer;

import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.tuple2.api.Tup2fR;
import org.barghos.core.tuple3.api.Tup3fR;
import org.barghos.core.tuple3.api.Tup3fW;
import org.barghos.core.tuple4.Tup4f;
import org.barghos.core.tuple4.api.Tup4fR;
import org.barghos.core.tuple4.api.Tup4fW;
import org.barghos.core.tuple4.pool.Tup4fPool;
import org.barghos.math.BarghosMath;
import org.barghos.math.matrix.api.Mat3fR;
import org.barghos.math.matrix.api.Mat4fR;
import org.barghos.math.matrix.api.Mat4fW;
import org.barghos.math.utils.LinearSystem3;
import org.barghos.math.vec3.Vec3f;
import org.barghos.math.vec4.Vec4f;
import org.barghos.math.vec4.pool.Vec4fPool;

/**
 * @author picatrix1899
 *
 */
public class SimpleMat4f implements Mat4fR, Mat4fW
{
	public static final int ROWS = 4;
	public static final int COLUMNS = 4;
	
	public final float[][] m = new float[ROWS][COLUMNS];
	
	public SimpleMat4f() { }
	
	public SimpleMat4f(Mat4fR m)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(m == null) throw new ArgumentNullException("m");
		}
		
		set(m);
	}
	
	public Tup4fR getRow(int index)
	{
		return new Tup4f(this.m[index][0], this.m[index][1], this.m[index][2], this.m[index][3]);
	}
	
	public <T extends Tup4fW> T getRow(int index, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		res.set(this.m[index][0], this.m[index][1], this.m[index][2], this.m[index][3]);
		
		return res;
	}
	
	public Tup4fR getColumn(int index)
	{
		return new Tup4f(this.m[0][index], this.m[1][index], this.m[2][index], this.m[3][index]);
	}
	
	public <T extends Tup4fW> T getColumn(int index, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		res.set(this.m[0][index], this.m[1][index], this.m[2][index], this.m[3][index]);
		
		return res;
	}
	
	public float getCell(int row, int column)
	{
		return this.m[row][column];
	}
	
	public SimpleMat4f mul(Mat4fR left)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(left == null) throw new ArgumentNullException("left");
		}
		
		mul(left, this);
		
		return this;
	}
	
	/**
	 * Multiplies the current Matrix with another Matrix.
	 * Import is that the call for a multiplication AB is B.mul(A).
	 * This is to allow easier chaining of multiplications without nesting method calls.
	 */
	public <T extends Mat4fW> T mul(Mat4fR left, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(left == null) throw new ArgumentNullException("left");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		float[][] m_ = new float[ROWS][COLUMNS];
		
		Vec4f rw = Vec4fPool.get();
		Vec4f cl = Vec4fPool.get();

		for(int row = 0; row < ROWS; row++)
		{
			left.getRow(row, rw);

			m_[row][0] = rw.dot(getColumn(0, cl));
			m_[row][1] = rw.dot(getColumn(1, cl));
			m_[row][2] = rw.dot(getColumn(2, cl));
			m_[row][3] = rw.dot(getColumn(3, cl));
		}
		
		Vec4fPool.store(rw, cl);
		
		for(int row = 0; row < ROWS; row++)
		{
			res.setRow(row, m_[row][0], m_[row][1], m_[row][2], m_[row][3]);
		}

		return res;
	}

	public SimpleMat4f mulN(Mat4fR left)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(left == null) throw new ArgumentNullException("left");
		}
		
		return mul(left, new SimpleMat4f());
	}
	
	public <T extends Tup4fR & Tup4fW> T transform(T r)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
		}
		
		return transform(r, r);
	}
	
	public <T extends Tup4fW> T transform(Tup4fR r, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
			if(res == null) throw new ArgumentNullException("res");
		}
	
		float x_ = this.m[0][0] * r.getX() + this.m[0][1] * r.getY() + this.m[0][2] * r.getZ() + this.m[0][3] * r.getW();
		float y_ = this.m[1][0] * r.getX() + this.m[1][1] * r.getY() + this.m[1][2] * r.getZ() + this.m[1][3] * r.getW();
		float z_ = this.m[2][0] * r.getX() + this.m[2][1] * r.getY() + this.m[2][2] * r.getZ() + this.m[2][3] * r.getW();
		float w_ = this.m[3][0] * r.getX() + this.m[3][1] * r.getY() + this.m[3][2] * r.getZ() + this.m[3][3] * r.getW();
		
		res.set(x_, y_, z_, w_);

		return res;
	}

	public LinearSystem3 transform(LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return transform(system, system);
	}
	
	public LinearSystem3 transform(LinearSystem3 system, LinearSystem3 res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		Vec3f forward = transform(system.getForward(), false);
		Vec3f right = transform(system.getRight(), false);
		Vec3f up = transform(system.getUp(), false);
		
		return res.set(up, forward, right);
	}
	
	public <T extends Tup3fR & Tup3fW> T transform(T r, boolean useLastColumn)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
		}
		
		return transform(r, useLastColumn, r);
	}
	
	public <T extends Tup3fW> T transform(Tup3fR r, boolean useLastColumn, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
			if(res == null) throw new ArgumentNullException("res");
		}
	
		float x_ = this.m[0][0] * r.getX() + this.m[0][1] * r.getY() + this.m[0][2] * r.getZ();
		float y_ = this.m[1][0] * r.getX() + this.m[1][1] * r.getY() + this.m[1][2] * r.getZ();
		float z_ = this.m[2][0] * r.getX() + this.m[2][1] * r.getY() + this.m[2][2] * r.getZ();
		
		if(useLastColumn)
		{
			x_ += this.m[0][3] * 1.0f;
			y_ += this.m[1][3] * 1.0f;
			z_ += this.m[2][3] * 1.0f;
		}
		
		res.set(x_, y_, z_);

		return res;
	}

	public SimpleMat4f set(Mat4fR m)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(m == null) throw new ArgumentNullException("m");
		}
		
		Tup4f r0 = m.getRow(0, Tup4fPool.get());
		Tup4f r1 = m.getRow(1, Tup4fPool.get());
		Tup4f r2 = m.getRow(2, Tup4fPool.get());
		Tup4f r3 = m.getRow(3, Tup4fPool.get());

		setRow(0, r0);
		setRow(1, r1);
		setRow(2, r2);
		setRow(3, r3);
		
		Tup4fPool.store(r0, r1, r2, r3);
		
		return this;
	}
	
	public SimpleMat4f set(Mat3fR m)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(m == null) throw new ArgumentNullException("m");
		}

		for(int r = 0; r < Mat3f.ROWS; r++)
			for(int c = 0; c < Mat3f.COLUMNS; c++)
				this.m[r][c] = m.getCell(r, c);

		this.m[0][3] = 0;
		this.m[1][3] = 0;
		this.m[2][3] = 0;
		
		setRow(3, 0, 0, 0, 1);
		
		return this;
	}
	
	public SimpleMat4f setRow(int index, Tup4fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setRow(index, t.getX(), t.getY(), t.getZ(), t.getW());
	}
	
	public SimpleMat4f setRow(int index, Tup3fR t, float w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setRow(index, t.getX(), t.getY(), t.getZ(), w);
	}
	
	public SimpleMat4f setRow(int index, float x, Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setRow(index, x, t.getX(), t.getY(), t.getZ());
	}
	
	public SimpleMat4f setRow(int index, Tup2fR t, float z, float w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setRow(index, t.getX(), t.getY(), z, w);
	}
	
	public SimpleMat4f setRow(int index, float x, Tup2fR t, float w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setRow(index, x, t.getX(), t.getY(), w);
	}
	
	public SimpleMat4f setRow(int index, float x, float y, Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setRow(index, x, y, t.getX(), t.getY());
	}
	
	public SimpleMat4f setRow(int index, float x, float y, float z, float w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
		}
		
		this.m[index][0] = x;
		this.m[index][1] = y;
		this.m[index][2] = z;
		this.m[index][3] = w;
		
		return this;
	}
	
	public SimpleMat4f setColumn(int index, Tup4fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setColumn(index, t.getX(), t.getY(), t.getZ(), t.getW());
	}
	
	public SimpleMat4f setColumn(int index, Tup3fR t, float w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setColumn(index, t.getX(), t.getY(), t.getZ(), w);
	}
	
	public SimpleMat4f setColumn(int index, float x, Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setColumn(index, x, t.getX(), t.getY(), t.getZ());
	}
	
	public SimpleMat4f setColumn(int index, Tup2fR t, float z, float w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setColumn(index, t.getX(), t.getY(), z, w);
	}
	
	public SimpleMat4f setColumn(int index, float x, Tup2fR t, float w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setColumn(index, x, t.getX(), t.getY(), w);
	}
	
	public SimpleMat4f setColumn(int index, float x, float y, Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setColumn(index, x, y, t.getX(), t.getY());
	}
	
	public SimpleMat4f setColumn(int index, float x, float y, float z, float w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
		}
		
		this.m[0][index] = x;
		this.m[1][index] = y;
		this.m[2][index] = z;
		this.m[3][index] = w;
		
		return this;
	}

	public SimpleMat4f setCell(int row, int column, float value)
	{
		this.m[row][column] = value;
		
		return this;
	}
	
	public SimpleMat4f transpose()
	{
		return transpose(this);
	}
	
	public <T extends Mat4fW> T transpose(T res)
	{
		Tup4f r0 = getRow(0, Tup4fPool.get());
		Tup4f r1 = getRow(1, Tup4fPool.get());
		Tup4f r2 = getRow(2, Tup4fPool.get());
		Tup4f r3 = getRow(3, Tup4fPool.get());
		
		res.setColumn(0, r0).setColumn(1, r1).setColumn(2, r2).setColumn(3, r3);
		
		Tup4fPool.store(r0, r1, r2, r3);
		
		return res;
	}
	
	public SimpleMat4f clean()
	{
		return clean(this);
	}
	
	public <T extends Mat4fW> T clean(T res)
	{
		float tr = BarghosMath.DEFAULT_ZERO_THRESHOLD_F;
		
		for(int r = 0; r < ROWS; r++)
			for(int c = 0; c < COLUMNS; c++)
			{
				if(Math.abs(this.m[r][c]) <= tr)
					res.setCell(r, c, 0.0f);
				else if(Math.abs(1.0f - this.m[r][c]) <= tr)
					res.setCell(r, c, 1.0f);
				else if(Math.abs(1.0f + this.m[r][c]) <= tr)
					res.setCell(r, c, -1.0f);
			}
		
		return res;
	}
	
	public boolean isZeroMatrix()
	{
		return isZeroMatrix(BarghosMath.DEFAULT_ZERO_THRESHOLD_F);
	}
	
	public boolean isZeroMatrix(float tr)
	{
		return  Math.abs(this.m[0][0]) <= tr && Math.abs(this.m[0][1]) <= tr && Math.abs(this.m[0][2]) <= tr && Math.abs(this.m[0][3]) <= tr &&
				Math.abs(this.m[1][0]) <= tr && Math.abs(this.m[1][1]) <= tr && Math.abs(this.m[1][2]) <= tr && Math.abs(this.m[1][3]) <= tr &&
				Math.abs(this.m[2][0]) <= tr && Math.abs(this.m[2][1]) <= tr && Math.abs(this.m[2][2]) <= tr && Math.abs(this.m[2][3]) <= tr &&
				Math.abs(this.m[3][0]) <= tr && Math.abs(this.m[3][1]) <= tr && Math.abs(this.m[3][2]) <= tr && Math.abs(this.m[3][3]) <= tr;
	}
	
	public boolean isIdentityMatrix()
	{
		return isIdentityMatrix(BarghosMath.DEFAULT_ZERO_THRESHOLD_F);
	}
	
	public boolean isIdentityMatrix(float tr)
	{	
		return  Math.abs(1.0f - this.m[0][0]) <= tr	&& Math.abs(this.m[0][1]) <= tr			&& Math.abs(this.m[0][2]) <= tr			&& Math.abs(this.m[0][3]) <= tr &&
				Math.abs(this.m[1][0]) <= tr		&& Math.abs(1.0f - this.m[1][1]) <= tr	&& Math.abs(this.m[1][2]) <= tr			&& Math.abs(this.m[1][3]) <= tr &&
				Math.abs(this.m[2][0]) <= tr		&& Math.abs(this.m[2][1]) <= tr			&& Math.abs(1.0f - this.m[2][2]) <= tr	&& Math.abs(this.m[2][3]) <= tr &&
				Math.abs(this.m[3][0]) <= tr		&& Math.abs(this.m[3][1]) <= tr			&& Math.abs(this.m[3][2]) <= tr			&& Math.abs(1.0f - this.m[3][3]) <= tr;
	}
	
	public boolean isRotationMatrix()
	{
		return isRotationMatrix(BarghosMath.DEFAULT_ZERO_THRESHOLD_F);
	}
	
	public boolean isRotationMatrix(float tr)
	{
		Mat4f t = transpose(new Mat4f());

		return !isIdentityMatrix(tr) && mul(t, t).isIdentityMatrix(tr);
	}
	
	public FloatBuffer toBufferColumnMajor(FloatBuffer res)
	{
		for(int i = 0; i < COLUMNS; i++)
		{
			res.put(this.m[0][i]);
			res.put(this.m[1][i]);
			res.put(this.m[2][i]);
			res.put(this.m[3][i]);
		}
		
		res.flip();
		
		return res;
	}
	
	public FloatBuffer toBufferRowMajor(FloatBuffer res)
	{
		for(int i = 0; i < ROWS; i++)
		{
			res.put(this.m[i][0]);
			res.put(this.m[i][1]);
			res.put(this.m[i][2]);
			res.put(this.m[i][3]);
		}
		
		res.flip();
		
		return res;
	}
	
	public float[] toArrayColumnMajor()
	{
		float[] out = new float[ROWS * COLUMNS];

		for(int i = 0; i < COLUMNS; i++)
		{
			out[i * ROWS + 0] = this.m[0][i];
			out[i * ROWS + 1] = this.m[1][i];
			out[i * ROWS + 2] = this.m[2][i];
			out[i * ROWS + 3] = this.m[3][i];
		}

		return out;		
	}
	
	public float[] toArrayRowMajor()
	{
		float[] out = new float[ROWS * COLUMNS];
		
		for(int i = 0; i < ROWS; i++)
		{
			out[i * COLUMNS + 0] = this.m[i][0];
			out[i * COLUMNS + 1] = this.m[i][1];
			out[i * COLUMNS + 2] = this.m[i][2];
			out[i * COLUMNS + 3] = this.m[i][3];
		}

		return out;		
	}
	
	public String toString()
	{
		return 	"simpleMat4f(" + this.m[0][0] + ", " + this.m[0][1] + ", " + this.m[0][2] + this.m[0][2] + "\n"
			  + "            " + this.m[1][0] + ", " + this.m[1][1] + ", " + this.m[1][2] + this.m[1][2] + "\n"
			  + "            " + this.m[2][0] + ", " + this.m[2][1] + ", " + this.m[2][2] + this.m[2][2] + "\n"
			  + "            " + this.m[3][0] + ", " + this.m[3][1] + ", " + this.m[3][2] + this.m[3][2] + ")";
	}
	
	public SimpleMat4f clone()
	{
		return new SimpleMat4f(this);
	}
}
