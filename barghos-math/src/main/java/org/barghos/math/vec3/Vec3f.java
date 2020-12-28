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

import org.barghos.core.Barghos;
import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.tuple3.api.Tup3fR;
import org.barghos.core.tuple3.api.Tup3fW;
import org.barghos.math.BarghosMath;
import org.barghos.math.utils.Maths;
import org.barghos.math.vec3.api.Vec3fRW;

/**
 * @author picatrix1899
 *
 * Represents a 3-dimensional mathematical vector in euclidean space.
 * This is a full featured version with common operations.
 */
public class Vec3f implements Vec3fRW
{
	protected float x;
	protected float y;
	protected float z;
	
	public Vec3f()
	{
		set(0.0f, 0.0f, 0.0f);
	}
	
	public Vec3f(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		set(t.getX(), t.getY(), t.getZ());
	}
	
	public Vec3f(float x, float y, float z)
	{
		set(x, y, z);
	}
	
	public float getX() { return this.x; }
	public float getY() { return this.y; }
	public float getZ() { return this.z; }
	
	public Vec3f setX(float x) { this.x = x; return this; }
	public Vec3f setY(float y) { this.y = y; return this; }
	public Vec3f setZ(float z) { this.z = z; return this; }
	
	public Vec3f set(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return set(t.getX(), t.getY(), t.getZ()); 
	}
	
	public Vec3f set(float value)
	{
		return setX(value).setY(value).setZ(value);
	}
	
	public Vec3f set(float x, float y, float z)
	{
		return setX(x).setY(y).setZ(z); 
	}
	
	public Vec3f add(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return add(t.getX(), t.getY(), t.getZ());
	}
	
	public Vec3f add(float scalar)
	{
		return add(scalar, scalar, scalar);
	}
	
	public Vec3f add(float x, float y, float z)
	{
		return set(this.x + x, this.y + y, this.z + z);
	}
	
	public <T extends Tup3fW> T add(Tup3fR t, T res)
	{
		if(Barghos.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return add(t.getX(), t.getY(), t.getZ(), res);
	}
	
	public <T extends Tup3fW> T add(float scalar, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return add(scalar, scalar, scalar, res);
	}
	
	public <T extends Tup3fW> T add(float x, float y, float z, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		res.set(this.x + x, this.y + y, this.z + z); return res;
	}
	
	public Vec3f addN(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return clone().add(t.getX(), t.getY(), t.getZ());
	}
	public Vec3f addN(float scalar)
	{
		return clone().add(scalar, scalar, scalar);
	}
	
	public Vec3f addN(float x, float y, float z)
	{
		return clone().add(x, y, z);
	}
	
	public Vec3f sub(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return sub(t.getX(), t.getY(), t.getZ());
	}
	public Vec3f sub(float scalar)
	{
		return sub(scalar, scalar, scalar);
	}
	
	public Vec3f sub(float x, float y, float z)
	{
		return set(this.x - x, this.y - y, this.z - z);
	}
	
	public <T extends Tup3fW> T sub(Tup3fR t, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return sub(t.getX(), t.getY(), t.getZ(), res);
	}
	
	public <T extends Tup3fW> T sub(float scalar, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return sub(scalar, scalar, scalar, res);
	}
	
	public <T extends Tup3fW> T sub(float x, float y, float z, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		res.set(this.x - x, this.y - y, this.z - z); return res;
	}
	
	public Vec3f subN(Tup3fR t)
	{
		if(Barghos.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return clone().sub(t.getX(), t.getY(), t.getZ());
	}
	
	public Vec3f subN(float scalar)
	{
		return clone().sub(scalar, scalar, scalar);
	}
	
	public Vec3f subN(float x, float y, float z)
	{
		return clone().sub(x, y, z);
	}
	
	public Vec3f mul(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return mul(t.getX(), t.getY(), t.getZ());
	}
	
	public Vec3f mul(float scalar)
	{
		return mul(scalar, scalar, scalar);
	}
	
	public Vec3f mul(float x, float y, float z)
	{
		return set(this.x * x, this.y * y, this.z * z);
	}
	
	public <T extends Tup3fW> T mul(Tup3fR t, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return mul(t.getX(), t.getY(), t.getZ(), res);
	}
	
	public <T extends Tup3fW> T mul(float scalar, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return mul(scalar, scalar, scalar, res);
	}
	
	public <T extends Tup3fW> T mul(float x, float y, float z, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		res.set(this.x * x, this.y * y, this.z * z); return res;
	}
	
	public Vec3f mulN(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return clone().mul(t.getX(), t.getY(), t.getZ());
	}
	
	public Vec3f mulN(float scalar)
	{
		return clone().mul(scalar, scalar, scalar);
	}
	
	public Vec3f mulN(float x, float y, float z)
	{
		return clone().mul(x, y, z);
	}
	
	public Vec3f div(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return div(t.getX(), t.getY(), t.getZ());
	}
	
	public Vec3f div(float scalar)
	{
		return div(scalar, scalar, scalar);
	}
	
	public Vec3f div(float x, float y, float z)
	{
		return set(this.x / x, this.y / y, this.z / z);
		}
	
	public <T extends Tup3fW> T div(Tup3fR t, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return div(t.getX(), t.getY(), t.getZ(), res);
	}
	
	public <T extends Tup3fW> T div(float scalar, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return div(scalar, scalar, scalar, res);
	}
	
	public <T extends Tup3fW> T div(float x, float y, float z, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		res.set(this.x / x, this.y / y, this.z / z); return res;
	}
	
	public Vec3f divN(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return clone().div(t.getX(), t.getY(), t.getZ());
	}
	
	public Vec3f divN(float scalar)
	{
		return clone().div(scalar, scalar, scalar);
	}
	
	public Vec3f divN(float x, float y, float z)
	{
		return clone().div(x, y, z);
	}
	
	public Vec3f normal() 
	{
		return div(length());
	}
	
	public <T extends Tup3fW> T normal(T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return div(length(), res);
	}
	
	public Vec3f normalN() 
	{
		return clone().div(length());
	}
	
	public Vec3f normalSafe()
	{
		return isZero() ? set(0.0f) : normal();
	}
	
	public Vec3f normalSafe(float tolerance)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(tolerance < 0) throw new IllegalArgumentException();
		}
		
		return isZero(tolerance) ? set(0.0f) : normal();
	}
	
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
	
	public Vec3f normalSafeN()
	{
		return clone().normalSafe();
	}
	
	public Vec3f normalSafeN(float tolerance)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(tolerance < 0) throw new IllegalArgumentException();
		}
		
		return clone().normalSafe(tolerance);
	}
	
	public Vec3f invert()
	{
		return set(-this.x, -this.y, -this.z);
	}
	
	public <T extends Tup3fW> T invert(T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		res.set(-this.x, -this.y, -this.z);
		
		return res;
	}
	
	public Vec3f invertN()
	{
		return clone().invert();
	}
	
	public Vec3f cross(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return cross(t.getX(), t.getY(), t.getZ());
	}
	
	public Vec3f cross(float x, float y, float z)
	{
		return set(this.y * z - this.z * y, this.z * x - this.x * z, this.x * y - this.y * x);
	}
	
	public <T extends Tup3fW> T cross(Tup3fR t, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return cross(t.getX(), t.getY(), t.getZ(), res);
	}
	
	public <T extends Tup3fW> T cross(float x, float y, float z, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		res.set(this.y * z - this.z * y, this.z * x - this.x * z, this.x * y - this.y * x);
		return res;
	}
	
	public Vec3f crossN(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return clone().cross(t);
	}
	
	public Vec3f crossN(float x, float y, float z)
	{
		return clone().cross(x, y, z);
	}
	
	public Vec3f snapToGrid(Tup3fR grid)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(grid == null) throw new ArgumentNullException("grid");
		}
		
		return snapToGrid(grid.getX(), grid.getY(), grid.getZ());
	}
	
	public Vec3f snapToGrid(float scalar)
	{
		return set(Maths.gridSnap(this.x, scalar), Maths.gridSnap(this.y, scalar), Maths.gridSnap(this.z, scalar));
	}
	
	public Vec3f snapToGrid(float gx, float gy, float gz)
	{
		return set(Maths.gridSnap(this.x, gx), Maths.gridSnap(this.y, gy), Maths.gridSnap(this.z, gz));
	}
	
	public <T extends Tup3fW> T snapToGrid(Tup3fR grid, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(grid == null) throw new ArgumentNullException("grid");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return snapToGrid(grid.getX(), grid.getY(), grid.getZ(), res);
	}
	
	public <T extends Tup3fW> T snapToGrid(float scalar, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return snapToGrid(scalar, scalar, scalar, res);
	}
	
	public <T extends Tup3fW> T snapToGrid(float gx, float gy, float gz, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		res.set(Maths.gridSnap(this.x, gx), Maths.gridSnap(this.y, gy), Maths.gridSnap(this.z, gz));
		return res;
	}
	
	public Vec3f snapToGridN(Tup3fR grid)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(grid == null) throw new ArgumentNullException("grid");
		}
		
		return clone().snapToGrid(grid);
	}
	
	public Vec3f snapToGridN(float scalar)
	{
		return clone().snapToGrid(scalar);
	}
	
	public Vec3f snapToGridN(float gx, float gy, float gz)
	{
		return clone().snapToGrid(gx, gy, gz);
	}

	public String toString()
	{
		return "vec3(" + this.x + ", " + this.y + ", " + this.z + ")";
	}
	
	public boolean equals(Object obj)
	{
		if(obj == null) return false;
		if(!(obj instanceof Vec3f)) return false;
		Vec3f v = (Vec3f)obj;
		
		if(this.x != v.x) return false;
		if(this.y != v.y) return false;
		if(this.z != v.z) return false;
		
		return true;
	}
	
	public Vec3f clone()
	{
		return new Vec3f(this);
	}
}
