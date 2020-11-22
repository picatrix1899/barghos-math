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

import org.barghos.core.tuple2.api.Tup2fR;
import org.barghos.core.tuple2.api.Tup2fW;
import org.barghos.core.tuple3.api.Tup3fR;
import org.barghos.core.tuple3.api.Tup3fW;
import org.barghos.core.tuple4.api.Tup4fR;
import org.barghos.core.tuple4.api.Tup4fW;

import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.tuple3.Tup3f;
import org.barghos.core.util.Nullable;

import org.barghos.math.matrix.api.Mat3fR;
import org.barghos.math.matrix.api.Mat4fR;
import org.barghos.math.utils.api.EulerRotationOrder3;
import org.barghos.math.utils.api.ITransform3f;

import org.barghos.math.BarghosMath;
import org.barghos.math.point.Point3f;
import org.barghos.math.quat.Quatf;
import org.barghos.math.utils.EulerAngles2f;
import org.barghos.math.utils.EulerAngles3f;
import org.barghos.math.utils.EulerAnglesRad2f;
import org.barghos.math.utils.EulerAnglesRad3f;
import org.barghos.math.utils.LinearSystem3;
import org.barghos.math.utils.Maths;
import org.barghos.math.vec3.Vec3f;
import org.barghos.math.vec3.pool.Vec3fPool;

public class Mat4f extends SimpleMat4f
{	
	public Mat4f() { }
	
	public Mat4f(Mat4f m)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(m == null) throw new ArgumentNullException("m");
		}
		
		set(m);
	}
	
	public Mat4f set(Mat4fR m)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(m == null) throw new ArgumentNullException("m");
		}
		
		super.set(m);

		return this;
	}
	
	public Mat4f set(Mat3fR m)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(m == null) throw new ArgumentNullException("m");
		}

		super.set(m);
		
		return this;
	}
	
	public Mat4f setRow(int index, Tup4fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		super.setRow(index, t);
		
		return this;
	}
	
	public Mat4f setRow(int index, Tup3fR t, float w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		super.setRow(index, t, w);
		
		return this;
	}
	
	public Mat4f setRow(int index, float x, Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		super.setRow(index, x, t);
		
		return this;
	}
	
	public Mat4f setRow(int index, Tup2fR t, float z, float w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		super.setRow(index, t, z, w);
		
		return this;
	}
	
	public Mat4f setRow(int index, float x, Tup2fR t, float w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		super.setRow(index, x, t, w);
		
		return this;
	}
	
	public Mat4f setRow(int index, float x, float y, Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		super.setRow(index, x, y, t);
		
		return this;
	}
	
	public Mat4f setRow(int index, float x, float y, float z, float w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= ROWS) throw new IndexOutOfBoundsException();
		}
		
		super.setRow(index, x, y, z, w);
		
		return this;
	}
	
	public Mat4f setColumn(int index, Tup4fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		super.setColumn(index, t);
		
		return this;
	}
	
	public Mat4f setColumn(int index, Tup3fR t, float w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		super.setColumn(index, t, w);
		
		return this;
	}
	
	public Mat4f setColumn(int index, float x, Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		super.setColumn(index, x, t);
		
		return this;
	}
	
	public Mat4f setColumn(int index, Tup2fR t, float z, float w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		super.setColumn(index, t, z, w);
		
		return this;
	}
	
	public Mat4f setColumn(int index, float x, Tup2fR t, float w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		super.setColumn(index, x, t, w);
		
		return this;
	}
	
	public Mat4f setColumn(int index, float x, float y, Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
			if(t == null) throw new ArgumentNullException("t");
		}
		
		super.setColumn(index, x, y, t);
		
		return this;
	}
	
	public Mat4f setColumn(int index, float x, float y, float z, float w)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(index < 0 || index >= COLUMNS) throw new IndexOutOfBoundsException();
		}
		
		super.setColumn(index, x, y, z, w);
		
		return this;
	}
	
	public Mat4f initIdentity()
	{
		setRow(0, 1.0f, 0.0f, 0.0f, 0.0f);
		setRow(1, 0.0f, 1.0f, 0.0f, 0.0f);
		setRow(2, 0.0f, 0.0f, 1.0f, 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		return this;
	}

	public Mat4f initZero()
	{
		setRow(0, 0.0f, 0.0f, 0.0f, 0.0f);
		setRow(1, 0.0f, 0.0f, 0.0f, 0.0f);
		setRow(2, 0.0f, 0.0f, 0.0f, 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 0.0f);
		
		return this;
	}
	
	public Mat4f initScaling4D(Tup4fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return initScaling4D(t.getX(), t.getY(), t.getZ(), t.getW());
	}
	
	public Mat4f initScaling4D(float x, float y, float z, float w)
	{
		setRow(0, x,	0.0f,	0.0f, 	0.0f);
		setRow(1, 0.0f,	y,		0.0f, 	0.0f);
		setRow(2, 0.0f,	0.0f,	z, 		0.0f);
		setRow(3, 0.0f,	0.0f,	0.0f, 	w);
		
		return this;
	}
	
	public Mat4f initScaling3D(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return initScaling3D(t.getX(), t.getY(), t.getZ());
	}
	
	public Mat4f initScaling3D(float x, float y, float z)
	{
		setRow(0, x,	0.0f,	0.0f, 	0.0f);
		setRow(1, 0.0f,	y,		0.0f, 	0.0f);
		setRow(2, 0.0f,	0.0f,	z, 		0.0f);
		setRow(3, 0.0f,	0.0f,	0.0f, 	1.0f);
		
		return this;
	}
	
	public Mat4f initScaling2D(Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return initScaling2D(t.getX(), t.getY());
	}
	
	public Mat4f initScaling2D(float x, float y)
	{
		setRow(0, x,	0.0f,	0.0f, 	0.0f);
		setRow(1, 0.0f,	y,		0.0f, 	0.0f);
		setRow(2, 0.0f,	0.0f,	1.0f, 	0.0f);
		setRow(3, 0.0f,	0.0f,	0.0f, 	1.0f);
		
		return this;
	}
	
	public Mat4f initTranslation3D(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return initTranslation3D(t.getX(), t.getY(), t.getZ());
	}
	
	public Mat4f initTranslation3D(float x, float y, float z)
	{
		setRow(0, 1.0f, 0.0f, 0.0f, x	);
		setRow(1, 0.0f, 1.0f, 0.0f, y	);
		setRow(2, 0.0f, 0.0f, 1.0f, z	);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat4f initTranslation2D(Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return initTranslation2D(t.getX(), t.getY());
	}
	
	public Mat4f initTranslation2D(float x, float y)
	{
		setRow(0, 1.0f, 0.0f, 0.0f, x	);
		setRow(1, 0.0f, 1.0f, 0.0f, y	);
		setRow(2, 0.0f, 0.0f, 1.0f, 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat4f initRotation2D(EulerAngles2f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		float rad = angles.getAngle() * Maths.DEG_TO_RADf;
		
		setRow(0, Maths.cos(rad), 0.0f, 0.0f, 0.0f);
		setRow(1, 0.0f, Maths.sin(rad), 0.0f, 0.0f);
		setRow(2, 0.0f, 0.0f, 1.0f, 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat4f initRotation2DRad(EulerAnglesRad2f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		setRow(0, Maths.cos(angles.getAngle()), 0.0f, 0.0f, 0.0f);
		setRow(1, 0.0f, Maths.sin(angles.getAngle()), 0.0f, 0.0f);
		setRow(2, 0.0f, 0.0f, 1.0f, 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat4f initRotation2D(float angle)
	{
		float rad = angle * Maths.DEG_TO_RADf;
		
		setRow(0, Maths.cos(rad), 0.0f, 0.0f, 0.0f);
		setRow(1, 0.0f, Maths.sin(rad), 0.0f, 0.0f);
		setRow(2, 0.0f, 0.0f, 1.0f, 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat4f initRotation2DRad(float angle)
	{
		setRow(0, Maths.cos(angle), 0.0f, 0.0f, 0.0f);
		setRow(1, 0.0f, Maths.sin(angle), 0.0f, 0.0f);
		setRow(2, 0.0f, 0.0f, 1.0f, 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat4f initRotation3D(Quatf q)
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
	
	public Mat4f initRotation3D(Tup3fR forward, Tup3fR left, Tup3fR up)
	{
		setColumn(0, left, 0.0f);
		setColumn(1, up, 0.0f);
		setColumn(2, forward, 0.0f);
		setColumn(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat4f initPitchRotation3D(float angle)
	{
		float rad = angle * Maths.DEG_TO_RADf;

		setRow(0, 1.0f, 0.0f, 0.0f, 0.0f);
		setRow(1, 0.0f, Maths.cos(rad), Maths.sin(rad), 0.0f);
		setRow(2, 0.0f, -Maths.sin(rad), Maths.cos(rad), 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		return this;
	}

	public Mat4f initPitchRotation3DRad(float angle)
	{
		setRow(0, 1.0f, 0.0f, 0.0f, 0.0f);
		setRow(1, 0.0f, Maths.cos(angle), Maths.sin(angle), 0.0f);
		setRow(2, 0.0f, -Maths.sin(angle), Maths.cos(angle), 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat4f initPitchRotation3D(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		float rad = angle * Maths.DEG_TO_RADf;

		setRow(0, 1.0f, 0.0f, 0.0f, 0.0f);
		setRow(1, 0.0f, Maths.cos(rad), Maths.sin(rad), 0.0f);
		setRow(2, 0.0f, -Maths.sin(rad), Maths.cos(rad), 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		mul(Mat4f.rotation3D(system), this);
		
		return this;
	}

	public Mat4f initPitchRotation3DRad(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		setRow(0, 1.0f, 0.0f, 0.0f, 0.0f);
		setRow(1, 0.0f, Maths.cos(angle), Maths.sin(angle), 0.0f);
		setRow(2, 0.0f, -Maths.sin(angle), Maths.cos(angle), 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		mul(Mat4f.rotation3D(system), this);
		
		return this;
	}
	
	public Mat4f initYawRotation3DRad(float angle)
	{
		setRow(0, Maths.cos(angle), 0.0f, -Maths.sin(angle), 0.0f);
		setRow(1, 0.0f, 1, 0.0f, 0.0f);
		setRow(2, Maths.sin(angle), 0.0f, Maths.cos(angle), 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat4f initYawRotation3D(float angle)
	{
		float rad = angle * Maths.DEG_TO_RADf;

		setRow(0, Maths.cos(rad), 0.0f, -Maths.sin(rad), 0.0f);
		setRow(1, 0.0f, 1, 0.0f, 0.0f);
		setRow(2, Maths.sin(rad), 0.0f, Maths.cos(rad), 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat4f initYawRotation3D(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		float rad = angle * Maths.DEG_TO_RADf;
		
		setRow(0, Maths.cos(rad), 0.0f, -Maths.sin(rad), 0.0f);
		setRow(1, 0.0f, 1, 0.0f, 0.0f);
		setRow(2, Maths.sin(rad), 0.0f, Maths.cos(rad), 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		mul(Mat4f.rotation3D(system), this);
		
		return this;
	}
	
	public Mat4f initYawRotation3DRad(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		setRow(0, Maths.cos(angle), 0.0f, -Maths.sin(angle), 0.0f);
		setRow(1, 0.0f, 1, 0.0f, 0.0f);
		setRow(2, Maths.sin(angle), 0.0f, Maths.cos(angle), 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		mul(Mat4f.rotation3D(system), this);
		
		return this;
	}
	
	public Mat4f initRollRotation3D(float angle)
	{
		float rad = angle * Maths.DEG_TO_RADf;

		setRow(0, Maths.cos(rad), Maths.sin(rad), 0.0f, 0.0f);
		setRow(1, -Maths.sin(rad), Maths.cos(rad), 0.0f, 0.0f);
		setRow(2, 0.0f, 0.0f, 1.0f, 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat4f initRollRotation3DRad(float angle)
	{
		setRow(0, Maths.cos(angle), Maths.sin(angle), 0.0f, 0.0f);
		setRow(1, -Maths.sin(angle), Maths.cos(angle), 0.0f, 0.0f);
		setRow(2, 0.0f, 0.0f, 1.0f, 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat4f initRollRotation3D(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		float rad = angle * Maths.DEG_TO_RADf;

		setRow(0, Maths.cos(rad), Maths.sin(rad), 0.0f, 0.0f);
		setRow(1, -Maths.sin(rad), Maths.cos(rad), 0.0f, 0.0f);
		setRow(2, 0.0f, 0.0f, 1.0f, 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		mul(Mat4f.rotation3D(system), this);
		
		return this;
	}
	
	public Mat4f initRollRotation3DRad(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		setRow(0, Maths.cos(angle), Maths.sin(angle), 0.0f, 0.0f);
		setRow(1, -Maths.sin(angle), Maths.cos(angle), 0.0f, 0.0f);
		setRow(2, 0.0f, 0.0f, 1.0f, 0.0f);
		setRow(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		mul(Mat4f.rotation3D(system), this);
		
		return this;
	}
	
	public Mat4f initRotation3D(EulerAngles3f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		initRotation3D(angles, BarghosMath.DEFAULT_EULER_ROTATION_ORDER);
		
		return this;
	}
	
	public Mat4f initRotation3DRad(EulerAnglesRad3f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		initRotation3DRad(angles, BarghosMath.DEFAULT_EULER_ROTATION_ORDER);
		
		return this;
	}
	
	public Mat4f initRotation3D(EulerAngles3f angles, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
		}
		
		initRotation3D(angles, system, BarghosMath.DEFAULT_EULER_ROTATION_ORDER);
		
		return this;
	}
	
	public Mat4f initRotation3DRad(EulerAnglesRad3f angles, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
		}
		
		initRotation3DRad(angles, system, BarghosMath.DEFAULT_EULER_ROTATION_ORDER);
		
		return this;
	}
	
	public Mat4f initRotation3D(EulerAngles3f angles, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		switch(order)
		{
			case PITCH_YAW_ROLL:
			{
				initRollRotation3D(angles.getRoll());
				yawRotate3D(angles.getYaw());
				pitchRotate3D(angles.getPitch());
				
				break;
			}
			case PITCH_ROLL_YAW:
			{
				initYawRotation3D(angles.getRoll());
				rollRotate3D(angles.getYaw());
				pitchRotate3D(angles.getPitch());
				
				break;
			}
			case YAW_PITCH_ROLL:
			{
				initRollRotation3D(angles.getRoll());
				pitchRotate3D(angles.getYaw());
				yawRotate3D(angles.getPitch());
				
				break;
			}
			case YAW_ROLL_PITCH:
			{
				initPitchRotation3D(angles.getRoll());
				rollRotate3D(angles.getYaw());
				yawRotate3D(angles.getPitch());
				
				break;
			}
			case ROLL_PITCH_YAW:
			{
				initYawRotation3D(angles.getRoll());
				pitchRotate3D(angles.getYaw());
				rollRotate3D(angles.getPitch());
				
				break;
			}
			case ROLL_YAW_PITCH:
			{
				initPitchRotation3D(angles.getRoll());
				yawRotate3D(angles.getYaw());
				rollRotate3D(angles.getPitch());
				
				break;
			}
		}
		
		return this;
	}
	
	public Mat4f initRotation3DRad(EulerAnglesRad3f angles, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		switch(order)
		{
			case PITCH_YAW_ROLL:
			{
				initRollRotation3DRad(angles.getRoll());
				yawRotate3DRad(angles.getYaw());
				pitchRotate3DRad(angles.getPitch());
				break;
			}
			case PITCH_ROLL_YAW:
			{
				initYawRotation3DRad(angles.getRoll());
				rollRotate3DRad(angles.getYaw());
				pitchRotate3DRad(angles.getPitch());
				break;
			}
			case YAW_PITCH_ROLL:
			{
				initRollRotation3DRad(angles.getRoll());
				pitchRotate3DRad(angles.getYaw());
				yawRotate3DRad(angles.getPitch());
				
				break;
			}
			case YAW_ROLL_PITCH:
			{
				initPitchRotation3DRad(angles.getRoll());
				rollRotate3DRad(angles.getYaw());
				yawRotate3DRad(angles.getPitch());
				
				break;
			}
			case ROLL_PITCH_YAW:
			{
				initYawRotation3DRad(angles.getRoll());
				pitchRotate3DRad(angles.getYaw());
				rollRotate3DRad(angles.getPitch());
				
				break;
			}
			case ROLL_YAW_PITCH:
			{
				initPitchRotation3DRad(angles.getRoll());
				yawRotate3DRad(angles.getYaw());
				rollRotate3DRad(angles.getPitch());
				
				break;
			}
		}
		
		return this;
	}
	
	public Mat4f initRotation3D(EulerAngles3f angles, LinearSystem3 system, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		switch(order)
		{
			case PITCH_YAW_ROLL:
			{
				initRollRotation3D(angles.getRoll());
				yawRotate3D(angles.getYaw());
				pitchRotate3D(angles.getPitch());
				break;
			}
			case PITCH_ROLL_YAW:
			{
				initYawRotation3D(angles.getRoll());
				rollRotate3D(angles.getYaw());
				pitchRotate3D(angles.getPitch());
				break;
			}
			case YAW_PITCH_ROLL:
			{
				initRollRotation3D(angles.getRoll());
				pitchRotate3D(angles.getYaw());
				yawRotate3D(angles.getPitch());
				
				break;
			}
			case YAW_ROLL_PITCH:
			{
				initPitchRotation3D(angles.getRoll());
				rollRotate3D(angles.getYaw());
				yawRotate3D(angles.getPitch());
				
				break;
			}
			case ROLL_PITCH_YAW:
			{
				initYawRotation3D(angles.getRoll());
				pitchRotate3D(angles.getYaw());
				rollRotate3D(angles.getPitch());
				
				break;
			}
			case ROLL_YAW_PITCH:
			{
				initPitchRotation3D(angles.getRoll());
				yawRotate3D(angles.getYaw());
				rollRotate3D(angles.getPitch());
				
				break;
			}
		}
		
		mul(Mat4f.rotation3D(system), this);
		
		return this;
	}
	
	public Mat4f initRotation3DRad(EulerAnglesRad3f angles, LinearSystem3 system, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		switch(order)
		{
			case PITCH_YAW_ROLL:
			{
				initRollRotation3DRad(angles.getRoll());
				yawRotate3DRad(angles.getYaw());
				pitchRotate3DRad(angles.getPitch());
				break;
			}
			case PITCH_ROLL_YAW:
			{
				initYawRotation3DRad(angles.getRoll());
				rollRotate3DRad(angles.getYaw());
				pitchRotate3DRad(angles.getPitch());
				break;
			}
			case YAW_PITCH_ROLL:
			{
				initRollRotation3DRad(angles.getRoll());
				pitchRotate3DRad(angles.getYaw());
				yawRotate3DRad(angles.getPitch());
				
				break;
			}
			case YAW_ROLL_PITCH:
			{
				initPitchRotation3DRad(angles.getRoll());
				rollRotate3DRad(angles.getYaw());
				yawRotate3DRad(angles.getPitch());
				
				break;
			}
			case ROLL_PITCH_YAW:
			{
				initYawRotation3DRad(angles.getRoll());
				pitchRotate3DRad(angles.getYaw());
				rollRotate3DRad(angles.getPitch());
				
				break;
			}
			case ROLL_YAW_PITCH:
			{
				initPitchRotation3DRad(angles.getRoll());
				yawRotate3DRad(angles.getYaw());
				rollRotate3DRad(angles.getPitch());
				
				break;
			}
		}
		
		mul(Mat4f.rotation3D(system), this);
		
		return this;
	}
	
	public Mat4f initRotation3D(LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		setColumn(0, system.getLeft(), 0.0f);
		setColumn(1, system.getUp(), 0.0f);
		setColumn(2, system.getForward(), 0.0f);
		setColumn(3, 0.0f, 0.0f, 0.0f, 1.0f);
		
		return this;
	}
	
	public Mat4f initTransform3D(ITransform3f t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}

		initIdentity();
		
		rotate3D(t.getOrientation());
		translate3D(t.getPosition());
		scale3D(t.getScale());

		return this;
	}
	
	public Mat4f initPerspective(Tup2fR t, float fov, float near, float far)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		
		return initPerspective(t.getX(), t.getY(), fov, near, far);
	}
	
	public Mat4f initPerspective(float width, float height, float fovY, float near, float far)
	{
		float y_scale = 1.0f / (float)Math.tan(fovY * 0.5 * Maths.DEG_TO_RAD);
		float x_scale = y_scale / (width / height);

		setRow(0, x_scale,	0f,			0f,								0f								);
		setRow(1, 0f,		y_scale,	0f,								0f								);
		setRow(2, 0f,		0f,			-(far + near) / (far - near),	-2f * near * far / (far - near)	);
		setRow(3, 0f,		0f,			-1f,							0f								);
		
		return this;
	}
	
	public Mat4f initPerspective(float fovX, float fovY, float near, float far)
	{
		float y_scale = 1.0f / (float)Math.tan(fovY * 0.5 * Maths.DEG_TO_RAD);
		float x_scale = 1.0f / (float)Math.tan(fovX * 0.5 * Maths.DEG_TO_RAD);

		setRow(0, x_scale,	0f,			0f,								0f								);
		setRow(1, 0f,		y_scale,	0,								0f								);
		setRow(2, 0f,		0f,			-(far + near) / (far - near),	-2f * near * far / (far - near)	);
		setRow(3, 0f,		0f,			-1f,							0f								);
		
		return this;
	}
	
	public Mat4f initPerspective(float left, float right, float bottom, float top, float near, float far)
	{
		setRow(0, 2f * near / (right - left),		0f,									0f,									0f	);
		setRow(1, 0f,								2f * near / (top - bottom),			0f,									0f	);
		setRow(2, (right + left) / (right - left),	(top + bottom) / (top - bottom),	-(far + near) / (far - near),		-1f	);
		setRow(3, 0f,								0f,									-2f * far * near / (far - near),	0f	);
		
		return this;
	}
	
	public Mat4f initOrtho(Tup2fR t, float near, float far)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return initOrtho(t.getX(), t.getY(), near, far);
	}
	
	public Mat4f initOrtho(float width, float height, float length)
	{
		setRow(0, 2.0f / width,	0f,				0f,						0f);
		setRow(1, 0f,			2.0f / height,	0f,						0f);
		setRow(2, 0f,			0f, 			-2.0f / length,			0f);
		setRow(3, 0f,			0f,				0f,						1f);
		
		return this;
	}
	
	public Mat4f initOrtho2(float width, float height, float fovY, float near, float far)
	{
		float y_scale = 1.0f / (float)Math.tan(fovY * 0.5 * Maths.DEG_TO_RAD);
		float x_scale = y_scale / (width / height);
		
		setRow(0, 2.0f / x_scale,	0f,				0f,						0f);
		setRow(1, 0f,			2.0f / y_scale,	0f,						0f);
		setRow(2, 0f,			0f, 			-2.0f / (far - near),	0f);
		setRow(3, 0f,			0f,				0f,						1f);
		
		return this;
	}
	
	public Mat4f initOrtho(float width, float height, float near, float far)
	{
		setRow(0, 2.0f / width,	0f,				0f,						0f);
		setRow(1, 0f,			2.0f / height,	0f,						0f);
		setRow(2, 0f,			0f, 			-2.0f / (far - near),	0f);
		setRow(3, 0f,			0f,				0f,						1f);
		
		return this;
	}
	
	public Mat4f initOrtho(float left, float right, float bottom, float top, float near, float far)
	{
		setRow(0, 2.0f / (right - left),				0f,									0f,								0f);
		setRow(1, 0f,									2.0f / (top - bottom),				0f,								0f);
		setRow(2, 0f,									0f, 								-2.0f / (far - near),			0f);
		setRow(3, 0f,		0f,	0f, 	1f);
		 
		return this;
	}
	
	public Mat4f initOrthoWithCorrection(float left, float right, float bottom, float top, float near, float far)
	{
		setRow(0, 2.0f / (right - left),				0f,									0f,								-(right + left) / (right - left));
		setRow(1, 0f,									2.0f / (top - bottom),				0f,								-(top + bottom) / (top - bottom));
		setRow(2, 0f,									0f, 								-2.0f / (far - near),			-(far + near) / (far - near));
		setRow(3, 0f,		0f,	0f, 	1f);
		 
		return this;
	}
	
	public Mat4f initModelMatrix(Tup3fR pos, Quatf rot, Tup3fR scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(rot == null) throw new ArgumentNullException("rot");
		}
		
		if(scale == null) scale = new Tup3f(1.0f);
		
		initIdentity();
		
		rotate3D(rot);
		translate3D(pos);
		scale3D(scale);

		return this;
	}
	
	public Mat4f initViewMatrix(Tup3fR pos, Quatf rot)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(rot == null) throw new ArgumentNullException("rot");
		}
		
		initIdentity();
		
		rotate3D(rot.conjugate(new Quatf()));
		translate3D(-pos.getX(), -pos.getY(), -pos.getZ());
		
		return this;
	}
	
	public Mat4f initLookAt(Tup3fR pos, Tup3fR target, Tup3fR worldUp)
	{
		Vec3f vpos = Vec3fPool.get(pos);
		Vec3f vtarget = Vec3fPool.get(target);
		Vec3f vworldUp = Vec3fPool.get(worldUp);
		
		Vec3f zaxis = vpos.sub(vtarget, Vec3fPool.get()).normal();
		Vec3f xaxis = vworldUp.normal().cross(zaxis, Vec3fPool.get()).normal();
		Vec3f yaxis = zaxis.cross(xaxis, Vec3fPool.get());
	
		initIdentity();
		translate3D(vpos.invert());
		rotate3D(zaxis, xaxis, yaxis);
		
		Vec3fPool.store(vpos, vtarget, vworldUp, zaxis, xaxis, yaxis);
		
		return this;
	}

	public Mat4f mul(Mat4fR r)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
		}
		
		mul(r, this);
		
		return this;
	}
	
	public Mat4f transpose()
	{
		return super.transpose(this);
	}
	
	public Point3f transform(Point3f r)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
		}

		return Mat4f.transform(this, r, r);
	}
	
	public Point3f transform(Point3f r, Point3f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
			if(res == null) throw new ArgumentNullException("res");
		}

		return Mat4f.transform(this, r, res);
	}
	
	public Vec3f transform(Vec3f r)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
		}
		
		return Mat4f.transform(this, r, r);
	}
	
	public Vec3f transform(Vec3f r, Vec3f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(r == null) throw new ArgumentNullException("r");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return Mat4f.transform(this, r, res);
	}
	
	public <T extends Tup2fW> T transform(Tup2fR r, T res)
	{
		return Mat4f.transform(this, r, res);
	}

	public Mat4f scale4D(Tup4fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return mul(Mat4f.scaling4D(t));
	}
	
	public Mat4f scale4D(float x, float y, float z, float w)
	{
		return mul(Mat4f.scaling4D(x, y, z, w));
	}
	
	public Mat4f scale3D(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return mul(Mat4f.scaling3D(t));
	}
	
	public Mat4f scale3D(float x, float y, float z)
	{
		return mul(Mat4f.scaling3D(x, y, z));
	}
	
	public Mat4f scale2D(Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return mul(Mat4f.scaling2D(t));
	}
	
	public Mat4f scale2D(float x, float y)
	{
		return mul(Mat4f.scaling2D(x, y));
	}
	
	public Mat4f rotate2D(EulerAngles2f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return mul(Mat4f.rotation2D(angles));
	}
	
	public Mat4f rotate2DRad(EulerAnglesRad2f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return mul(Mat4f.rotation2DRad(angles));
	}
	
	public Mat4f rotate2D(float angle)
	{
		return mul(Mat4f.rotation2D(angle));
	}
	
	public Mat4f rotate2DRad(float angle)
	{
		return mul(Mat4f.rotation2DRad(angle));
	}
	
	public Mat4f rotate3D(Quatf q)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(q == null) throw new ArgumentNullException("q");
		}
		
		return mul(Mat4f.rotation3D(q));
	}
	
	public Mat4f rotate3D(Tup3fR forward, Tup3fR left, Tup3fR up)
	{
		return mul(Mat4f.rotation3D(forward, left, up));
	}
	
	public Mat4f rotate3D(LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return mul(Mat4f.rotation3D(system));
	}
	
	public Mat4f pitchRotate3D(float angle)
	{
		return mul(Mat4f.pitchRotation3D(angle));
	}
	
	public Mat4f pitchRotate3DRad(float angle)
	{
		return mul(Mat4f.pitchRotation3DRad(angle));
	}
	
	public Mat4f pitchRotate3D(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return mul(Mat4f.pitchRotation3D(angle, system));
	}
	
	public Mat4f pitchRotate3DRad(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return mul(Mat4f.pitchRotation3DRad(angle, system));
	}
	
	public Mat4f yawRotate3D(float angle)
	{
		return mul(Mat4f.yawRotation3D(angle));
	}
	
	public Mat4f yawRotate3DRad(float angle)
	{
		return mul(Mat4f.yawRotation3DRad(angle));
	}
	
	public Mat4f yawRotate3D(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return mul(Mat4f.yawRotation3D(angle, system));
	}
	
	public Mat4f yawRotate3DRad(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return mul(Mat4f.yawRotation3DRad(angle, system));
	}
	
	public Mat4f rollRotate3D(float angle)
	{
		return mul(Mat4f.rollRotation3D(angle));
	}
	
	public Mat4f rollRotate3DRad(float angle)
	{
		return mul(Mat4f.rollRotation3DRad(angle));
	}
	
	public Mat4f rollRotate3D(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return mul(Mat4f.rollRotation3D(angle, system));
	}
	
	public Mat4f rollRotate3DRad(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return mul(Mat4f.rollRotation3DRad(angle, system));
	}
	
	public Mat4f rotate3D(EulerAngles3f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return mul(Mat4f.rotation3D(angles));
	}
	
	public Mat4f rotate3DRad(EulerAnglesRad3f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return mul(Mat4f.rotation3DRad(angles));
	}
	
	public Mat4f rotate3D(EulerAngles3f angles, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return mul(Mat4f.rotation3D(angles, system));
	}
	
	public Mat4f rotate3DRad(EulerAnglesRad3f angles, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return mul(Mat4f.rotation3DRad(angles, system));
	}
	
	public Mat4f rotate3D(EulerAngles3f angles, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return mul(Mat4f.rotation3D(angles, order));
	}
	
	public Mat4f rotate3DRad(EulerAnglesRad3f angles, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return mul(Mat4f.rotation3DRad(angles, order));
	}
	
	public Mat4f rotate3D(EulerAngles3f angles, LinearSystem3 system, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return mul(Mat4f.rotation3D(angles, system, order));
	}
	
	public Mat4f rotate3DRad(EulerAnglesRad3f angles, LinearSystem3 system, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return mul(Mat4f.rotation3DRad(angles, system, order));
	}
	
	public Mat4f translate3D(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return mul(Mat4f.translation3D(t));
	}
	public Mat4f translate3D(float x, float y, float z)
	{
		return mul(Mat4f.translation3D(x, y, z));
	}

	public Mat4f invert()
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
	
	public Mat4f invertN()
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

			Mat4f out = new Mat4f();
			
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
	
	public Mat4f clean()
	{
		super.clean();
		
		return this;
	}
	
	public String toString()
	{
		return 	"mat4f(" + this.m[0][0] + ", " + this.m[0][1] + ", " + this.m[0][2] + ", " + this.m[0][3] + "\n"
			  + "      " + this.m[1][0] + ", " + this.m[1][1] + ", " + this.m[1][2] + ", " + this.m[1][3] + "\n"
			  + "      " + this.m[2][0] + ", " + this.m[2][1] + ", " + this.m[2][2] + ", " + this.m[2][3] + "\n"
			  + "      " + this.m[3][0] + ", " + this.m[3][1] + ", " + this.m[3][2] + ", " + this.m[3][3] + ")";
	}
	
	public Mat4f clone()
	{
		return new Mat4f(this);
	}
	
	public static Mat4f mul(Mat4f l, Mat4f r, @Nullable Mat4f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(l == null) throw new ArgumentNullException("l");
			if(r == null) throw new ArgumentNullException("r");
		}
		
		if(res == null) res = new Mat4f();
		
		l.mul(r, res);
		
		return res;
	}
	
	public static Point3f transform(Mat4f l, Point3f r, Point3f res)
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
	
	public static Vec3f transform(Mat4f l, Vec3f r, Vec3f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(l == null) throw new ArgumentNullException("l");
			if(r == null) throw new ArgumentNullException("r");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		float x_ = l.m[0][0] * r.getX() + l.m[0][1] * r.getY() + l.m[0][2] * r.getZ();
		float y_ = l.m[1][0] * r.getX() + l.m[1][1] * r.getY() + l.m[1][2] * r.getZ();
		float z_ = l.m[2][0] * r.getX() + l.m[2][1] * r.getY() + l.m[2][2] * r.getZ();
		
		res.set(x_, y_, z_);

		return res;
	}
	
	public static <T extends Tup4fW> T transform(Mat4f l, Tup4fR r, T res)
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
	
	public static <T extends Tup3fW> T transform(Mat4f l, Tup3fR r, T res)
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
	
	public static <T extends Tup2fW> T transform(Mat4f l, Tup2fR r, T res)
	{
		float x_ = l.m[0][0] * r.getX() + l.m[0][1] * r.getY() + l.m[0][2] * 1.0f + l.m[0][3] * 1.0f;
		float y_ = l.m[1][0] * r.getX() + l.m[1][1] * r.getY() + l.m[1][2] * 1.0f + l.m[1][3] * 1.0f;

		res.set(x_, y_);

		return res;
	}
	
	public static <T extends Tup4fR & Tup4fW> T transform(Mat4f l, T r)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(l == null) throw new ArgumentNullException("l");
			if(r == null) throw new ArgumentNullException("r");
		}
		
		return Mat4f.transform(l, r, r);
	}
	
	public static <T extends Tup3fR & Tup3fW> T transform(Mat4f l, T r)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(l == null) throw new ArgumentNullException("l");
			if(r == null) throw new ArgumentNullException("r");
		}
		
		return Mat4f.transform(l, r, r);
	}
	
	public static Mat4f identity()
	{
		return new Mat4f().initIdentity();
	}
	
	public static Mat4f zero()
	{
		return new Mat4f().initZero();
	}
	
	public static Mat4f scaling4D(Tup4fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return scaling4D(t.getX(), t.getY(), t.getZ(), t.getW());
	}
	
	public static Mat4f scaling4D(float x, float y, float z, float w)
	{
		return new Mat4f().initScaling4D(x, y, z, w);
	}
	
	public static Mat4f scaling3D(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return scaling3D(t.getX(), t.getY(), t.getZ());
	}
	
	public static Mat4f scaling3D(float x, float y, float z)
	{
		return new Mat4f().initScaling3D(x, y, z);
	}
	
	public static Mat4f scaling2D(Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return scaling2D(t.getX(), t.getY());
	}
	
	public static Mat4f scaling2D(float x, float y)
	{
		return new Mat4f().initScaling2D(x, y);
	}
	
	public static Mat4f rotation2D(EulerAngles2f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return new Mat4f().initRotation2D(angles);
	}
	
	public static Mat4f rotation2DRad(EulerAnglesRad2f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return new Mat4f().initRotation2DRad(angles);
	}
	
	public static Mat4f rotation2D(float angle)
	{
		return new Mat4f().initRotation2D(angle);
	}
	
	public static Mat4f rotation2DRad(float angle)
	{
		return new Mat4f().initRotation2DRad(angle);
	}
	
	public static Mat4f rotation3D(Quatf q)
	{
		return new Mat4f().initRotation3D(q);
	}
	
	public static Mat4f rotation3D(Tup3fR forward, Tup3fR left, Tup3fR up)
	{
		return new Mat4f().initRotation3D(forward, left, up);
	}
	
	public static Mat4f rotation3D(LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return new Mat4f().initRotation3D(system);
	}
	
	public static Mat4f pitchRotation3D(float angle)
	{
		return new Mat4f().initPitchRotation3D(angle);
	}
	
	public static Mat4f pitchRotation3DRad(float angle)
	{
		return new Mat4f().initPitchRotation3DRad(angle);
	}
	
	public static Mat4f pitchRotation3D(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return new Mat4f().initPitchRotation3D(angle, system);
	}
	
	public static Mat4f pitchRotation3DRad(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return new Mat4f().initPitchRotation3DRad(angle, system);
	}
	
	public static Mat4f yawRotation3D(float angle)
	{
		return new Mat4f().initYawRotation3D(angle);
	}
	
	public static Mat4f yawRotation3DRad(float angle)
	{
		return new Mat4f().initYawRotation3DRad(angle);
	}
	
	public static Mat4f yawRotation3D(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return new Mat4f().initYawRotation3D(angle, system);
	}
	
	public static Mat4f yawRotation3DRad(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return new Mat4f().initYawRotation3DRad(angle, system);
	}
	
	public static Mat4f rollRotation3D(float angle)
	{
		return new Mat4f().initRollRotation3D(angle);
	}
	
	public static Mat4f rollRotation3DRad(float angle)
	{
		return new Mat4f().initRollRotation3DRad(angle);
	}
	
	public static Mat4f rollRotation3D(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return new Mat4f().initRollRotation3D(angle);
	}
	
	public static Mat4f rollRotation3DRad(float angle, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return new Mat4f().initRollRotation3DRad(angle, system);
	}
	
	public static Mat4f rotation3D(EulerAngles3f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return new Mat4f().initRotation3D(angles);
	}
	
	public static Mat4f rotation3DRad(EulerAnglesRad3f angles)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
		}
		
		return new Mat4f().initRotation3DRad(angles);
	}
	
	public static Mat4f rotation3D(EulerAngles3f angles, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return new Mat4f().initRotation3D(angles, system);
	}
	
	public static Mat4f rotation3DRad(EulerAnglesRad3f angles, LinearSystem3 system)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
		}
		
		return new Mat4f().initRotation3DRad(angles, system);
	}
	
	public static Mat4f rotation3D(EulerAngles3f angles, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return new Mat4f().initRotation3D(angles, order);
	}
	
	public static Mat4f rotation3DRad(EulerAnglesRad3f angles, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return new Mat4f().initRotation3DRad(angles, order);
	}
	
	public static Mat4f rotation3D(EulerAngles3f angles, LinearSystem3 system, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return new Mat4f().initRotation3D(angles, system, order);
	}
	
	public static Mat4f rotation3DRad(EulerAnglesRad3f angles, LinearSystem3 system, EulerRotationOrder3 order)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(angles == null) throw new ArgumentNullException("angles");
			if(system == null) throw new ArgumentNullException("system");
			if(order == null) throw new ArgumentNullException("order");
		}
		
		return new Mat4f().initRotation3DRad(angles, system, order);
	}
	
	public static Mat4f translation3D(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return translation3D(t.getX(), t.getY(), t.getZ());
	}
	
	public static Mat4f translation3D(float x, float y, float z)
	{
		return new Mat4f().initTranslation3D(x, y, z);
	}
	
	public static Mat4f translation2D(Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return translation2D(t.getX(),t.getY());
	}
	
	public static Mat4f translation2D(float x, float y)
	{
		return new Mat4f().initTranslation2D(x, y);
	}
	
	public static Mat4f perspective(Tup2fR t, float fovY, float near, float far)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return perspective(t.getX(), t.getY(), fovY, near, far);
	}
	public static Mat4f perspective(float width, float height, float fov, float near, float far)
	{
		return new Mat4f().initPerspective(width, height, fov, near, far);
	}
	
	public static Mat4f perspective(float fovX, float fovY, float near, float far)
	{
		return new Mat4f().initPerspective(fovX, fovY, near, far);
	}
	
	public static Mat4f perspective(float left, float right, float bottom, float top, float near, float far)
	{
		return new Mat4f().initPerspective(left, right, bottom, top, near, far);
	
	}
	public static Mat4f ortho(Tup2fR t, float near, float far)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return ortho(t.getX(), t.getY(), near, far);
	}
	
	public static Mat4f ortho(float width, float height, float near, float far)
	{
		return new Mat4f().initOrtho(width, height, near, far);
	}
	
	public static Mat4f ortho(float left, float right, float bottom, float top, float near, float far)
	{
		return new Mat4f().initOrtho(left, right, bottom, top, near, far);
	}
	
	public static Mat4f modelMatrix(Tup3fR pos, Quatf rot, Tup3fR scale)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
		}
		
		return new Mat4f().initModelMatrix(pos, rot, scale);
	}
	
	public static Mat4f viewMatrix(Tup3fR pos, Quatf rot)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pos == null) throw new ArgumentNullException("pos");
			if(rot == null) throw new ArgumentNullException("rot");
		}
		
		return new Mat4f().initViewMatrix(pos, rot);
	}
	
	public static Mat4f lookAt(Tup3fR pos, Tup3fR target, Tup3fR up)
	{
		return new Mat4f().initLookAt(pos, target, up);
	} 
}
