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

package org.barghos.math.geometry;

import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.tuple3.api.Tup3fR;
import org.barghos.core.tuple3.api.Tup3fW;
import org.barghos.math.BarghosMath;
import org.barghos.math.matrix.Mat4;
import org.barghos.math.point.Point3;
import org.barghos.math.vector.vec3.Vec3;
import org.barghos.math.vector.vec3.Vec3Pool;

public class AABB3f
{
	protected final Point3 min = new Point3();
	protected final Point3 max = new Point3();
	
	public AABB3f() { }
	
	public AABB3f(AABB3f aabb)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(aabb == null) throw new ArgumentNullException("aabb");
		}
		
		set(aabb);
	}
	
	public AABB3f(Tup3fR min, Tup3fR max)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(min == null) throw new ArgumentNullException("min");
			if(max == null) throw new ArgumentNullException("max");
		}
		
		set(min, max);
	}
	
	public AABB3f(Tup3fR min, float maxX, float maxY, float maxZ)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(min == null) throw new ArgumentNullException("min");
		}
		
		set(min, maxX, maxY, maxZ);
	}
	
	public AABB3f(float minX, float minY, float minZ, Tup3fR max)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(max == null) throw new ArgumentNullException("max");
		}
		
		set(minX, minY, minZ, max);
	}
	
	public AABB3f(float minX, float minY, float minZ, float maxX, float maxY, float maxZ)
	{
		set(minX, minY, minZ, maxX, maxY, maxZ);
	}
	
	public AABB3f set(AABB3f aabb)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(aabb == null) throw new ArgumentNullException("aabb");
		}
		
		aabb.getMin(this.min);
		aabb.getMax(this.max);
		
		return this;
	}
	
	public AABB3f set(Tup3fR min, Tup3fR max)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(min == null) throw new ArgumentNullException("min");
			if(max == null) throw new ArgumentNullException("max");
		}
		
		return setMin(min).setMax(max);
	}
	
	public AABB3f set(Tup3fR min, float maxX, float maxY, float maxZ)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(min == null) throw new ArgumentNullException("min");
		}
		
		return setMin(min).setMax(maxX, maxY, maxZ);
	}
	
	public AABB3f set(float minX, float minY, float minZ, Tup3fR max)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(max == null) throw new ArgumentNullException("max");
		}
		
		return setMin(minX, minY, minZ).setMax(max);
	}
	
	public AABB3f set(float minX, float minY, float minZ, float maxX, float maxY, float maxZ)
	{
		return setMin(minX, minY, minZ).setMax(maxX, maxY, maxZ);
	}
	
	public AABB3f setMin(Tup3fR min)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(min == null) throw new ArgumentNullException("min");
		}
		
		return setMin(min.getX(), min.getY(), min.getZ());
	}
	
	public AABB3f setMin(float x, float y, float z)
	{
		this.min.set(x, y, z);
		
		return this;
	}
	
	public AABB3f setMinX(float x)
	{
		this.min.setX(x);
		
		return this;
	}
	
	public AABB3f setMinY(float y)
	{
		this.min.setY(y);
		
		return this;
	}
	
	public AABB3f setMinZ(float z)
	{
		this.min.setZ(z);
	
		return this;
	}
	
	public AABB3f setMax(Tup3fR max)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(max == null) throw new ArgumentNullException("max");
		}
		
		return setMin(max.getX(), max.getY(), max.getZ());
	}
	
	public AABB3f setMax(float x, float y, float z)
	{
		this.max.set(x, y, z);
		
		return this;
	}
	
	public AABB3f setMaxX(float x)
	{
		this.max.setX(x);
		
		return this;
	}
	
	public AABB3f setMaxY(float y)
	{
		this.max.setY(y);
		
		return this;
	}
	
	public AABB3f setMaxZ(float z)
	{
		this.max.setZ(z);
		
		return this;
	}
	
	public <T extends Tup3fW> T getMin(T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		res.set(this.min);
		
		return res;
	}
	
	public Point3 getMin()
	{
		return this.min.clone();
	}
	
	public <T extends Tup3fW> T getMax(T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		res.set(this.max);
		
		return res;
	}
	
	public Point3 getMax()
	{
		return this.max.clone();
	}

	public <T extends Tup3fW> T getCenter(T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		Vec3 halfExtend = this.max.sub(this.min, Vec3Pool.get()).mul(0.5f);
		
		this.min.add(halfExtend, res);
		
		Vec3Pool.store(halfExtend);
		
		return res;
	}
	
	public Point3 getCenter()
	{
		Vec3 halfExtend = this.max.sub(this.min, Vec3Pool.get()).mul(0.5f);
		
		Point3 res = this.min.add(halfExtend, new Point3());
		
		Vec3Pool.store(halfExtend);
		
		return res;
	}

	public <T extends Tup3fW> T getHalfExtend(T res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		Vec3 halfExtend = this.max.sub(this.min, Vec3Pool.get()).mul(0.5f);
		
		res.set(halfExtend);
		
		Vec3Pool.store(halfExtend);
		
		return res;
	}
	
	public Vec3 getHalfExtend()
	{
		Vec3 halfExtend = this.max.sub(this.min, Vec3Pool.get()).mul(0.5f);
		
		Vec3 res = new Vec3(halfExtend);
		
		Vec3Pool.store(halfExtend);
		
		return res;
	}
	
	public AABB3f transform(Mat4 t, AABB3f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
			if(res == null) throw new ArgumentNullException("res");
		}

		Vec3 min = Vec3Pool.get(this.min);
		Vec3 max = Vec3Pool.get(this.max);

		t.transform(min);
		t.transform(max);

		res.set(min, max);
		
		Vec3Pool.store(min, max);
		
		return res;
	}
	
	public AABB3f transform(Mat4 t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}

		Vec3 min = Vec3Pool.get(this.min);
		Vec3 max = Vec3Pool.get(this.max);

		t.transform(min);
		t.transform(max);

		AABB3f res = new AABB3f(min, max);
		
		Vec3Pool.store(min, max);
		
		return res;
	}
	
	public String toString()
	{
		return "aabb3f(min=" + this.min + ", max=" + this.max + ")";
	}
}