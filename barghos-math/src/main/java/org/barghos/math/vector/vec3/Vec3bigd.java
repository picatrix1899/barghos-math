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

package org.barghos.math.vector.vec3;

import java.math.BigDecimal;

import org.barghos.core.Barghos;
import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.tuple3.api.Tup3bigdR;
import org.barghos.core.tuple3.api.Tup3bigdW;
import org.barghos.core.tuple3.api.Tup3fW;
import org.barghos.core.tuple4.api.Tup4fR;
import org.barghos.core.util.Nullable;
import org.barghos.math.BarghosMath;
import org.barghos.math.Maths;

/**
 * @author picatrix1899
 *
 * Represents a 3-dimensional mathematical vector in euclidean space.
 * This is a full featured version with common operations.
 */
public class Vec3bigd implements Vec3bigdR, Tup3bigdW
{
	protected BigDecimal x;
	protected BigDecimal y;
	protected BigDecimal z;
	
	public Vec3bigd()
	{
		set(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
	}
	
	public Vec3bigd(Tup3bigdR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		set(t.getX(), t.getY(), t.getZ());
	}
	
	public Vec3bigd(BigDecimal x, BigDecimal y, BigDecimal z)
	{
		set(x, y, z);
	}
	
	public BigDecimal getX() { return this.x; }
	public BigDecimal getY() { return this.y; }
	public BigDecimal getZ() { return this.z; }
	
	public Vec3bigd setX(BigDecimal x) { this.x = x; return this; }
	public Vec3bigd setY(BigDecimal y) { this.y = y; return this; }
	public Vec3bigd setZ(BigDecimal z) { this.z = z; return this; }
	
	public Vec3bigd set(Tup3bigdR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return set(t.getX(), t.getY(), t.getZ()); 
	}
	
	public Vec3bigd set(BigDecimal value)
	{
		return setX(value).setY(value).setZ(value);
	}
	
	public Vec3bigd set(BigDecimal x, BigDecimal y, BigDecimal z)
	{
		return setX(x).setY(y).setZ(z); 
	}
	
	public <T extends Vec3bigd> T add(Tup3bigdR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return add(t.getX(), t.getY(), t.getZ());
	}
	
	public <T extends Vec3bigd> T add(BigDecimal scalar)
	{
		return add(scalar, scalar, scalar);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Vec3bigd> T add(BigDecimal x, BigDecimal y, BigDecimal z)
	{
		return (T)set(this.x.add(x), this.y.add(y), this.z.add(z));
	}
	
	public <T extends Tup3bigdW> T add(Tup3bigdR t, T res)
	{
		if(Barghos.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return add(t.getX(), t.getY(), t.getZ(), res);
	}
	
	public <T extends Tup3bigdW> T add(BigDecimal scalar, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return add(scalar, scalar, scalar, res);
	}
	
	public <T extends Tup3bigdW> T add(BigDecimal x, BigDecimal y, BigDecimal z, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		res.set(this.x.add(x), this.y.add(y), this.z.add(z)); return res;
	}
	
	public <T extends Vec3bigd> T addN(Tup3bigdR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return clone().add(t.getX(), t.getY(), t.getZ());
	}
	public <T extends Vec3bigd> T addN(BigDecimal scalar)
	{
		return clone().add(scalar, scalar, scalar);
	}
	
	public <T extends Vec3bigd> T addN(BigDecimal x, BigDecimal y, BigDecimal z)
	{
		return clone().add(x, y, z);
	}
	
	public <T extends Vec3bigd> T sub(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return sub(t.getX(), t.getY(), t.getZ());
	}
	public <T extends Vec3bigd> T sub(float scalar)
	{
		return sub(scalar, scalar, scalar);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Vec3bigd> T sub(float x, float y, float z)
	{
		return (T)set(this.x - x, this.y - y, this.z - z);
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
	
	public <T extends Vec3bigd> T subN(Tup3fR t)
	{
		if(Barghos.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return clone().sub(t.getX(), t.getY(), t.getZ());
	}
	
	public <T extends Vec3bigd> T subN(float scalar)
	{
		return clone().sub(scalar, scalar, scalar);
	}
	
	public <T extends Vec3bigd> T subN(float x, float y, float z)
	{
		return clone().sub(x, y, z);
	}
	
	public <T extends Vec3bigd> T mul(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return mul(t.getX(), t.getY(), t.getZ());
	}
	
	public <T extends Vec3bigd> T mul(float scalar)
	{
		return mul(scalar, scalar, scalar);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Vec3bigd> T mul(float x, float y, float z)
	{
		return (T)set(this.x * x, this.y * y, this.z * z);
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
	
	public <T extends Vec3bigd> T mulN(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return clone().mul(t.getX(), t.getY(), t.getZ());
	}
	
	public <T extends Vec3bigd> T mulN(float scalar)
	{
		return clone().mul(scalar, scalar, scalar);
	}
	
	public <T extends Vec3bigd> T mulN(float x, float y, float z)
	{
		return clone().mul(x, y, z);
	}
	
	public <T extends Vec3bigd> T div(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return div(t.getX(), t.getY(), t.getZ());
	}
	
	public <T extends Vec3bigd> T div(float scalar)
	{
		return div(scalar, scalar, scalar);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Vec3bigd> T div(float x, float y, float z)
	{
		return (T)set(this.x / x, this.y / y, this.z / z);
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
	
	public <T extends Vec3bigd> T divN(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return clone().div(t.getX(), t.getY(), t.getZ());
	}
	
	public <T extends Vec3bigd> T divN(float scalar)
	{
		return clone().div(scalar, scalar, scalar);
	}
	
	public <T extends Vec3bigd> T divN(float x, float y, float z)
	{
		return clone().div(x, y, z);
	}
	
	public float length()
	{
		return (float)Maths.sqrt(squaredLength());
	}
	
	public float lengthSafe()
	{
		return isZero() ? 0.0f : length();
	}
	
	public float lengthSafe(float tolerance)
	{
		return isZero(tolerance) ? 0.0f : length();
	}
	
	public float reciprocalLength()
	{
		return 1.0f / length();
	}

	public float reciprocalLengthSafe()
	{
		return isZero() ? 0.0f : reciprocalLength();
	}
	
	public float reciprocalLengthSafe(float tolerance)
	{
		return isZero(tolerance) ? 0.0f : reciprocalLength();
	}

	public float squaredLength()
	{
		return this.x * this.x + this.y * this.y + this.z * this.z;
	}

	public Vec3bigd normal() 
	{
		return div(length());
	}
	
	public Vec3bigd normal(@Nullable Vec3bigd res)
	{
		return div(length(), res);
	}
	
	public Vec3bigd normalSafe()
	{
		return isZero() ? set(0.0f, 0.0f, 0.0f) : normal();
	}
	
	public Vec3bigd normalSafe(float tolerance)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(tolerance < 0) throw new IllegalArgumentException();
		}
		
		return isZero(tolerance) ? set(0.0f, 0.0f, 0.0f) : normal();
	}
	
	public Vec3bigd normalSafe(@Nullable Vec3bigd res)
	{
		if(res == null) res = new Vec3bigd();
		return isZero() ? res.set(0.0f, 0.0f, 0.0f) : normal(res);
	}
	
	public Vec3bigd normalSafe(float tolerance, @Nullable Vec3bigd res)
	{
		if(tolerance < 0) throw new IllegalArgumentException();
		if(res == null) res = new Vec3bigd(); 
		return isZero(tolerance) ? res.set(0.0f, 0.0f, 0.0f) : normal(res);
	}
	
	public Vec3bigd invert()
	{
		return set(-this.x, -this.y, -this.z);
	}
	
	public Vec3bigd invert(@Nullable Vec3bigd res)
	{
		if(res == null) res = new Vec3bigd();
		return res.set(-this.x, -this.y, -this.z);
	}
	
	public float dot(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return dot(t.getX(), t.getY(), t.getZ());
	}
	
	public float dot(float x, float y, float z)
	{
		return this.x * x + this.y * y + this.z * z;
	}
	
	public float dot(Tup4fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return dot(t.getX(), t.getY(), t.getZ(), t.getW());
	}
	
	public float dot(float x, float y, float z, float w)
	{
		return this.x * x + this.y * y + this.z * z;
	}
	
	public Vec3bigd cross(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return cross(t.getX(), t.getY(), t.getZ());
	}
	
	public Vec3bigd cross(float x, float y, float z)
	{
		return set(this.y * z - this.z * y, this.z * x - this.x * z, this.x * y - this.y * x);
	}
	
	public Vec3bigd cross(Tup3fR t, @Nullable Vec3bigd res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return cross(t.getX(), t.getY(), t.getZ(), res);
	}
	
	public Vec3bigd cross(float x, float y, float z, @Nullable Vec3bigd res)
	{
		if(res == null) res = new Vec3bigd();
		return res.set(this.y * z - this.z * y, this.z * x - this.x * z, this.x * y - this.y * x);
	}
	
	public <T extends Vec3bigd> T snapToGrid(Tup3fR grid)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(grid == null) throw new ArgumentNullException("grid");
		}
		
		return snapToGrid(grid.getX(), grid.getY(), grid.getZ());
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Vec3bigd> T snapToGrid(float gx, float gy, float gz)
	{
		return (T)set(Maths.gridSnap(this.x, gx), Maths.gridSnap(this.y, gy), Maths.gridSnap(this.z, gz));
	}
	
	public <T extends Tup3fW> T snapToGrid(Tup3fR grid, T res)
	{
		if(grid == null) throw new ArgumentNullException("grid");
		return snapToGrid(grid.getX(), grid.getY(), grid.getZ(), res);
	}
	
	public <T extends Tup3fW> T snapToGrid(float gx, float gy, float gz, T res)
	{
		res.set(Maths.gridSnap(this.x, gx), Maths.gridSnap(this.y, gy), Maths.gridSnap(this.z, gz));
		return res;
	}
	
	public <T extends Vec3bigd> T snapToGridN(Tup3fR grid)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(grid == null) throw new ArgumentNullException("grid");
		}
		
		return snapToGrid(grid.getX(), grid.getY(), grid.getZ());
	}
	
	public <T extends Vec3bigd> T snapToGridN(float gx, float gy, float gz)
	{
		return clone().snapToGrid(gx, gy, gz);
	}

	public boolean isZero()
	{
		return this.x.compareTo(BigDecimal.ZERO) == 0 && this.y.compareTo(BigDecimal.ZERO) == 0 && this.z.compareTo(BigDecimal.ZERO) == 0;
	}
	
	public boolean isZero(BigDecimal tolerance)
	{
		return (this.x.abs().compareTo(tolerance) <= 0) && (this.y.abs().compareTo(tolerance) <= 0) && (this.z.abs().compareTo(tolerance) <= 0);
	}
	
	public boolean isFinite()
	{
		return true;
	}
	
	public String toString()
	{
		return "vec3(" + this.x + ", " + this.y + ", " + this.z + ")";
	}
	
	public boolean equals(Object obj)
	{
		if(obj == null) return false;
		if(!(obj instanceof Vec3bigd)) return false;
		Vec3bigd v = (Vec3bigd)obj;
		
		if(this.x != v.x) return false;
		if(this.y != v.y) return false;
		if(this.z != v.z) return false;
		
		return true;
	}
	
	public Vec3bigd clone()
	{
		return new Vec3bigd(this);
	}
}
