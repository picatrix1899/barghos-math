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
import org.barghos.core.tuple2.api.Tup2fW;
import org.barghos.core.tuple3.Tup3f;
import org.barghos.core.tuple3.api.Tup3fR;
import org.barghos.core.tuple3.api.Tup3fW;
import org.barghos.core.tuple3.pool.Tup3fPool;
import org.barghos.math.BarghosMath;
import org.barghos.math.vector.vec3.Vec3f;
import org.barghos.math.vector.vec3.Vec3fPool;

/**
 * This class represents a simplified version of the {@link Mat3f} class.
 * It can be used as a substitution for {@link Mat3f} but does not have any methods for
 * initializing the matrix.
 *   
 * @author picatrix1899
 */
public class SimpleMat3f implements Mat3fR, Mat3fW
{
	public static final int ROWS = 3;
	public static final int COLUMNS = 3;
	
	public final float[][] m = new float[3][3];
	
	public SimpleMat3f() { }
	
	public SimpleMat3f(Mat3fR m)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(m == null) throw new ArgumentNullException("m");
		}
		
		set(m);
	}
	
	public Tup3fR getRow(int index)
	{
		return new Tup3f(this.m[index][0], this.m[index][1], this.m[index][2]);
	}
	
	public <T extends Tup3fW> T getRow(int index, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		res.set(this.m[index][0], this.m[index][1], this.m[index][2]);
		
		return res;
	}
	
	public Tup3fR getColumn(int index)
	{
		return new Tup3f(this.m[0][index], this.m[1][index], this.m[2][index]);
	}
	
	public <T extends Tup3fW> T getColumn(int index, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		res.set(this.m[0][index], this.m[1][index], this.m[2][index]);
		
		return res;
	}
	
	public float getCell(int row, int column)
	{
		return this.m[row][column];
	}
	
	public Mat3f mul(Mat3fR r, Mat3f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		float[] m_ = new float[ROWS * COLUMNS];
		
		Vec3f rw = Vec3fPool.get();
		Vec3f cl = Vec3fPool.get();

		for(int row = 0; row < ROWS; row++)
		{
			getRow(row, rw);
			
			int index = row * COLUMNS;
			
			m_[index + 0] = rw.dot(r.getColumn(0, cl));
			m_[index + 1] = rw.dot(r.getColumn(1, cl));
			m_[index + 2] = rw.dot(r.getColumn(2, cl));
		}
		
		Vec3fPool.store(rw, cl);
		
		for(int row = 0; row < ROWS; row++)
		{
			int index = row * COLUMNS;
			
			res.m[row][0] = m_[index + 0];
			res.m[row][1] = m_[index + 1];
			res.m[row][2] = m_[index + 2];
		}

		return res;
	}
	
	public <T extends Tup3fR & Tup3fW> T transform(T r)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
		}
		
		return transform(r, r);
	}
	
	public <T extends Tup3fW> T transform(Tup3fR r, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
			if(res == null) throw new ArgumentNullException("res");
		}
	
		float x_ = m[0][0] * r.getX() + m[0][1] * r.getY() + m[0][2] * r.getZ();
		float y_ = m[1][0] * r.getX() + m[1][1] * r.getY() + m[1][2] * r.getZ();
		float z_ = m[2][0] * r.getX() + m[2][1] * r.getY() + m[2][2] * r.getZ();

		res.set(x_, y_, z_);

		return res;
	}

	public <T extends Tup2fR & Tup2fW> T transform(T r, boolean useLastColumn)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
		}
		
		return transform(r, useLastColumn, r);
	}
	
	public <T extends Tup2fW> T transform(Tup2fR r, boolean useLastColumn, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
			if(res == null) throw new ArgumentNullException("res");
		}
	
		float x_ = m[0][0] * r.getX() + m[0][1] * r.getY();
		float y_ = m[1][0] * r.getX() + m[1][1] * r.getY();

		if(useLastColumn)
		{
			x_ += m[0][2] * 1.0f;
			y_ += m[1][2] * 1.0f;
		}
		
		res.set(x_, y_);

		return res;
	}

	public SimpleMat3f set(Mat3fR m)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(m == null) throw new ArgumentNullException("m");
		}
		
		Tup3f r0 = m.getRow(0, Tup3fPool.get());
		Tup3f r1 = m.getRow(1, Tup3fPool.get());
		Tup3f r2 = m.getRow(2, Tup3fPool.get());

		setRow(0, r0);
		setRow(1, r1);
		setRow(2, r2);

		Tup3fPool.store(r0, r1, r2);
		
		return this;
	}
	
	/*
	 *
	 */
	public SimpleMat3f setRow(int index, Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setRow(index, t.getX(), t.getY(), t.getZ());
	}
	
	public SimpleMat3f setRow(int index, Tup2fR t, float z)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setRow(index, t.getX(), t.getY(), z);
	}
	
	public SimpleMat3f setRow(int index, float x, Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setRow(index, x, t.getX(), t.getY());
	}
	
	public SimpleMat3f setRow(int index, float x, float y, float z)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
		}
		
		this.m[index][0] = x;
		this.m[index][1] = y;
		this.m[index][2] = z;
		return this;
	}
	
	public SimpleMat3f setColumn(int index, Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setColumn(index, t.getX(), t.getY(), t.getZ());
	}
	
	public SimpleMat3f setColumn(int index, Tup2fR t, float z)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setColumn(index, t.getX(), t.getY(), z);
	}
	
	public SimpleMat3f setColumn(int index, float x, Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setColumn(index, x, t.getX(), t.getY());
	}
	
	public SimpleMat3f setColumn(int index, float x, float y, float z)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
		}
		
		this.m[0][index] = x;
		this.m[1][index] = y;
		this.m[2][index] = z;
		
		return this;
	}

	public SimpleMat3f setCell(int row, int column, float value)
	{
		this.m[row][column] = value;
		
		return this;
	}
	
		public boolean isZeroMatrix()
	{
		return  this.m[0][0] == 0.0f && this.m[0][1] == 0.0f && this.m[0][2] == 0.0f &&
				this.m[1][0] == 0.0f && this.m[1][1] == 0.0f && this.m[1][2] == 0.0f &&
				this.m[2][0] == 0.0f && this.m[2][1] == 0.0f && this.m[2][2] == 0.0f;
	}
	
	public boolean isIdentityMatrix()
	{
		return  this.m[0][0] == 1.0f && this.m[0][1] == 0.0f && this.m[0][2] == 0.0f &&
				this.m[1][0] == 0.0f && this.m[1][1] == 1.0f && this.m[1][2] == 0.0f &&
				this.m[2][0] == 0.0f && this.m[2][1] == 0.0f && this.m[2][2] == 1.0f;
	}
	
	public String toString()
	{
		return 	"mat3f(" + this.m[0][0] + ", " + this.m[0][1] + ", " + this.m[0][2] + "\n"
			  + "      " + this.m[1][0] + ", " + this.m[1][1] + ", " + this.m[1][2] + "\n"
			  + "      " + this.m[2][0] + ", " + this.m[2][1] + ", " + this.m[2][2] + ")";
	}
	
	public SimpleMat3f clone()
	{
		return new SimpleMat3f(this);
	}
}
