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
import org.barghos.core.tuple2.api.Tup2dR;
import org.barghos.core.tuple2.api.Tup2dW;
import org.barghos.core.tuple3.Tup3d;
import org.barghos.core.tuple3.api.Tup3dR;
import org.barghos.core.tuple3.api.Tup3dW;
import org.barghos.core.tuple4.Tup4d;
import org.barghos.core.tuple4.api.Tup4dR;
import org.barghos.core.tuple4.api.Tup4dW;
import org.barghos.core.tuple4.pool.Tup4dPool;
import org.barghos.math.BarghosMath;
import org.barghos.math.Maths;
import org.barghos.math.vector.quat.Quat;
import org.barghos.math.vector.vec3.Vec3d;
import org.barghos.math.vector.vec3.Vec3dPool;


public class Mat4d
{	
	public static final Mat4d IDENTITY = Mat4d.identity();
	
	public static final int ROWS = 4;
	public static final int COLUMNS = 4;
	
	public final double[][] m = new double[ROWS][COLUMNS];
	
	public Mat4d() {  }
	public Mat4d(Mat4d m)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(m == null) throw new ArgumentNullException("m");
		}
		
		set(m);
	}
	
	public Mat4d set(Mat3 m)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(m == null) throw new ArgumentNullException("m");
		}

		for(int r = 0; r < Mat3.ROWS; r++)
			for(int c = 0; c < Mat3.COLUMNS; c++)
				this.m[r][c] = m.m[r][c];

		this.m[0][3] = 0;
		this.m[1][3] = 0;
		this.m[2][3] = 0;
		
		setRow(3, 0, 0, 0, 1);
		
		return this;
	}
	
	public Mat4d set(Mat4d m)
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
	
	public Mat4d initIdentity()
	{
		setRow(0, 1.0, 0.0, 0.0, 0.0);
		setRow(1, 0.0, 1.0, 0.0, 0.0);
		setRow(2, 0.0, 0.0, 1.0, 0.0);
		setRow(3, 0.0, 0.0, 0.0, 1.0);
		
		return this;
	}

	public Mat4d initZero()
	{
		setRow(0, 0.0, 0.0, 0.0, 0.0);
		setRow(1, 0.0, 0.0, 0.0, 0.0);
		setRow(2, 0.0, 0.0, 0.0, 0.0);
		setRow(3, 0.0, 0.0, 0.0, 0.0);
		return this;
	}
	
	public Mat4d initScaling(Tup3dR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return initScaling(t.getX(), t.getY(), t.getZ());
	}
	
	public Mat4d initScaling(double x, double y, double z)
	{
		setRow(0, x,	0.0,	0.0, 	0.0);
		setRow(1, 0.0,	y,		0.0, 	0.0);
		setRow(2, 0.0,	0.0,	z, 		0.0);
		setRow(3, 0.0,	0.0,	0.0, 	1.0);
		return this;
	}
	
	public Mat4d initTranslation(Tup3dR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return initTranslation(t.getX(), t.getY(), t.getZ());
	}
	
	public Mat4d initTranslation(double x, double y, double z)
	{
		setRow(0, 1.0, 0.0, 0.0, x	);
		setRow(1, 0.0, 1.0, 0.0, y	);
		setRow(2, 0.0, 0.0, 1.0, z	);
		setRow(3, 0.0, 0.0, 0.0, 1.0);
		
		return this;
	}
	
	public Mat4d initRotation(Quat q)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(q == null) throw new ArgumentNullException("q");
		}
		
		this.m[0][0] = (1.0 - 2.0	*	(q.getY() * q.getY() + q.getZ() * q.getZ()));
		this.m[0][1] = (2.0			*	(q.getX() * q.getY() - q.getW() * q.getZ()));
		this.m[0][2] = (2.0			*	(q.getX() * q.getZ() + q.getW() * q.getY()));
		this.m[0][3] = 0.0f;
		
		this.m[1][0] = (2.0			*	(q.getX() * q.getY() + q.getW() * q.getZ()));
		this.m[1][1] = (1.0 - 2.0	*	(q.getX() * q.getX() + q.getZ() * q.getZ()));
		this.m[1][2] = (2.0			*	(q.getY() * q.getZ() - q.getW() * q.getX()));
		this.m[1][3] = 0.0f;
		
		this.m[2][0] = (2.0			*	(q.getX() * q.getZ() - q.getW() * q.getY()));
		this.m[2][1] = (2.0			*	(q.getY() * q.getZ() + q.getW() * q.getX()));
		this.m[2][2] = (1.0 - 2.0	*	(q.getX() * q.getX() + q.getY() * q.getY()));
		this.m[2][3] = 0.0f;
		
		setRow(3, 0.0, 0.0, 0.0, 1.0);

		return this;
	}
	
	public Mat4d initRotation(Tup3dR forward, Tup3dR left, Tup3dR up)
	{
		setRow(0, left, 0.0);
		setRow(1, up, 0.0);
		setRow(2, forward, 0.0);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0);
		
		return this;
	}
	
	public Mat4d initPerspective(Tup2dR t, double fov, double near, double far)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return initPerspective(t.getX(), t.getY(), fov, near, far);
	}
	
	public Mat4d initPerspective(double width, double height, double fovY, double near, double far)
	{
		double y_scale = 1.0 / Math.tan(fovY * 0.5 * Maths.DEG_TO_RAD);
		double x_scale = y_scale / (width / height);

		setRow(0, x_scale,	0,			0,								0								);
		setRow(1, 0,		y_scale,	0,								0								);
		setRow(2, 0,		0,			-(far + near) / (far - near),	-2 * near * far / (far - near)	);
		setRow(3, 0,		0,			-1,								0								);
		
		return this;
	}
	
	public Mat4d initPerspective(double fovX, double fovY, double near, double far)
	{
		double y_scale = 1.0 / Math.tan(fovY * 0.5 * Maths.DEG_TO_RAD);
		double x_scale = 1.0 / Math.tan(fovX * 0.5 * Maths.DEG_TO_RAD);

		setRow(0, x_scale,	0,			0,								0								);
		setRow(1, 0,		y_scale,	0,								0								);
		setRow(2, 0,		0,			-(far + near) / (far - near),	-2 * near * far / (far - near)	);
		setRow(3, 0,		0,			-1,								0								);
		
		return this;
	}
	
	public Mat4d initPerspective(double left, double right, double bottom, double top, double near, double far)
	{
		setRow(0, 2 * near / (right - left),		0,									0,									0	);
		setRow(1, 0,								2 * near / (top - bottom),			0,									0	);
		setRow(2, (right + left) / (right - left),	(top + bottom) / (top - bottom),	-(far + near) / (far - near),		-1	);
		setRow(3, 0,								0,									-2 * far * near / (far - near),		0	);
		
		return this;
	}
	
	public Mat4d initOrtho(Tup2dR t, double near, double far)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return initOrtho(t.getX(), t.getY(), near, far);
	}
	
	public Mat4d initOrtho(double width, double height, double length)
	{
		setRow(0, 2.0f / width,	0,				0,						0);
		setRow(1, 0,			2.0 / height,	0,						0);
		setRow(2, 0,			0,	 			-2.0 / length,			0);
		setRow(3, 0,			0,				0,						1);
		
		return this;
	}
	
	public Mat4d initOrtho2(double width, double height, double fovY, double near, double far)
	{
		double y_scale = 1.0 / Math.tan(fovY * 0.5 * Maths.DEG_TO_RAD);
		double x_scale = y_scale / (width / height);
		
		setRow(0, 2.0 / x_scale,	0,				0,						0);
		setRow(1, 0,				2.0 / y_scale,	0,						0);
		setRow(2, 0,				0, 				-2.0 / (far - near),	0);
		setRow(3, 0,				0,				0,						1);
		
		return this;
	}
	
	public Mat4d initOrtho(double width, double height, double near, double far)
	{
		setRow(0, 2.0 / width,	0,				0,						0);
		setRow(1, 0,			2.0 / height,	0,						0);
		setRow(2, 0,			0, 			-2.0 / (far - near),		0);
		setRow(3, 0,			0,				0,						1);
		
		return this;
	}
	
	public Mat4d initOrtho(double left, double right, double bottom, double top, double near, double far)
	{
		setRow(0, 2.0f / (right - left),				0f,									0f,								0f);
		setRow(1, 0f,									2.0f / (top - bottom),				0f,								0f);
		setRow(2, 0f,									0f, 								-2.0f / (far - near),			0f);
		setRow(3, 0f,		0f,	0f, 	1f);
		 
		return this;
	}
	
	public Mat4d initOrthoWithCorrection(double left, double right, double bottom, double top, double near, double far)
	{
		setRow(0, 2.0f / (right - left),				0f,									0f,								-(right + left) / (right - left));
		setRow(1, 0f,									2.0f / (top - bottom),				0f,								-(top + bottom) / (top - bottom));
		setRow(2, 0f,									0f, 								-2.0f / (far - near),			-(far + near) / (far - near));
		setRow(3, 0f,		0f,	0f, 	1f);
		 
		return this;
	}
	
	public Mat4d initModelMatrix(Tup3dR pos, Quat rot, Tup3dR scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(rot == null) throw new ArgumentNullException("rot");
		}
		
		if(scale == null) scale = new Tup3d(1.0f);
		
		initIdentity();
		
		rotate(rot);
		translate(pos);
		scale(scale);

		return this;
	}
	
	public Mat4d initViewMatrix(Tup3dR pos, Quat rot)
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
	
	public Mat4d initLookAt(Tup3dR pos, Tup3dR target, Tup3dR worldUp)
	{
		Vec3d vpos = Vec3dPool.get(pos);
		Vec3d vtarget = Vec3dPool.get(target);
		Vec3d vworldUp = Vec3dPool.get(worldUp);
		
		Vec3d zaxis = vpos.sub(vtarget, Vec3dPool.get()).normal();
		Vec3d xaxis = vworldUp.normal().cross(zaxis, Vec3dPool.get()).normal();
		Vec3d yaxis = zaxis.cross(xaxis, Vec3dPool.get());
	
		initIdentity();
		translate(vpos.invert());
		rotate(zaxis, xaxis, yaxis);
		
		Vec3dPool.store(vpos, vtarget, vworldUp, zaxis, xaxis, yaxis);
		
		return this;
	}
	
	public Mat4d setRow(int index, Tup2dR t, double z, double w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setRow(index, t.getX(), t.getY(), z, w);
	}
	
	public Mat4d setRow(int index, double x, Tup2dR t, double w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setRow(index, x, t.getX(), t.getY(), w);
	}
	
	public Mat4d setRow(int index, double x, double y, Tup2dR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setRow(index, x, y, t.getX(), t.getY());
	}
	
	public Mat4d setRow(int index, Tup3dR t, double w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setRow(index, t.getX(), t.getY(), t.getZ(), w);
	}
	
	public Mat4d setRow(int index, double x, Tup3dR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setRow(index, x, t.getX(), t.getY(), t.getZ());
	}
	
	public Mat4d setRow(int index, Tup4dR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setRow(index, t.getX(), t.getY(), t.getZ(), t.getW());
	}
	
	public Mat4d setRow(int index, double x, double y, double z, double w)
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
	
	
	public Mat4d setColumn(int index, Tup2dR t, double z, double w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setColumn(index, t.getX(), t.getY(), z, w);
	}
	
	public Mat4d setColumn(int index, double x, Tup2dR t, double w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setColumn(index, x, t.getX(), t.getY(), w);
	}
	
	public Mat4d setColumn(int index, double x, double y, Tup2dR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setColumn(index, x, y, t.getX(), t.getY());
	}
	
	public Mat4d setColumn(int index, Tup3dR t, double w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setColumn(index, t.getX(), t.getY(), t.getZ(), w);
	}
	
	public Mat4d setColumn(int index, double x, Tup3dR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setColumn(index, x, t.getX(), t.getY(), t.getZ());
	}
	
	public Mat4d setColumn(int index, Tup4dR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return setColumn(index, t.getX(), t.getY(), t.getZ(), t.getW());
	}
	
	public Mat4d setColumn(int index, double x, double y, double z, double w)
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

	public Tup4d getRow(int index)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
		}
		
		return new Tup4d(this.m[index][0], this.m[index][1], this.m[index][2], this.m[index][3]);
	}
	public Tup4d getColumn(int index)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
		}
		
		return new Tup4d(this.m[0][index], this.m[1][index], this.m[2][index], this.m[3][index]);
	}
	
	public double getCell(int row, int column)
	{
		return this.m[row][column];
	}
	
	public <T extends Tup4dW> T getRow(int index, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
		}
		
		res.set(this.m[index][0], this.m[index][1], this.m[index][2], this.m[index][3]);
		return res;
	}
	
	public <T extends Tup4dW> T getColumn(int index, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
		}
		
		res.set((double)this.m[0][index], (double)this.m[1][index], (double)this.m[2][index], (double)this.m[3][index]);
		return res;
	}

	public double determinant()
	{
		return MatUtils.det4x4(	this.m[0][0], this.m[0][1], this.m[0][2], this.m[0][3],
								this.m[1][0], this.m[1][1], this.m[1][2], this.m[1][3],
								this.m[2][0], this.m[2][1], this.m[2][2], this.m[2][3],
								this.m[3][0], this.m[3][1], this.m[3][2], this.m[3][3]);
	}
	
	public Mat4d transpose()
	{
		Tup4d r0 = Tup4dPool.get();
		Tup4d r1 = Tup4dPool.get();
		Tup4d r2 = Tup4dPool.get();
		Tup4d r3 = Tup4dPool.get();
		
		getRow(0, r0);
		getRow(1, r1);
		getRow(2, r2);
		getRow(3, r3);
		
		setColumn(0, r0).setColumn(1, r1).setColumn(2, r2).setColumn(3, r3);
		
		Tup4dPool.store(r0, r1, r2, r3);
		
		return this;
	}
	
	public static Mat4d mul(Mat4d l, Mat4d r, Mat4d res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(l == null) throw new ArgumentNullException("l");
			if(r == null) throw new ArgumentNullException("r");
		}
		
		if(res == null) res = new Mat4d();
		
		double[][] m_ = new double[ROWS][COLUMNS];
		
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
	
	public Mat4d mul(Mat4d r)
	{
		if(Barghos.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
		}
		
		return Mat4d.mul(this, r, this);
	}
	
	public Mat4d mul(Mat4d r, Mat4d res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
		}
		
		return Mat4d.mul(this, r, res);
	}
	
	public static <T extends Tup4dW> T transform(Mat4d l, Tup4dR r, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(l == null) throw new ArgumentNullException("l");
			if(r == null) throw new ArgumentNullException("r");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		double x_ = l.m[0][0] * r.getX() + l.m[0][1] * r.getY() + l.m[0][2] * r.getZ() + l.m[0][3] * r.getW();
		double y_ = l.m[1][0] * r.getX() + l.m[1][1] * r.getY() + l.m[1][2] * r.getZ() + l.m[1][3] * r.getW();
		double z_ = l.m[2][0] * r.getX() + l.m[2][1] * r.getY() + l.m[2][2] * r.getZ() + l.m[2][3] * r.getW();
		double w_ = l.m[3][0] * r.getX() + l.m[3][1] * r.getY() + l.m[3][2] * r.getZ() + l.m[3][3] * r.getW();
		
		res.set(x_, y_, z_, w_);

		return res;
	}
	
	public <T extends Tup4dR & Tup4dW> T transform(T r)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
		}

		return Mat4d.transform(this, r, r);
	}
	
	public <T extends Tup4dW> T transform(Tup4dR r, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
		}
		
		return Mat4d.transform(this, r, res);
	}
	
	public static <T extends Tup4dR & Tup4dW> T transform(Mat4d l, T r)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(l == null) throw new ArgumentNullException("l");
			if(r == null) throw new ArgumentNullException("r");
		}
		
		return Mat4d.transform(l, r, r);
	}
	
	public static <T extends Tup3dW> T transform(Mat4d l, Tup3dR r, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(l == null) throw new ArgumentNullException("l");
			if(r == null) throw new ArgumentNullException("r");
			if(res == null) throw new ArgumentNullException("res");
		}

		double x_ = l.m[0][0] * r.getX() + l.m[0][1] * r.getY() + l.m[0][2] * r.getZ() + l.m[0][3] * 1.0f;
		double y_ = l.m[1][0] * r.getX() + l.m[1][1] * r.getY() + l.m[1][2] * r.getZ() + l.m[1][3] * 1.0f;
		double z_ = l.m[2][0] * r.getX() + l.m[2][1] * r.getY() + l.m[2][2] * r.getZ() + l.m[2][3] * 1.0f;

		res.set(x_, y_, z_);

		return res;
	}
	
	public <T extends Tup3dR & Tup3dW> T transform(T r)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
		}
		
		return Mat4d.transform(this, r, r);
	}
	
	public <T extends Tup3dW> T transform(Tup3dR r, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
		}
		
		return Mat4d.transform(this, r, res);
	}
	
	public static <T extends Tup3dR & Tup3dW> T transform(Mat4d l, T r)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(l == null) throw new ArgumentNullException("l");
			if(r == null) throw new ArgumentNullException("r");
		}
		
		return Mat4d.transform(l, r, r);
	}
	
	public static <T extends Tup2dW> T transform(Mat4d l, Tup2dR r, T res)
	{
		double x_ = l.m[0][0] * r.getX() + l.m[0][1] * r.getY() + l.m[0][2] * 1.0f + l.m[0][3] * 1.0f;
		double y_ = l.m[1][0] * r.getX() + l.m[1][1] * r.getY() + l.m[1][2] * 1.0f + l.m[1][3] * 1.0f;

		res.set(x_, y_);

		return res;
	}

	public <T extends Tup2dW> T transform(Tup2dR r, T res)
	{
		return Mat4d.transform(this, r, res);
	}

	public static Mat4d identity()
	{
		return new Mat4d().initIdentity();
	}
	
	public static Mat4d zero()
	{
		return new Mat4d().initZero();
	}
	
	public static Mat4d scaling(Tup3dR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return scaling(t.getX(), t.getY(), t.getZ());
	}
	public static Mat4d scaling(double x, double y, double z)
	{
		return new Mat4d().initScaling(x, y,z);
	}
	
	public static Mat4d translation(Tup3dR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return translation(t.getX(),t.getY(), t.getZ());
	}
	public static Mat4d translation(double x, double y, double z)
	{
		return new Mat4d().initTranslation(x, y, z);
	}
	
	public static Mat4d perspective(Tup2dR t, double fovY, double near, double far)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return perspective(t.getX(), t.getY(), fovY, near, far);
	}
	public static Mat4d perspective(double width, double height, double fov, double near, double far)
	{
		return new Mat4d().initPerspective(width, height, fov, near, far);
	}
	
	public static Mat4d perspective(double fovX, double fovY, double near, double far)
	{
		return new Mat4d().initPerspective(fovX, fovY, near, far);
	}
	
	public static Mat4d perspective(double left, double right, double bottom, double top, double near, double far)
	{
		return new Mat4d().initPerspective(left, right, bottom, top, near, far);
	
	}
	public static Mat4d ortho(Tup2dR t, double near, double far)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return ortho(t.getX(), t.getY(), near, far);
	}
	
	public static Mat4d ortho(double width, double height, double near, double far)
	{
		return new Mat4d().initOrtho(width, height, near, far);
	}
	
	public static Mat4d ortho(double left, double right, double bottom, double top, double near, double far)
	{
		return new Mat4d().initOrtho(left, right, bottom, top, near, far);
	}
	
	public static Mat4d rotation(Quat q)
	{
		return new Mat4d().initRotation(q);
	}
	
	public static Mat4d rotation(Tup3dR forward, Tup3dR left, Tup3dR up)
	{
		return new Mat4d().initRotation(forward, left, up);
	}
	
	public static Mat4d modelMatrix(Tup3dR pos, Quat rot, Tup3dR scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
		}
		
		return new Mat4d().initModelMatrix(pos, rot, scale);
	}
	
	public static Mat4d viewMatrix(Tup3dR pos, Quat rot)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(rot == null) throw new ArgumentNullException("rot");
		}
		
		return new Mat4d().initViewMatrix(pos, rot);
	}
	
	public static Mat4d lookAt(Tup3dR pos, Tup3dR target, Tup3dR up)
	{
		return new Mat4d().initLookAt(pos, target, up);
	} 
	
	public Mat4d scale(Tup3dR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return Mat4d.mul(Mat4d.scaling(t), this, this);
	}
	
	public Mat4d scale(double x, double y, double z)
	{
		return Mat4d.mul(Mat4d.scaling(x, y, z), this, this);
	}
	public Mat4d translate(Tup3dR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return Mat4d.mul(Mat4d.translation(t), this, this);
	}
	public Mat4d translate(double x, double y, double z)
	{
		return Mat4d.mul(Mat4d.translation(x, y, z), this, this);
	}
	public Mat4d rotate(Quat q)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(q == null) throw new ArgumentNullException("q");
		}
		
		return Mat4d.mul(Mat4d.rotation(q), this, this);
	}
	public Mat4d rotate(Tup3dR forward, Tup3dR left, Tup3dR up)
	{
		return Mat4d.mul(Mat4d.rotation(forward, left, up), this, this);
	}
	
	public FloatBuffer toBufferColumnMajor(FloatBuffer res)
	{
		for(int i = 0; i < COLUMNS; i++)
		{
			res.put((float)this.m[0][i]);
			res.put((float)this.m[1][i]);
			res.put((float)this.m[2][i]);
			res.put((float)this.m[3][i]);
		}
		
		res.flip();
		
		return res;
	}
	
	public double[] toArrayColumnMajor()
	{
		double[] out = new double[ROWS * COLUMNS];

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
			res.put((float)this.m[i][0]);
			res.put((float)this.m[i][1]);
			res.put((float)this.m[i][2]);
			res.put((float)this.m[i][3]);
		}
		
		res.flip();
		
		return res;
	}
	
	public double[] toArrayRowMajor()
	{
		double[] out = new double[ROWS * COLUMNS];
		
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

	public Mat4d invert()
	{
		double determinant = determinant();
		
		if(determinant != 0)
		{
			double determinant_inv = 1f/determinant;

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
			m[0][0] = _m0x * determinant_inv;
			m[1][1] = _m1y * determinant_inv;
			m[2][2] = _m2z * determinant_inv;
			m[3][3] = _m3w * determinant_inv;
			m[0][1] = _m1x * determinant_inv;
			m[1][0] = _m0y * determinant_inv;
			m[2][0] = _m0z * determinant_inv;
			m[0][2] = _m2x * determinant_inv;
			m[1][2] = _m2y * determinant_inv;
			m[2][1] = _m1z * determinant_inv;
			m[0][3] = _m3x * determinant_inv;
			m[3][0] = _m0w * determinant_inv;
			m[1][3] = _m3y * determinant_inv;
			m[3][1] = _m1w * determinant_inv;
			m[3][2] = _m2w * determinant_inv;
			m[2][3] = _m3z * determinant_inv;
			
			return this;
		}
		
		return null;
	}
	
	public Mat4d invertN()
	{
		double determinant = determinant();
		
		if(determinant != 0)
		{
			double determinant_inv = 1f / determinant;

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

			Mat4d out = new Mat4d();
			
			// transpose and divide by the determinant
			out.m[0][0] = _m0x * determinant_inv;
			out.m[1][1] = _m1y * determinant_inv;
			out.m[2][2] = _m2z * determinant_inv;
			out.m[3][3] = _m3w * determinant_inv;
			out.m[0][1] = _m1x * determinant_inv;
			out.m[1][0] = _m0y * determinant_inv;
			out.m[2][0] = _m0z * determinant_inv;
			out.m[0][2] = _m2x * determinant_inv;
			out.m[1][2] = _m2y * determinant_inv;
			out.m[2][1] = _m1z * determinant_inv;
			out.m[0][3] = _m3x * determinant_inv;
			out.m[3][0] = _m0w * determinant_inv;
			out.m[1][3] = _m3y * determinant_inv;
			out.m[3][1] = _m1w * determinant_inv;
			out.m[3][2] = _m2w * determinant_inv;
			out.m[2][3] = _m3z * determinant_inv;
			
			return out;
		}
		
		return null;
	}
	
	public String toString()
	{
		return 	"mat4d(" + this.m[0][0] + ", " + this.m[0][1] + ", " + this.m[0][2] + ", " + this.m[0][3] + "\n"
			  + "      " + this.m[1][0] + ", " + this.m[1][1] + ", " + this.m[1][2] + ", " + this.m[1][3] + "\n"
			  + "      " + this.m[2][0] + ", " + this.m[2][1] + ", " + this.m[2][2] + ", " + this.m[2][3] + "\n"
			  + "      " + this.m[3][0] + ", " + this.m[3][1] + ", " + this.m[3][2] + ", " + this.m[3][3] + ")";
	}
	
	public Mat4d clone()
	{
		return new Mat4d(this);
	}
}
