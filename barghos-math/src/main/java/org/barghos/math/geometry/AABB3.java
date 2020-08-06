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

import org.barghos.core.tuple3.api.Tup3fR;
import org.barghos.math.matrix.Mat4;
import org.barghos.math.point.Point3;
import org.barghos.math.vector.vec3.Vec3;
import org.barghos.math.vector.vec3.Vec3Pool;

public class AABB3 implements FiniteGeometricObject3
{
	private final Point3 center = new Point3();
	private final Vec3 halfExtend = new Vec3();
	private boolean dirty;
	private final Point3[] points = new Point3[8];
	
	public AABB3() { }
	
	public AABB3(AABB3 aabb) { set(aabb); }
	
	public AABB3(Tup3fR center, Tup3fR halfExtend) { set(center, halfExtend); }
	
	public AABB3(float cX, float cY, float cZ, float heX, float heY, float heZ) { set(cX, cY, cZ, heX, heY, heZ); }
	
	public AABB3 set(AABB3 aabb)
	{
		aabb.getCenter(this.center);
		aabb.getHalfExtend(this.halfExtend);
		this.dirty = true;
		return this;
	}
	
	public AABB3 set(Tup3fR center, Tup3fR halfExtend) { return setCenter(center).setHalfExtend(halfExtend); }
	
	public AABB3 set(float cX, float cY, float cZ, float heX, float heY, float heZ) { return setCenter(cX, cY, cZ).setHalfExtend(heX, heY, heZ); }
	
	public AABB3 setHalfExtend(Tup3fR t) { return setHalfExtend(t.getX(), t.getY(), t.getZ()); }
	
	public AABB3 setHalfExtend(float x, float y, float z) { return setHalfExtendX(x).setHalfExtendY(y).setHalfExtendZ(z); }
	
	public AABB3 setHalfExtendX(float x) { this.halfExtend.setX(x); this.dirty = true; return this; }
	
	public AABB3 setHalfExtendY(float y) { this.halfExtend.setY(y); this.dirty = true; return this; }
	
	public AABB3 setHalfExtendZ(float z) { this.halfExtend.setZ(z); this.dirty = true; return this; }
	
	public AABB3 setCenter(Tup3fR t) { return setCenter(t.getX(), t.getY(), t.getZ()); }
	
	public AABB3 setCenter(float x, float y, float z) { return setCenterX(x).setCenterY(y).setCenterZ(z); }
	
	public AABB3 setCenterX(float x) { this.center.setX(x); this.dirty = true; return this; }
	
	public AABB3 setCenterY(float y) { this.center.setY(y); this.dirty = true; return this; }
	
	public AABB3 setCenterZ(float z) { this.center.setZ(z); this.dirty = true; return this; }
	
	public Point3 getCenter(Point3 res)
	{
		if(res == null) res = new Point3();
		return res.set(this.center);
	}
	
	public Point3 getCenter()
	{
		return new Point3(this.center);
	}
	
	public Vec3 getCenter(Vec3 res)
	{
		if(res == null) res = new Vec3();
		return res.set(this.center);
	}
	
	public Vec3 getHalfExtend()
	{
		return new Vec3(this.halfExtend);
	}
	
	public Vec3 getHalfExtend(Vec3 res)
	{
		if(res == null) res = new Vec3();
		return res.set(this.halfExtend);
	}
	
	public Point3 getMin(Point3 res)
	{
		if(res == null) res = new Point3();
		this.center.sub(this.halfExtend, res);
		return res;
	}
	
	public Vec3 getMin(Vec3 res)
	{
		if(res == null) res = new Vec3();
		return this.center.sub(this.halfExtend, res);
	}
	
	public Point3 getMin()
	{
		Point3 result = new Point3();
		this.center.sub(this.halfExtend, result);
		return result;
	}
	
	public Point3 getMax(Point3 res)
	{
		if(res == null) res = new Point3();
		this.center.add(this.halfExtend, res);
		return res;
	}
	
	public Vec3 getMax(Vec3 res)
	{
		if(res == null) res = new Vec3();
		return this.center.add(this.halfExtend, res);
	}
	
	public Point3 getMax()
	{
		Point3 result = new Point3();
		this.center.add(this.halfExtend, result);
		return result;
	}
	
	public Point3[] getPoints()
	{
		if(this.dirty)
		{
			Vec3 v = Vec3Pool.get(this.halfExtend);

			Vec3 t1 = Vec3Pool.get(v);
			Vec3 t2 = Vec3Pool.get(-v.getX(), v.getY(), v.getZ());
			Vec3 t3 = Vec3Pool.get(-v.getX(), -v.getY(), v.getZ());
			Vec3 t4 = Vec3Pool.get(v.getX(), -v.getY(), v.getZ());

			if(this.points[0] == null)
			{
				this.points[0] = this.center.addN(t1);
				this.points[1] = this.center.addN(t2);
				this.points[2] = this.center.addN(t3);
				this.points[3] = this.center.addN(t4);
				
				this.points[4] = this.center.sub(t1);
				this.points[5] = this.center.sub(t2);
				this.points[6] = this.center.sub(t3);
				this.points[7] = this.center.sub(t4);
			}
			else
			{
				this.center.add(t1, this.points[0]);
				this.center.add(t2, this.points[1]);
				this.center.add(t3, this.points[2]);
				this.center.add(t4, this.points[3]);
				
				this.center.sub(t1, this.points[4]);
				this.center.sub(t2, this.points[5]);
				this.center.sub(t3, this.points[6]);
				this.center.sub(t4, this.points[7]);
			}
			
			Vec3Pool.store(v, t1, t2, t3, t4);
			
			this.dirty = false;
		}
		return this.points;
	}

	public AABB3 transform(Mat4 t, AABB3 res)
	{
		if(res == null) res = new AABB3();

		Vec3 min = Vec3Pool.get();
		Vec3 max = Vec3Pool.get();

		t.transform(this.center.sub(this.halfExtend, min));
		t.transform(this.center.add(this.halfExtend, max));
		
		Vec3 halfExtend = max.sub(min).mul(0.5f);
		Vec3 center = min.add(halfExtend);
		
		res.set(center, halfExtend);
		
		Vec3Pool.store(min, max);
		
		return res;
	}
	
	public OBB3 getOBB()
	{
		return new OBB3(this.center, this.halfExtend, Mat4.IDENTITY);
	}
	
	public OBB3 getOBB(OBB3 res)
	{
		if(res == null) res = new OBB3();
		return res.set(this.center, this.halfExtend, Mat4.IDENTITY);
	}
	
	public OBB3 getOBB(Mat4 rotation)
	{
		return new OBB3(this.center, this.halfExtend, rotation);
	}

	public OBB3 getOBB(Mat4 rotation, OBB3 res)
	{
		if(res == null) res = new OBB3();
		return res.set(this.center, this.halfExtend, rotation);
	}
	
	public String toString()
	{
		return "aabb3f(center=" + this.center + ", halfExtend=" + this.halfExtend + ")";
	}
}