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

package org.barghos.math.vec3;

import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.tuple3.api.Tup3fR;
import org.barghos.core.tuple3.api.Tup3fRW;
import org.barghos.core.tuple3.api.Tup3fW;
import org.barghos.core.util.Nullable;
import org.barghos.math.BarghosMath;
import org.barghos.math.utils.Maths;
import org.barghos.math.vec3.api.Vec3fRW;

/**
 * @author picatrix1899
 */
public class Vec3fWrapper implements Vec3fRW
{
	private Tup3fRW internal;
	
	public Vec3fWrapper() { }
	
	public Vec3fWrapper(@Nullable Tup3fRW internal)
	{
		setInternal(internal);
	}
	
	public Vec3fWrapper setInternal(@Nullable Tup3fRW internal)
	{
		this.internal = internal;
		
		return this;
	}
	
	public Tup3fRW getInternal()
	{
		return this.internal;
	}
	
	public boolean isValid()
	{
		return this.internal != null;
	}
	
	@Override
	public float getX()
	{
		return this.internal.getX();
	}

	@Override
	public float getY()
	{
		return this.internal.getY();
	}

	@Override
	public float getZ()
	{
		return this.internal.getZ();
	}
	
	@Override
	public Vec3fWrapper setX(float x)
	{
		this.internal.setX(x);
		
		return this;
	}

	@Override
	public Vec3fWrapper setY(float y)
	{
		this.internal.setY(y);
		
		return this;
	}

	@Override
	public Vec3fWrapper setZ(float z)
	{
		this.internal.setZ(z);
		
		return this;
	}

	@Override
	public Vec3fWrapper set(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		this.internal.set(t);
		
		return this;
	}

	@Override
	public Vec3fWrapper set(float value)
	{
		this.internal.set(value);
		
		return this;
	}

	@Override
	public Vec3fWrapper set(float x, float y, float z)
	{
		this.internal.set(x, y, z);
		
		return this;
	}

	@Override
	public Vec3fWrapper add(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return add(t.getX(), t.getY(), t.getZ());
	}

	@Override
	public Vec3fWrapper add(float scalar)
	{
		return add(scalar, scalar, scalar);
	}

	@Override
	public Vec3fWrapper add(float x, float y, float z)
	{
		return set(this.internal.getX() + x, this.internal.getY() + y, this.internal.getZ());
	}

	@Override
	public <T extends Tup3fW> T add(Tup3fR t, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return add(t.getX(), t.getY(), t.getZ(), res);
	}

	@Override
	public <T extends Tup3fW> T add(float scalar, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return add(scalar, scalar, scalar, res);
	}

	@Override
	public <T extends Tup3fW> T add(float x, float y, float z, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		res.set(this.internal.getX() + x, this.internal.getY() + y, this.internal.getZ() + z);
		
		return res;
	}

	@Override
	public Vec3f addN(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return new Vec3f(this.internal).add(t);
	}

	@Override
	public Vec3f addN(float scalar)
	{
		return new Vec3f(this.internal).add(scalar);
	}

	@Override
	public Vec3f addN(float x, float y, float z)
	{
		return new Vec3f(this.internal).add(x, y, z);
	}

	@Override
	public Vec3fWrapper sub(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return sub(t.getX(), t.getY(), t.getZ());
	}

	@Override
	public Vec3fWrapper sub(float scalar)
	{
		return sub(scalar, scalar, scalar);
	}

	@Override
	public Vec3fWrapper sub(float x, float y, float z)
	{
		return set(this.internal.getX() - x, this.internal.getY() - y, this.internal.getZ() - z);
	}

	@Override
	public <T extends Tup3fW> T sub(Tup3fR t, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return sub(t.getX(), t.getY(), t.getZ(), res);
	}

	@Override
	public <T extends Tup3fW> T sub(float scalar, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return sub(scalar, scalar, scalar, res);
	}

	@Override
	public <T extends Tup3fW> T sub(float x, float y, float z, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		res.set(this.internal.getX() - x, this.internal.getY() - y, this.internal.getZ() - z);
		
		return res;
	}

	@Override
	public Vec3f subN(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return new Vec3f(this.internal).sub(t);
	}

	@Override
	public Vec3f subN(float scalar)
	{
		return new Vec3f(this.internal).sub(scalar);
	}

	@Override
	public Vec3f subN(float x, float y, float z)
	{
		return new Vec3f(this.internal).sub(x, y, z);
	}

	@Override
	public Vec3fWrapper mul(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return mul(t.getX(), t.getY(), t.getZ());
	}

	@Override
	public Vec3fWrapper mul(float scalar)
	{
		return mul(scalar, scalar, scalar);
	}

	@Override
	public Vec3fWrapper mul(float x, float y, float z)
	{
		return set(this.internal.getX() * x, this.internal.getY() * y, this.internal.getZ());
	}

	@Override
	public <T extends Tup3fW> T mul(Tup3fR t, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return mul(t.getX(), t.getY(), t.getZ(), res);
	}

	@Override
	public <T extends Tup3fW> T mul(float scalar, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return mul(scalar, scalar, scalar, res);
	}

	@Override
	public <T extends Tup3fW> T mul(float x, float y, float z, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		res.set(this.internal.getX() * x, this.internal.getY() * y, this.internal.getZ() * z);
		
		return res;
	}

	@Override
	public Vec3f mulN(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return new Vec3f(this.internal).mul(t);
	}

	@Override
	public Vec3f mulN(float scalar)
	{
		return new Vec3f(this.internal).mul(scalar);
	}

	@Override
	public Vec3f mulN(float x, float y, float z)
	{
		return new Vec3f(this.internal).mul(x, y, z);
	}

	@Override
	public Vec3fWrapper div(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return div(t.getX(), t.getY(), t.getZ());
	}

	@Override
	public Vec3fWrapper div(float scalar)
	{
		return div(scalar, scalar, scalar);
	}

	@Override
	public Vec3fWrapper div(float x, float y, float z)
	{
		return set(this.internal.getX() / x, this.internal.getY() / y, this.internal.getZ() / z);
	}

	@Override
	public <T extends Tup3fW> T div(Tup3fR t, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return div(t.getX(), t.getY(), t.getZ(), res);
	}

	@Override
	public <T extends Tup3fW> T div(float scalar, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return div(scalar, scalar, scalar, res);
	}

	@Override
	public <T extends Tup3fW> T div(float x, float y, float z, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		res.set(this.internal.getX() / x, this.internal.getY() / y, this.internal.getZ() / z);
		
		return res;
	}

	@Override
	public Vec3f divN(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return new Vec3f(this.internal).div(t);
	}

	@Override
	public Vec3f divN(float scalar)
	{
		return new Vec3f(this.internal).div(scalar);
	}

	@Override
	public Vec3f divN(float x, float y, float z)
	{
		return new Vec3f(this.internal).div(x, y, z);
	}

	@Override
	public Vec3fWrapper normal()
	{
		return div(length());
	}

	@Override
	public <T extends Tup3fW> T normal(T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		div(length(), res);
		
		return res;
	}

	@Override
	public Vec3f normalN()
	{
		return new Vec3f(this.internal).normal();
	}

	@Override
	public Vec3fWrapper normalSafe()
	{
		return isZero() ? set(0.0f) : normal();
	}

	@Override
	public Vec3fWrapper normalSafe(float tolerance)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(tolerance < 0) throw new IllegalArgumentException();
		}
		
		return isZero(tolerance) ? set(0.0f) : normal();
	}

	@Override
	public <T extends Tup3fW> T normalSafe(T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		if(isZero())
			res.set(0.0f);
		else
			normal(res);
		
		return res;
	}

	@Override
	public <T extends Tup3fW> T normalSafe(float tolerance, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(tolerance < 0) throw new IllegalArgumentException();
			if(res == null) throw new ArgumentNullException("res");
		}
		
		if(isZero(tolerance))
			res.set(0.0f);
		else
			normal(res);
		
		return res;
	}

	@Override
	public Vec3f normalSafeN()
	{
		return new Vec3f(this.internal).normalSafe();
	}

	@Override
	public Vec3f normalSafeN(float tolerance)
	{
		return new Vec3f(this.internal).normalSafe(tolerance);
	}

	@Override
	public Vec3fWrapper invert()
	{
		return set(-this.internal.getX(), -this.internal.getY(), -this.internal.getZ());
	}

	@Override
	public <T extends Tup3fW> T invert(T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		res.set(-this.internal.getX(), -this.internal.getY(), -this.internal.getZ());
		
		return res;
	}

	@Override
	public Vec3f invertN()
	{
		return new Vec3f(this.internal).invert();
	}

	@Override
	public Vec3fWrapper cross(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return cross(t.getX(), t.getY(), t.getZ());
	}

	@Override
	public Vec3fWrapper cross(float x, float y, float z)
	{
		return set(this.internal.getY() * z - this.internal.getZ() * y, this.internal.getZ() * x - this.internal.getX() * z, this.internal.getX() * y - this.internal.getY() * x);
	}

	@Override
	public <T extends Tup3fW> T cross(Tup3fR t, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return cross(t.getX(), t.getY(), t.getZ(), res);
	}

	@Override
	public <T extends Tup3fW> T cross(float x, float y, float z, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		res.set(this.internal.getY() * z - this.internal.getZ() * y, this.internal.getZ() * x - this.internal.getX() * z, this.internal.getX() * y - this.internal.getY() * x);
		
		return res;
	}

	@Override
	public Vec3f crossN(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return new Vec3f(this.internal).cross(t);
	}

	@Override
	public Vec3f crossN(float x, float y, float z)
	{
		return new Vec3f(this.internal).cross(x, y, z);
	}

	@Override
	public Vec3fWrapper snapToGrid(Tup3fR grid)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(grid == null) throw new ArgumentNullException("grid");
		}
		
		return snapToGrid(grid.getX(), grid.getY(), grid.getZ());
	}

	@Override
	public Vec3fWrapper snapToGrid(float scalar)
	{
		return snapToGrid(scalar, scalar, scalar);
	}

	@Override
	public Vec3fWrapper snapToGrid(float gx, float gy, float gz)
	{
		return set(Maths.gridSnap(this.internal.getX(), gx), Maths.gridSnap(this.internal.getY(), gy), Maths.gridSnap(this.internal.getZ(), gz));
	}

	@Override
	public <T extends Tup3fW> T snapToGrid(Tup3fR grid, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(grid == null) throw new ArgumentNullException("grid");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return snapToGrid(grid.getX(), grid.getY(), grid.getZ(), res);
	}

	@Override
	public <T extends Tup3fW> T snapToGrid(float scalar, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return snapToGrid(scalar, scalar, scalar, res);
	}

	@Override
	public <T extends Tup3fW> T snapToGrid(float gx, float gy, float gz, T res)
	{
		res.set(Maths.gridSnap(this.internal.getX(), gx), Maths.gridSnap(this.internal.getY(), gy), Maths.gridSnap(this.internal.getZ(), gz));
		
		return res;
	}

	@Override
	public Vec3f snapToGridN(Tup3fR grid)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(grid == null) throw new ArgumentNullException("grid");
		}
		
		return new Vec3f(this.internal).snapToGrid(grid);
	}

	@Override
	public Vec3f snapToGridN(float scalar)
	{
		return new Vec3f(this.internal).snapToGrid(scalar);
	}

	@Override
	public Vec3f snapToGridN(float gx, float gy, float gz)
	{
		return new Vec3f(this.internal).snapToGrid(gx, gy, gz);
	}
}
