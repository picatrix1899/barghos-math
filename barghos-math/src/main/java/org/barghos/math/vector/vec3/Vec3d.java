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

import org.barghos.core.Barghos;
import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.tuple3.api.Tup3dR;
import org.barghos.core.tuple3.api.Tup3dW;
import org.barghos.core.tuple4.api.Tup4dR;
import org.barghos.core.util.Nullable;
import org.barghos.math.BarghosMath;
import org.barghos.math.Maths;

/**
 * @author picatrix1899
 *
 * Represents a 3-dimensional mathematical vector in euclidean space.
 * This is a full featured version with common operations.
 */
public class Vec3d implements Vec3dR, Tup3dW
{
	protected double x;
	protected double y;
	protected double z;
	
	public Vec3d()
	{
		set(0.0, 0.0, 0.0);
	}
	
	public Vec3d(Tup3dR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		set(t.getX(), t.getY(), t.getZ());
	}
	
	public Vec3d(double x, double y, double z)
	{
		set(x, y, z);
	}
	
	public double getX() { return this.x; }
	public double getY() { return this.y; }
	public double getZ() { return this.z; }
	
	public Vec3d setX(double x) { this.x = x; return this; }
	public Vec3d setY(double y) { this.y = y; return this; }
	public Vec3d setZ(double z) { this.z = z; return this; }
	
	public Vec3d set(Tup3dR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return set(t.getX(), t.getY(), t.getZ()); 
	}
	
	public Vec3d set(double value)
	{
		return setX(value).setY(value).setZ(value);
	}
	
	public Vec3d set(double x, double y, double z)
	{
		return setX(x).setY(y).setZ(z); 
	}
	
	public <T extends Vec3d> T add(Tup3dR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return add(t.getX(), t.getY(), t.getZ());
	}
	
	public <T extends Vec3d> T add(double scalar)
	{
		return add(scalar, scalar, scalar);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Vec3d> T add(double x, double y, double z)
	{
		return (T)set(this.x + x, this.y + y, this.z + z);
	}
	
	public <T extends Tup3dW> T add(Tup3dR t, T res)
	{
		if(Barghos.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return add(t.getX(), t.getY(), t.getZ(), res);
	}
	
	public <T extends Tup3dW> T add(double scalar, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return add(scalar, scalar, scalar, res);
	}
	
	public <T extends Tup3dW> T add(double x, double y, double z, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		res.set(this.x + x, this.y + y, this.z + z); return res;
	}
	
	public <T extends Vec3d> T addN(Tup3dR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return clone().add(t.getX(), t.getY(), t.getZ());
	}
	public <T extends Vec3d> T addN(double scalar)
	{
		return clone().add(scalar, scalar, scalar);
	}
	
	public <T extends Vec3d> T addN(double x, double y, double z)
	{
		return clone().add(x, y, z);
	}
	
	public <T extends Vec3d> T sub(Tup3dR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return sub(t.getX(), t.getY(), t.getZ());
	}
	public <T extends Vec3d> T sub(double scalar)
	{
		return sub(scalar, scalar, scalar);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Vec3d> T sub(double x, double y, double z)
	{
		return (T)set(this.x - x, this.y - y, this.z - z);
	}
	
	public <T extends Tup3dW> T sub(Tup3dR t, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return sub(t.getX(), t.getY(), t.getZ(), res);
	}
	
	public <T extends Tup3dW> T sub(double scalar, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return sub(scalar, scalar, scalar, res);
	}
	
	public <T extends Tup3dW> T sub(double x, double y, double z, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		res.set(this.x - x, this.y - y, this.z - z); return res;
	}
	
	public <T extends Vec3d> T subN(Tup3dR t)
	{
		if(Barghos.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return clone().sub(t.getX(), t.getY(), t.getZ());
	}
	
	public <T extends Vec3d> T subN(double scalar)
	{
		return clone().sub(scalar, scalar, scalar);
	}
	
	public <T extends Vec3d> T subN(double x, double y, double z)
	{
		return clone().sub(x, y, z);
	}
	
	public <T extends Vec3d> T mul(Tup3dR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return mul(t.getX(), t.getY(), t.getZ());
	}
	
	public <T extends Vec3d> T mul(double scalar)
	{
		return mul(scalar, scalar, scalar);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Vec3d> T mul(double x, double y, double z)
	{
		return (T)set(this.x * x, this.y * y, this.z * z);
	}
	
	public <T extends Tup3dW> T mul(Tup3dR t, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return mul(t.getX(), t.getY(), t.getZ(), res);
	}
	
	public <T extends Tup3dW> T mul(double scalar, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return mul(scalar, scalar, scalar, res);
	}
	
	public <T extends Tup3dW> T mul(double x, double y, double z, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		res.set(this.x * x, this.y * y, this.z * z); return res;
	}
	
	public <T extends Vec3d> T mulN(Tup3dR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return clone().mul(t.getX(), t.getY(), t.getZ());
	}
	
	public <T extends Vec3d> T mulN(double scalar)
	{
		return clone().mul(scalar, scalar, scalar);
	}
	
	public <T extends Vec3d> T mulN(double x, double y, double z)
	{
		return clone().mul(x, y, z);
	}
	
	public <T extends Vec3d> T div(Tup3dR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return div(t.getX(), t.getY(), t.getZ());
	}
	
	public <T extends Vec3d> T div(double scalar)
	{
		return div(scalar, scalar, scalar);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Vec3d> T div(double x, double y, double z)
	{
		return (T)set(this.x / x, this.y / y, this.z / z);
		}
	
	public <T extends Tup3dW> T div(Tup3dR t, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return div(t.getX(), t.getY(), t.getZ(), res);
	}
	
	public <T extends Tup3dW> T div(double scalar, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return div(scalar, scalar, scalar, res);
	}
	
	public <T extends Tup3dW> T div(double x, double y, double z, T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		res.set(this.x / x, this.y / y, this.z / z); return res;
	}
	
	public <T extends Vec3d> T divN(Tup3dR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return clone().div(t.getX(), t.getY(), t.getZ());
	}
	
	public <T extends Vec3d> T divN(double scalar)
	{
		return clone().div(scalar, scalar, scalar);
	}
	
	public <T extends Vec3d> T divN(double x, double y, double z)
	{
		return clone().div(x, y, z);
	}
	
	public double length()
	{
		return Maths.sqrt(squaredLength());
	}
	
	public double lengthSafe()
	{
		return isZero() ? 0.0f : length();
	}
	
	public double lengthSafe(double tolerance)
	{
		return isZero(tolerance) ? 0.0 : length();
	}
	
	public double reciprocalLength()
	{
		return 1.0 / length();
	}

	public double reciprocalLengthSafe()
	{
		return isZero() ? 0.0 : reciprocalLength();
	}
	
	public double reciprocalLengthSafe(double tolerance)
	{
		return isZero(tolerance) ? 0.0 : reciprocalLength();
	}

	public double squaredLength()
	{
		return this.x * this.x + this.y * this.y + this.z * this.z;
	}

	public Vec3d normal() 
	{
		return div(length());
	}
	
	public Vec3d normal(@Nullable Vec3d res)
	{
		return div(length(), res);
	}
	
	public Vec3d normalSafe()
	{
		return isZero() ? set(0.0, 0.0, 0.0) : normal();
	}
	
	public Vec3d normalSafe(double tolerance)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(tolerance < 0) throw new IllegalArgumentException();
		}
		
		return isZero(tolerance) ? set(0.0, 0.0, 0.0) : normal();
	}
	
	public Vec3d normalSafe(@Nullable Vec3d res)
	{
		if(res == null) res = new Vec3d();
		return isZero() ? res.set(0.0f, 0.0f, 0.0f) : normal(res);
	}
	
	public Vec3d normalSafe(double tolerance, @Nullable Vec3d res)
	{
		if(tolerance < 0) throw new IllegalArgumentException();
		if(res == null) res = new Vec3d(); 
		return isZero(tolerance) ? res.set(0.0f, 0.0f, 0.0f) : normal(res);
	}
	
	public Vec3d invert()
	{
		return set(-this.x, -this.y, -this.z);
	}
	
	public Vec3d invert(@Nullable Vec3d res)
	{
		if(res == null) res = new Vec3d();
		return res.set(-this.x, -this.y, -this.z);
	}
	
	public double dot(Tup3dR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return dot(t.getX(), t.getY(), t.getZ());
	}
	
	public double dot(double x, double y, double z)
	{
		return this.x * x + this.y * y + this.z * z;
	}
	
	public double dot(Tup4dR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return dot(t.getX(), t.getY(), t.getZ(), t.getW());
	}
	
	public double dot(double x, double y, double z, double w)
	{
		return this.x * x + this.y * y + this.z * z;
	}
	
	public Vec3d cross(Tup3dR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return cross(t.getX(), t.getY(), t.getZ());
	}
	
	public Vec3d cross(double x, double y, double z)
	{
		return set(this.y * z - this.z * y, this.z * x - this.x * z, this.x * y - this.y * x);
	}
	
	public Vec3d cross(Tup3dR t, @Nullable Vec3d res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return cross(t.getX(), t.getY(), t.getZ(), res);
	}
	
	public Vec3d cross(double x, double y, double z, @Nullable Vec3d res)
	{
		if(res == null) res = new Vec3d();
		return res.set(this.y * z - this.z * y, this.z * x - this.x * z, this.x * y - this.y * x);
	}
	
	public <T extends Vec3d> T snapToGrid(Tup3dR grid)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(grid == null) throw new ArgumentNullException("grid");
		}
		
		return snapToGrid(grid.getX(), grid.getY(), grid.getZ());
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Vec3d> T snapToGrid(double gx, double gy, double gz)
	{
		return (T)set(Maths.gridSnap(this.x, gx), Maths.gridSnap(this.y, gy), Maths.gridSnap(this.z, gz));
	}
	
	public <T extends Tup3dW> T snapToGrid(Tup3dR grid, T res)
	{
		if(grid == null) throw new ArgumentNullException("grid");
		return snapToGrid(grid.getX(), grid.getY(), grid.getZ(), res);
	}
	
	public <T extends Tup3dW> T snapToGrid(double gx, double gy, double gz, T res)
	{
		res.set(Maths.gridSnap(this.x, gx), Maths.gridSnap(this.y, gy), Maths.gridSnap(this.z, gz));
		return res;
	}
	
	public <T extends Vec3d> T snapToGridN(Tup3dR grid)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(grid == null) throw new ArgumentNullException("grid");
		}
		
		return snapToGrid(grid.getX(), grid.getY(), grid.getZ());
	}
	
	public <T extends Vec3d> T snapToGridN(double gx, double gy, double gz)
	{
		return clone().snapToGrid(gx, gy, gz);
	}

	public boolean isZero()
	{
		return this.x == 0.0f && this.y == 0.0f && this.z == 0.0f;
	}
	
	public boolean isZero(double tolerance)
	{
		return (Math.abs(this.x) <= tolerance) && (Math.abs(this.y) <= tolerance) && (Math.abs(this.z) <= tolerance);
	}
	
	public boolean isFinite()
	{
		return Double.isFinite(this.x) && Double.isFinite(this.y) && Double.isFinite(this.z);
	}
	
	public String toString()
	{
		return "vec3d(" + this.x + ", " + this.y + ", " + this.z + ")";
	}
	
	public boolean equals(Object obj)
	{
		if(obj == null) return false;
		if(!(obj instanceof Vec3d)) return false;
		Vec3d v = (Vec3d)obj;
		
		if(this.x != v.x) return false;
		if(this.y != v.y) return false;
		if(this.z != v.z) return false;
		
		return true;
	}
	
	public Vec3d clone()
	{
		return new Vec3d(this);
	}
}
