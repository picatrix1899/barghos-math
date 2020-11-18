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

import org.barghos.core.Barghos;
import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.tuple2.api.Tup2fR;
import org.barghos.core.tuple2.api.Tup2fW;
import org.barghos.core.tuple3.Tup3f;
import org.barghos.core.tuple3.api.Tup3fR;
import org.barghos.core.tuple3.api.Tup3fW;
import org.barghos.core.tuple4.Tup4f;
import org.barghos.core.tuple4.api.Tup4fR;
import org.barghos.core.tuple4.api.Tup4fW;
import org.barghos.core.tuple4.pool.Tup4fPool;
import org.barghos.math.BarghosMath;
import org.barghos.math.Maths;
import org.barghos.math.vector.quat.Quatf;
import org.barghos.math.vector.vec3.Vec3f;
import org.barghos.math.vector.vec3.Vec3fPool;


public class Mat4 implements Mat4R
{	
	public static final Mat4 IDENTITY = Mat4.identity();
	
	public static final int ROWS = 4;
	public static final int COLUMNS = 4;
	
	public final float[][] m = new float[ROWS][COLUMNS];
	
	public Mat4() {  }
	public Mat4(Mat4 m)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(m == null) throw new ArgumentNullException("m");
		}
		
		set(m);
	}
	
	public Mat4 set(Mat3f m)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(m == null) throw new ArgumentNullException("m");
		}

		for(int r = 0; r < Mat3f.ROWS; r++)
			for(int c = 0; c < Mat3f.COLUMNS; c++)
				this.m[r][c] = m.m[r][c];

		this.m[0][3] = 0;
		this.m[1][3] = 0;
		this.m[2][3] = 0;
		
		setRow(3, 0, 0, 0, 1);
		
		return this;
	}
	
	public Mat4 set(Mat4 m)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(m == null) throw new ArgumentNullException("m");
		}
		
		for(int r = 0; r < ROWS; r++)
			for(int c = 0; c < COLUMNS; c++)
				this.m[r][c] = m.m[r][c];

		return this;
	}
	
	public Mat4 initIdentity()
	{
		setRow(0, 1.0f, 0.0f, 0.0f, 0.0f);
		setRow(1, 0.0f, 1.0f, 0.0f, 0.0f);
		setRow(2, 0.0f, 0.0f, 1.0f, 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		return this;
	}

	public Mat4 initZero()
	{
		setRow(0, 0.0f, 0.0f, 0.0f, 0.0f);
		setRow(1, 0.0f, 0.0f, 0.0f, 0.0f);
		setRow(2, 0.0f, 0.0f, 0.0f, 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 0.0f);
		return this;
	}
	
	public Mat4 initScaling(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return initScaling(t.getX(), t.getY(), t.getZ());
	}
	
	public Mat4 initScaling(float x, float y, float z)
	{
		setRow(0, x,	0.0f,	0.0f, 	0.0f);
		setRow(1, 0.0f,	y,		0.0f, 	0.0f);
		setRow(2, 0.0f,	0.0f,	z, 		0.0f);
		setRow(3, 0.0f,	0.0f,	0.0f, 	1.0f);
		return this;
	}
	
	public Mat4 initTranslation(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return initTranslation(t.getX(), t.getY(), t.getZ());
	}
	
	public Mat4 initTranslation(float x, float y, float z)
	{
		setRow(0, 1.0f, 0.0f, 0.0f, x	);
		setRow(1, 0.0f, 1.0f, 0.0f, y	);
		setRow(2, 0.0f, 0.0f, 1.0f, z	);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat4 initRotation(Quatf q)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(q == null) throw new ArgumentNullException("q");
		}
		
		this.m[0][0] = 1.0f - 2.0f	*	(q.getY() * q.getY() + q.getZ() * q.getZ());
		this.m[0][1] = 2.0f			*	(q.getX() * q.getY() - q.getW() * q.getZ());
		this.m[0][2] = 2.0f			*	(q.getX() * q.getZ() + q.getW() * q.getY());
		this.m[0][3] = 0.0f;
		
		this.m[1][0] = 2.0f			*	(q.getX() * q.getY() + q.getW() * q.getZ());
		this.m[1][1] = 1.0f - 2.0f	*	(q.getX() * q.getX() + q.getZ() * q.getZ());
		this.m[1][2] = 2.0f			*	(q.getY() * q.getZ() - q.getW() * q.getX());
		this.m[1][3] = 0.0f;
		
		this.m[2][0] = 2.0f			*	(q.getX() * q.getZ() - q.getW() * q.getY());
		this.m[2][1] = 2.0f			*	(q.getY() * q.getZ() + q.getW() * q.getX());
		this.m[2][2] = 1.0f - 2.0f	*	(q.getX() * q.getX() + q.getY() * q.getY());
		this.m[2][3] = 0.0f;
		
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);

		return this;
	}
	
	public Mat4 initRotation(Tup3fR forward, Tup3fR left, Tup3fR up)
	{
		setRow(0, left, 0.0f);
		setRow(1, up, 0.0f);
		setRow(2, forward, 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat4 initPerspective(Tup2fR t, float fov, float near, float far)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		
		return initPerspective(t.getX(), t.getY(), fov, near, far);
	}
	
	public Mat4 initPerspective(float width, float height, float fovY, float near, float far)
	{
		float y_scale = 1.0f / (float)Math.tan(fovY * 0.5 * Maths.DEG_TO_RAD);
		float x_scale = y_scale / (width / height);

		setRow(0, x_scale,	0f,			0f,								0f								);
		setRow(1, 0f,		y_scale,	0f,								0f								);
		setRow(2, 0f,		0f,			-(far + near) / (far - near),	-2f * near * far / (far - near)	);
		setRow(3, 0f,		0f,			-1f,							0f								);
		
		return this;
	}
	
	public Mat4 initPerspective(float fovX, float fovY, float near, float far)
	{
		float y_scale = 1.0f / (float)Math.tan(fovY * 0.5 * Maths.DEG_TO_RAD);
		float x_scale = 1.0f / (float)Math.tan(fovX * 0.5 * Maths.DEG_TO_RAD);

		setRow(0, x_scale,	0f,			0f,								0f								);
		setRow(1, 0f,		y_scale,	0,								0f								);
		setRow(2, 0f,		0f,			-(far + near) / (far - near),	-2f * near * far / (far - near)	);
		setRow(3, 0f,		0f,			-1f,							0f								);
		
		return this;
	}
	
	public Mat4 initPerspective(float left, float right, float bottom, float top, float near, float far)
	{
		setRow(0, 2f * near / (right - left),		0f,									0f,									0f	);
		setRow(1, 0f,								2f * near / (top - bottom),			0f,									0f	);
		setRow(2, (right + left) / (right - left),	(top + bottom) / (top - bottom),	-(far + near) / (far - near),		-1f	);
		setRow(3, 0f,								0f,									-2f * far * near / (far - near),	0f	);
		
		return this;
	}
	
	public Mat4 initOrtho(Tup2fR t, float near, float far)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return initOrtho(t.getX(), t.getY(), near, far);
	}
	
	public Mat4 initOrtho(float width, float height, float length)
	{
		setRow(0, 2.0f / width,	0f,				0f,						0f);
		setRow(1, 0f,			2.0f / height,	0f,						0f);
		setRow(2, 0f,			0f, 			-2.0f / length,			0f);
		setRow(3, 0f,			0f,				0f,						1f);
		
		return this;
	}
	
	public Mat4 initOrtho2(float width, float height, float fovY, float near, float far)
	{
		float y_scale = 1.0f / (float)Math.tan(fovY * 0.5 * Maths.DEG_TO_RAD);
		float x_scale = y_scale / (width / height);
		
		setRow(0, 2.0f / x_scale,	0f,				0f,						0f);
		setRow(1, 0f,			2.0f / y_scale,	0f,						0f);
		setRow(2, 0f,			0f, 			-2.0f / (far - near),	0f);
		setRow(3, 0f,			0f,				0f,						1f);
		
		return this;
	}
	
	public Mat4 initOrtho(float width, float height, float near, float far)
	{
		setRow(0, 2.0f / width,	0f,				0f,						0f);
		setRow(1, 0f,			2.0f / height,	0f,						0f);
		setRow(2, 0f,			0f, 			-2.0f / (far - near),	0f);
		setRow(3, 0f,			0f,				0f,						1f);
		
		return this;
	}
	
	public Mat4 initOrtho(float left, float right, float bottom, float top, float near, float far)
	{
		setRow(0, 2.0f / (right - left),				0f,									0f,								0f);
		setRow(1, 0f,									2.0f / (top - bottom),				0f,								0f);
		setRow(2, 0f,									0f, 								-2.0f / (far - near),			0f);
		setRow(3, 0f,		0f,	0f, 	1f);
		 
		return this;
	}
	
	public Mat4 initOrthoWithCorrection(float left, float right, float bottom, float top, float near, float far)
	{
		setRow(0, 2.0f / (right - left),				0f,									0f,								-(right + left) / (right - left));
		setRow(1, 0f,									2.0f / (top - bottom),				0f,								-(top + bottom) / (top - bottom));
		setRow(2, 0f,									0f, 								-2.0f / (far - near),			-(far + near) / (far - near));
		setRow(3, 0f,		0f,	0f, 	1f);
		 
		return this;
	}
	
	public Mat4 initModelMatrix(Tup3fR pos, Quatf rot, Tup3fR scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(rot == null) throw new ArgumentNullException("rot");
		}
		
		if(scale == null) scale = new Tup3f(1.0f);
		
		initIdentity();
		
		rotate(rot);
		translate(pos);
		scale(scale);

		return this;
	}
	
	public Mat4 initViewMatrix(Tup3fR pos, Quatf rot)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(rot == null) throw new ArgumentNullException("rot");
		}
		
		initIdentity();
		
		translate(-pos.getX(), -pos.getY(), -pos.getZ());
		rotate(rot.conjugate(null));
		
		return this;
	}
	
	public Mat4 initLookAt(Tup3fR pos, Tup3fR target, Tup3fR worldUp)
	{
		Vec3f vpos = Vec3fPool.get(pos);
		Vec3f vtarget = Vec3fPool.get(target);
		Vec3f vworldUp = Vec3fPool.get(worldUp);
		
		Vec3f zaxis = vpos.sub(vtarget, Vec3fPool.get()).normal();
		Vec3f xaxis = vworldUp.normal().cross(zaxis, Vec3fPool.get()).normal();
		Vec3f yaxis = zaxis.cross(xaxis, Vec3fPool.get());
	
		initIdentity();
		translate(vpos.invert());
		rotate(zaxis, xaxis, yaxis);
		
		Vec3fPool.store(vpos, vtarget, vworldUp, zaxis, xaxis, yaxis);
		
		return this;
	}
	
	public Mat4 setRow(int index, Tup2fR t, float z, float w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setRow(index, t.getX(), t.getY(), z, w);
	}
	
	public Mat4 setRow(int index, float x, Tup2fR t, float w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setRow(index, x, t.getX(), t.getY(), w);
	}
	
	public Mat4 setRow(int index, float x, float y, Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setRow(index, x, y, t.getX(), t.getY());
	}
	
	public Mat4 setRow(int index, Tup3fR t, float w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setRow(index, t.getX(), t.getY(), t.getZ(), w);
	}
	
	public Mat4 setRow(int index, float x, Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setRow(index, x, t.getX(), t.getY(), t.getZ());
	}
	
	public Mat4 setRow(int index, Tup4fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setRow(index, t.getX(), t.getY(), t.getZ(), t.getW());
	}
	
	public Mat4 setRow(int index, float x, float y, float z, float w)
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
	
	
	public Mat4 setColumn(int index, Tup2fR t, float z, float w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setColumn(index, t.getX(), t.getY(), z, w);
	}
	
	public Mat4 setColumn(int index, float x, Tup2fR t, float w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setColumn(index, x, t.getX(), t.getY(), w);
	}
	
	public Mat4 setColumn(int index, float x, float y, Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setColumn(index, x, y, t.getX(), t.getY());
	}
	
	public Mat4 setColumn(int index, Tup3fR t, float w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setColumn(index, t.getX(), t.getY(), t.getZ(), w);
	}
	
	public Mat4 setColumn(int index, float x, Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setColumn(index, x, t.getX(), t.getY(), t.getZ());
	}
	
	public Mat4 setColumn(int index, Tup4fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setColumn(index, t.getX(), t.getY(), t.getZ(), t.getW());
	}
	
	public Mat4 setColumn(int index, float x, float y, float z, float w)
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

	public Tup4f getRow(int index)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
		}
		
		return new Tup4f(this.m[index][0], this.m[index][1], this.m[index][2], this.m[index][3]);
	}
	public Tup4f getColumn(int index)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
		}
		
		return new Tup4f(this.m[0][index], this.m[1][index], this.m[2][index], this.m[3][index]);
	}
	
	public float getCell(int row, int column)
	{
		return this.m[row][column];
	}
	
	public <T extends Tup4fW> T getRow(int index, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
		}
		
		res.set(this.m[index][0], this.m[index][1], this.m[index][2], this.m[index][3]);
		return res;
	}
	
	public <T extends Tup4fW> T getColumn(int index, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
		}
		
		res.set(this.m[0][index], this.m[1][index], this.m[2][index], this.m[3][index]);
		return res;
	}

	public float determinant()
	{
		return (float) MatUtils.det4x4(	this.m[0][0], this.m[0][1], this.m[0][2], this.m[0][3],
								this.m[1][0], this.m[1][1], this.m[1][2], this.m[1][3],
								this.m[2][0], this.m[2][1], this.m[2][2], this.m[2][3],
								this.m[3][0], this.m[3][1], this.m[3][2], this.m[3][3]);
	}
	
	public Mat4 transpose()
	{
		Tup4f r0 = Tup4fPool.get();
		Tup4f r1 = Tup4fPool.get();
		Tup4f r2 = Tup4fPool.get();
		Tup4f r3 = Tup4fPool.get();
		
		getRow(0, r0);
		getRow(1, r1);
		getRow(2, r2);
		getRow(3, r3);
		
		setColumn(0, r0).setColumn(1, r1).setColumn(2, r2).setColumn(3, r3);
		
		Tup4fPool.store(r0, r1, r2, r3);
		
		return this;
	}
	
	public static Mat4 mul(Mat4 l, Mat4 r, Mat4 res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(l == null) throw new ArgumentNullException("l");
			if(r == null) throw new ArgumentNullException("r");
		}
		
		if(res == null) res = new Mat4();
		
		float[][] m_ = new float[ROWS][COLUMNS];
		
		for(int row = 0; row < ROWS; row++)
		{
			m_[row][0] = l.m[row][0] * r.m[0][0] + l.m[row][1] * r.m[1][0] + l.m[row][2] * r.m[2][0] + l.m[row][3] * r.m[3][0];
			m_[row][1] = l.m[row][0] * r.m[0][1] + l.m[row][1] * r.m[1][1] + l.m[row][2] * r.m[2][1] + l.m[row][3] * r.m[3][1];
			m_[row][2] = l.m[row][0] * r.m[0][2] + l.m[row][1] * r.m[1][2] + l.m[row][2] * r.m[2][2] + l.m[row][3] * r.m[3][2];
			m_[row][3] = l.m[row][0] * r.m[0][3] + l.m[row][1] * r.m[1][3] + l.m[row][2] * r.m[2][3] + l.m[row][3] * r.m[3][3];
		}
		
		for(int row = 0; row < ROWS; row++)
			for(int c = 0; c < COLUMNS; c++)
				res.m[row][c] = m_[row][c];
		
		return res;
	}
	
	public Mat4 mul(Mat4 r)
	{
		if(Barghos.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
		}
		
		return Mat4.mul(this, r, this);
	}
	
	public Mat4 mul(Mat4 r, Mat4 res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
		}
		
		return Mat4.mul(this, r, res);
	}
	
	public static <T extends Tup4fW> T transform(Mat4 l, Tup4fR r, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(l == null) throw new ArgumentNullException("l");
			if(r == null) throw new ArgumentNullException("r");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		float x_ = l.m[0][0] * r.getX() + l.m[0][1] * r.getY() + l.m[0][2] * r.getZ() + l.m[0][3] * r.getW();
		float y_ = l.m[1][0] * r.getX() + l.m[1][1] * r.getY() + l.m[1][2] * r.getZ() + l.m[1][3] * r.getW();
		float z_ = l.m[2][0] * r.getX() + l.m[2][1] * r.getY() + l.m[2][2] * r.getZ() + l.m[2][3] * r.getW();
		float w_ = l.m[3][0] * r.getX() + l.m[3][1] * r.getY() + l.m[3][2] * r.getZ() + l.m[3][3] * r.getW();
		
		res.set(x_, y_, z_, w_);

		return res;
	}
	
	public <T extends Tup4fR & Tup4fW> T transform(T r)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
		}

		return Mat4.transform(this, r, r);
	}
	
	public <T extends Tup4fW> T transform(Tup4fR r, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
		}
		
		return Mat4.transform(this, r, res);
	}
	
	public static <T extends Tup4fR & Tup4fW> T transform(Mat4 l, T r)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(l == null) throw new ArgumentNullException("l");
			if(r == null) throw new ArgumentNullException("r");
		}
		
		return Mat4.transform(l, r, r);
	}
	
	public static <T extends Tup3fW> T transform(Mat4 l, Tup3fR r, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(l == null) throw new ArgumentNullException("l");
			if(r == null) throw new ArgumentNullException("r");
			if(res == null) throw new ArgumentNullException("res");
		}

		float x_ = l.m[0][0] * r.getX() + l.m[0][1] * r.getY() + l.m[0][2] * r.getZ() + l.m[0][3] * 1.0f;
		float y_ = l.m[1][0] * r.getX() + l.m[1][1] * r.getY() + l.m[1][2] * r.getZ() + l.m[1][3] * 1.0f;
		float z_ = l.m[2][0] * r.getX() + l.m[2][1] * r.getY() + l.m[2][2] * r.getZ() + l.m[2][3] * 1.0f;

		res.set(x_, y_, z_);

		return res;
	}
	
	public <T extends Tup3fR & Tup3fW> T transform(T r)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
		}
		
		return Mat4.transform(this, r, r);
	}
	
	public <T extends Tup3fW> T transform(Tup3fR r, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
		}
		
		return Mat4.transform(this, r, res);
	}
	
	public static <T extends Tup3fR & Tup3fW> T transform(Mat4 l, T r)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(l == null) throw new ArgumentNullException("l");
			if(r == null) throw new ArgumentNullException("r");
		}
		
		return Mat4.transform(l, r, r);
	}
	
	public static <T extends Tup2fW> T transform(Mat4 l, Tup2fR r, T res)
	{
		float x_ = l.m[0][0] * r.getX() + l.m[0][1] * r.getY() + l.m[0][2] * 1.0f + l.m[0][3] * 1.0f;
		float y_ = l.m[1][0] * r.getX() + l.m[1][1] * r.getY() + l.m[1][2] * 1.0f + l.m[1][3] * 1.0f;

		res.set(x_, y_);

		return res;
	}

	public <T extends Tup2fW> T transform(Tup2fR r, T res)
	{
		return Mat4.transform(this, r, res);
	}

	public static Mat4 identity()
	{
		return new Mat4().initIdentity();
	}
	
	public static Mat4 zero()
	{
		return new Mat4().initZero();
	}
	
	public static Mat4 scaling(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return scaling(t.getX(), t.getY(), t.getZ());
	}
	public static Mat4 scaling(float x, float y, float z)
	{
		return new Mat4().initScaling(x, y,z);
	}
	
	public static Mat4 translation(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return translation(t.getX(),t.getY(), t.getZ());
	}
	public static Mat4 translation(float x, float y, float z)
	{
		return new Mat4().initTranslation(x, y, z);
	}
	
	public static Mat4 perspective(Tup2fR t, float fovY, float near, float far)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return perspective(t.getX(), t.getY(), fovY, near, far);
	}
	public static Mat4 perspective(float width, float height, float fov, float near, float far)
	{
		return new Mat4().initPerspective(width, height, fov, near, far);
	}
	
	public static Mat4 perspective(float fovX, float fovY, float near, float far)
	{
		return new Mat4().initPerspective(fovX, fovY, near, far);
	}
	
	public static Mat4 perspective(float left, float right, float bottom, float top, float near, float far)
	{
		return new Mat4().initPerspective(left, right, bottom, top, near, far);
	
	}
	public static Mat4 ortho(Tup2fR t, float near, float far)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return ortho(t.getX(), t.getY(), near, far);
	}
	
	public static Mat4 ortho(float width, float height, float near, float far)
	{
		return new Mat4().initOrtho(width, height, near, far);
	}
	
	public static Mat4 ortho(float left, float right, float bottom, float top, float near, float far)
	{
		return new Mat4().initOrtho(left, right, bottom, top, near, far);
	}
	
	public static Mat4 rotation(Quatf q)
	{
		return new Mat4().initRotation(q);
	}
	
	public static Mat4 rotation(Tup3fR forward, Tup3fR left, Tup3fR up)
	{
		return new Mat4().initRotation(forward, left, up);
	}
	
	public static Mat4 modelMatrix(Tup3fR pos, Quatf rot, Tup3fR scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
		}
		
		return new Mat4().initModelMatrix(pos, rot, scale);
	}
	
	public static Mat4 viewMatrix(Tup3fR pos, Quatf rot)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(rot == null) throw new ArgumentNullException("rot");
		}
		
		return new Mat4().initViewMatrix(pos, rot);
	}
	
	public static Mat4 lookAt(Tup3fR pos, Tup3fR target, Tup3fR up)
	{
		return new Mat4().initLookAt(pos, target, up);
	} 
	
	public Mat4 scale(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return Mat4.mul(Mat4.scaling(t), this, this);
	}
	
	public Mat4 scale(float x, float y, float z)
	{
		return Mat4.mul(Mat4.scaling(x, y, z), this, this);
	}
	public Mat4 translate(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return Mat4.mul(Mat4.translation(t), this, this);
	}
	public Mat4 translate(float x, float y, float z)
	{
		return Mat4.mul(Mat4.translation(x, y, z), this, this);
	}
	public Mat4 rotate(Quatf q)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(q == null) throw new ArgumentNullException("q");
		}
		
		return Mat4.mul(Mat4.rotation(q), this, this);
	}
	public Mat4 rotate(Tup3fR forward, Tup3fR left, Tup3fR up)
	{
		return Mat4.mul(Mat4.rotation(forward, left, up), this, this);
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
	
	public boolean isZeroMatrix()
	{
		return  this.m[0][0] == 0 && this.m[0][1] == 0 && this.m[0][2] == 0 && this.m[0][3] == 0 &&
				this.m[1][0] == 0 && this.m[1][1] == 0 && this.m[1][2] == 0 && this.m[1][3] == 0 &&
				this.m[2][0] == 0 && this.m[2][1] == 0 && this.m[2][2] == 0 && this.m[2][3] == 0 &&
				this.m[3][0] == 0 && this.m[3][1] == 0 && this.m[3][2] == 0 && this.m[3][3] == 0;
	}
	
	public boolean isIdentityMatrix()
	{
		return  this.m[0][0] == 1 && this.m[0][1] == 0 && this.m[0][2] == 0 && this.m[0][3] == 0 &&
				this.m[1][0] == 0 && this.m[1][1] == 1 && this.m[1][2] == 0 && this.m[1][3] == 0 &&
				this.m[2][0] == 0 && this.m[2][1] == 0 && this.m[2][2] == 1 && this.m[2][3] == 0 &&
				this.m[3][0] == 0 && this.m[3][1] == 0 && this.m[3][2] == 0 && this.m[3][3] == 1;
	}

	public Mat4 invert()
	{
		double determinant = determinant();
		
		if(determinant != 0)
		{
			double determinant_inv = 1.0 / determinant;

			// first row
			double _m0x =  MatUtils.det3x3(m[1][1], m[1][2], m[1][3], m[2][1], m[2][2], m[2][3], m[3][1], m[3][2], m[3][3]);
			double _m0y = -MatUtils.det3x3(m[1][0], m[1][2], m[1][3], m[2][0], m[2][2], m[2][3], m[3][0], m[3][2], m[3][3]);
			double _m0z =  MatUtils.det3x3(m[1][0], m[1][1], m[1][3], m[2][0], m[2][1], m[2][3], m[3][0], m[3][1], m[3][3]);
			double _m0w = -MatUtils.det3x3(m[1][0], m[1][1], m[1][2], m[2][0], m[2][1], m[2][2], m[3][0], m[3][1], m[3][2]);
			// second row
			double _m1x = -MatUtils.det3x3(m[0][1], m[0][2], m[0][3], m[2][1], m[2][2], m[2][3], m[3][1], m[3][2], m[3][3]);
			double _m1y =  MatUtils.det3x3(m[0][0], m[0][2], m[0][3], m[2][0], m[2][2], m[2][3], m[3][0], m[3][2], m[3][3]);
			double _m1z = -MatUtils.det3x3(m[0][0], m[0][1], m[0][3], m[2][0], m[2][1], m[2][3], m[3][0], m[3][1], m[3][3]);
			double _m1w =  MatUtils.det3x3(m[0][0], m[0][1], m[0][2], m[2][0], m[2][1], m[2][2], m[3][0], m[3][1], m[3][2]);
			// third row
			double _m2x =  MatUtils.det3x3(m[0][1], m[0][2], m[0][3], m[1][1], m[1][2], m[1][3], m[3][1], m[3][2], m[3][3]);
			double _m2y = -MatUtils.det3x3(m[0][0], m[0][2], m[0][3], m[1][0], m[1][2], m[1][3], m[3][0], m[3][2], m[3][3]);
			double _m2z =  MatUtils.det3x3(m[0][0], m[0][1], m[0][3], m[1][0], m[1][1], m[1][3], m[3][0], m[3][1], m[3][3]);
			double _m2w = -MatUtils.det3x3(m[0][0], m[0][1], m[0][2], m[1][0], m[1][1], m[1][2], m[3][0], m[3][1], m[3][2]);
			// fourth row
			double _m3x = -MatUtils.det3x3(m[0][1], m[0][2], m[0][3], m[1][1], m[1][2], m[1][3], m[2][1], m[2][2], m[2][3]);
			double _m3y =  MatUtils.det3x3(m[0][0], m[0][2], m[0][3], m[1][0], m[1][2], m[1][3], m[2][0], m[2][2], m[2][3]);
			double _m3z = -MatUtils.det3x3(m[0][0], m[0][1], m[0][3], m[1][0], m[1][1], m[1][3], m[2][0], m[2][1], m[2][3]);
			double _m3w =  MatUtils.det3x3(m[0][0], m[0][1], m[0][2], m[1][0], m[1][1], m[1][2], m[2][0], m[2][1], m[2][2]);

			// transpose and divide by the determinant
			m[0][0] = (float)(_m0x * determinant_inv);
			m[1][1] = (float)(_m1y * determinant_inv);
			m[2][2] = (float)(_m2z * determinant_inv);
			m[3][3] = (float)(_m3w * determinant_inv);
			m[0][1] = (float)(_m1x * determinant_inv);
			m[1][0] = (float)(_m0y * determinant_inv);
			m[2][0] = (float)(_m0z * determinant_inv);
			m[0][2] = (float)(_m2x * determinant_inv);
			m[1][2] = (float)(_m2y * determinant_inv);
			m[2][1] = (float)(_m1z * determinant_inv);
			m[0][3] = (float)(_m3x * determinant_inv);
			m[3][0] = (float)(_m0w * determinant_inv);
			m[1][3] = (float)(_m3y * determinant_inv);
			m[3][1] = (float)(_m1w * determinant_inv);
			m[3][2] = (float)(_m2w * determinant_inv);
			m[2][3] = (float)(_m3z * determinant_inv);
			
			return this;
		}
		
		return null;
	}
	
	public Mat4 invertN()
	{
		double determinant = determinant();
		
		if(determinant != 0)
		{
			double determinant_inv = 1.0 / determinant;

			// first row
			double _m0x =  MatUtils.det3x3(m[1][1], m[1][2], m[1][3], m[2][1], m[2][2], m[2][3], m[3][1], m[3][2], m[3][3]);
			double _m0y = -MatUtils.det3x3(m[1][0], m[1][2], m[1][3], m[2][0], m[2][2], m[2][3], m[3][0], m[3][2], m[3][3]);
			double _m0z =  MatUtils.det3x3(m[1][0], m[1][1], m[1][3], m[2][0], m[2][1], m[2][3], m[3][0], m[3][1], m[3][3]);
			double _m0w = -MatUtils.det3x3(m[1][0], m[1][1], m[1][2], m[2][0], m[2][1], m[2][2], m[3][0], m[3][1], m[3][2]);
			// second row
			double _m1x = -MatUtils.det3x3(m[0][1], m[0][2], m[0][3], m[2][1], m[2][2], m[2][3], m[3][1], m[3][2], m[3][3]);
			double _m1y =  MatUtils.det3x3(m[0][0], m[0][2], m[0][3], m[2][0], m[2][2], m[2][3], m[3][0], m[3][2], m[3][3]);
			double _m1z = -MatUtils.det3x3(m[0][0], m[0][1], m[0][3], m[2][0], m[2][1], m[2][3], m[3][0], m[3][1], m[3][3]);
			double _m1w =  MatUtils.det3x3(m[0][0], m[0][1], m[0][2], m[2][0], m[2][1], m[2][2], m[3][0], m[3][1], m[3][2]);
			// third row
			double _m2x =  MatUtils.det3x3(m[0][1], m[0][2], m[0][3], m[1][1], m[1][2], m[1][3], m[3][1], m[3][2], m[3][3]);
			double _m2y = -MatUtils.det3x3(m[0][0], m[0][2], m[0][3], m[1][0], m[1][2], m[1][3], m[3][0], m[3][2], m[3][3]);
			double _m2z =  MatUtils.det3x3(m[0][0], m[0][1], m[0][3], m[1][0], m[1][1], m[1][3], m[3][0], m[3][1], m[3][3]);
			double _m2w = -MatUtils.det3x3(m[0][0], m[0][1], m[0][2], m[1][0], m[1][1], m[1][2], m[3][0], m[3][1], m[3][2]);
			// fourth row
			double _m3x = -MatUtils.det3x3(m[0][1], m[0][2], m[0][3], m[1][1], m[1][2], m[1][3], m[2][1], m[2][2], m[2][3]);
			double _m3y =  MatUtils.det3x3(m[0][0], m[0][2], m[0][3], m[1][0], m[1][2], m[1][3], m[2][0], m[2][2], m[2][3]);
			double _m3z = -MatUtils.det3x3(m[0][0], m[0][1], m[0][3], m[1][0], m[1][1], m[1][3], m[2][0], m[2][1], m[2][3]);
			double _m3w =  MatUtils.det3x3(m[0][0], m[0][1], m[0][2], m[1][0], m[1][1], m[1][2], m[2][0], m[2][1], m[2][2]);

			Mat4 out = new Mat4();
			
			// transpose and divide by the determinant
			out.m[0][0] = (float)(_m0x * determinant_inv);
			out.m[1][1] = (float)(_m1y * determinant_inv);
			out.m[2][2] = (float)(_m2z * determinant_inv);
			out.m[3][3] = (float)(_m3w * determinant_inv);
			out.m[0][1] = (float)(_m1x * determinant_inv);
			out.m[1][0] = (float)(_m0y * determinant_inv);
			out.m[2][0] = (float)(_m0z * determinant_inv);
			out.m[0][2] = (float)(_m2x * determinant_inv);
			out.m[1][2] = (float)(_m2y * determinant_inv);
			out.m[2][1] = (float)(_m1z * determinant_inv);
			out.m[0][3] = (float)(_m3x * determinant_inv);
			out.m[3][0] = (float)(_m0w * determinant_inv);
			out.m[1][3] = (float)(_m3y * determinant_inv);
			out.m[3][1] = (float)(_m1w * determinant_inv);
			out.m[3][2] = (float)(_m2w * determinant_inv);
			out.m[2][3] = (float)(_m3z * determinant_inv);
			
			return out;
		}
		
		return null;
	}
	
	public String toString()
	{
		return 	"mat4f(" + this.m[0][0] + ", " + this.m[0][1] + ", " + this.m[0][2] + ", " + this.m[0][3] + "\n"
			  + "      " + this.m[1][0] + ", " + this.m[1][1] + ", " + this.m[1][2] + ", " + this.m[1][3] + "\n"
			  + "      " + this.m[2][0] + ", " + this.m[2][1] + ", " + this.m[2][2] + ", " + this.m[2][3] + "\n"
			  + "      " + this.m[3][0] + ", " + this.m[3][1] + ", " + this.m[3][2] + ", " + this.m[3][3] + ")";
	}
	
	public Mat4 clone()
	{
		return new Mat4(this);
	}
}
