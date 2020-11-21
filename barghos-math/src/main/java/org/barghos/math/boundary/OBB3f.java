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

package org.barghos.math.boundary;

import org.barghos.core.tuple3.api.Tup3fR;
import org.barghos.math.geometry.FiniteGeometricObject3f;
import org.barghos.math.matrix.Mat4;
import org.barghos.math.point.Point3f;
import org.barghos.math.vec3.Vec3f;
import org.barghos.math.vec3.Vec3fAxis;
import org.barghos.math.vec3.pool.Vec3fPool;

public class OBB3f implements FiniteGeometricObject3f
{
	private Point3f center = new Point3f();
	private Vec3f halfExtend = new Point3f();
	private Mat4 rotation = Mat4.identity();
	
	private Point3f[] points = new Point3f[8];
	private Mat4 modelSpace = Mat4.identity();
	
	private boolean isPointsDirty = true;
	private boolean isModelSpaceDirty = true;
	
	public OBB3f() { }
	
	public OBB3f(OBB3f obb) { set(obb); }
	
	public OBB3f(Tup3fR center, Tup3fR halfExtend, Mat4 rotation) { set(center, halfExtend, rotation); }
	
	public OBB3f(float cX, float cY, float cZ, float heX, float heY, float heZ, Mat4 rotation) { set(cX, cY, cZ, heX, heY, heZ, rotation); }
	
	public OBB3f set(OBB3f obb)
	{
		obb.getCenter(this.center);
		obb.getHalfExtend(this.halfExtend);
		obb.getRotation(this.rotation);
		this.isPointsDirty = true;
		this.isModelSpaceDirty = true;
		return this;
	}
	
	public OBB3f set(Tup3fR center, Tup3fR halfExtend, Mat4 rotation) { return setCenter(center).setHalfExtend(halfExtend).setRotation(rotation); }
	
	public OBB3f set(float cX, float cY, float cZ, float heX, float heY, float heZ, Mat4 rotation) { return setCenter(cX, cY, cZ).setHalfExtend(heX, heY, heZ).setRotation(rotation); }
	
	public OBB3f setCenter(Tup3fR center) { return setCenter(center.getX(), center.getY(), center.getZ()); }
	
	public OBB3f setCenter(float x, float y, float z) { return setCenterX(x).setCenterY(y).setCenterZ(z); }
	
	public OBB3f setCenterX(float x) { this.center.setX(x); this.isPointsDirty = true; return this; }
	
	public OBB3f setCenterY(float y) { this.center.setY(y); this.isPointsDirty = true; return this; }
	
	public OBB3f setCenterZ(float z) { this.center.setZ(z); this.isPointsDirty = true; return this; }
	
	public OBB3f setHalfExtend(Tup3fR halfExtend) { return this.setHalfExtend(halfExtend.getX(), halfExtend.getY(), halfExtend.getZ()); }
	
	public OBB3f setHalfExtend(float x, float y, float z) { return setHalfExtendX(x).setHalfExtendY(y).setHalfExtendZ(z); }
	
	public OBB3f setHalfExtendX(float x) { this.halfExtend.setX(x); this.isPointsDirty = true; return this; }
	
	public OBB3f setHalfExtendY(float y) { this.halfExtend.setY(y); this.isPointsDirty = true; return this; }
	
	public OBB3f setHalfExtendZ(float z) { this.halfExtend.setZ(z); this.isPointsDirty = true; return this; }
	
	public OBB3f setRotation(Mat4 rotation) { this.rotation.set(rotation); this.isPointsDirty = true; this.isModelSpaceDirty = true; return this; }
	
	public Point3f getCenter()
	{
		return new Point3f(this.center);
	}
	
	public Point3f getCenter(Point3f res)
	{
		if(res == null) res = new Point3f();
		return res.set(this.center);
	}
	
	public Vec3f getCenter(Vec3f res)
	{
		if(res == null) res = new Vec3f();
		return res.set(this.center);
	}
	
	public Vec3f getHalfExtend()
	{
		return new Vec3f(this.halfExtend);
	}
	
	public Vec3f getHalfExtend(Vec3f res)
	{
		if(res == null) res = new Vec3f();
		return res.set(this.halfExtend);
	}
	
	public Mat4 getRotation()
	{
		return new Mat4(this.rotation);
	}
	
	public Mat4 getRotation(Mat4 res)
	{
		if(res == null) res = new Mat4();
		return res.set(this.rotation);
	}
	
	public Mat4 getModelSpaceMatrix()
	{
		if(this.isModelSpaceDirty)
		{
			Vec3f msX = Vec3fPool.get();
			Vec3f msY = Vec3fPool.get();
			Vec3f msZ = Vec3fPool.get();
			
			this.rotation.transform(Vec3fAxis.AXIS_X, msX);
			this.rotation.transform(Vec3fAxis.AXIS_Y, msY);
			this.rotation.transform(Vec3fAxis.AXIS_Z, msZ);
			
			Mat4 m = Mat4.identity();
			m.setRow(0, msX.getX(), msX.getY(), msX.getZ(), 0);
			m.setRow(1, msY.getX(), msY.getY(), msY.getZ(), 0);
			m.setRow(2, msZ.getX(), msZ.getY(), msZ.getZ(), 0);
			
			Vec3fPool.store(msX, msY, msZ);
			
			this.modelSpace.set(m);
			this.isModelSpaceDirty = false;
		}

		return new Mat4(this.modelSpace);
	}
	
	public Mat4 getModelSpaceMatrix(Mat4 res)
	{
		if(res == null) res = new Mat4();
		
		if(this.isModelSpaceDirty)
		{
			Vec3f msX = Vec3fPool.get();
			Vec3f msY = Vec3fPool.get();
			Vec3f msZ = Vec3fPool.get();
			
			this.rotation.transform(Vec3fAxis.AXIS_X, msX);
			this.rotation.transform(Vec3fAxis.AXIS_Y, msY);
			this.rotation.transform(Vec3fAxis.AXIS_Z, msZ);
			
			Mat4 m = Mat4.identity();
			m.setRow(0, msX.getX(), msX.getY(), msX.getZ(), 0);
			m.setRow(1, msY.getX(), msY.getY(), msY.getZ(), 0);
			m.setRow(2, msZ.getX(), msZ.getY(), msZ.getZ(), 0);
			
			Vec3fPool.store(msX, msY, msZ);
			
			this.modelSpace.set(m);
			this.isModelSpaceDirty = false;
		}

		return res.set(this.modelSpace);
	}
	
	@Override
	public Point3f[] getPoints()
	{
		if(this.isPointsDirty)
		{
			Mat4 modelSpace = getModelSpaceMatrix();

			Mat4 objToWorld = new Mat4(modelSpace).transpose();

			Vec3f v = Vec3fPool.get(this.halfExtend);

			modelSpace.transform(v);

			Vec3f t1 = Vec3fPool.get(v);
			Vec3f t2 = Vec3fPool.get(-v.getX(), v.getY(), v.getZ());
			Vec3f t3 = Vec3fPool.get(-v.getX(), -v.getY(), v.getZ());
			Vec3f t4 = Vec3fPool.get(v.getX(), -v.getY(), v.getZ());

			objToWorld.transform(t1);
			objToWorld.transform(t2);
			objToWorld.transform(t3);
			objToWorld.transform(t4);
			
			Vec3f r = Vec3fPool.get();
			
			if(this.points[0] == null)
			{
				this.points[0] = this.center.addN(t1);
				this.points[1] = this.center.addN(t2);
				this.points[2] = this.center.addN(t3);
				this.points[3] = this.center.addN(t4);
				
				this.points[4] = this.center.subN(t1);
				this.points[5] = this.center.subN(t2);
				this.points[6] = this.center.subN(t3);
				this.points[7] = this.center.subN(t4);
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

			Vec3fPool.store(v, t1, t2, t3, t4, r);
			
			this.isPointsDirty = false;
			
		}
		return this.points;
	}
	
	public OBB3f transform(Mat4 t, OBB3f res)
	{
		if(res == null) res = new OBB3f();
		
		Vec3f min = Vec3fPool.get();
		Vec3f max = Vec3fPool.get();

		t.transform(this.center.sub(this.halfExtend, min));
		t.transform(this.center.add(this.halfExtend, max));
		
		Vec3f halfExtend = max.sub(min).mul(0.5f);
		Vec3f center = min.add(halfExtend);
		
		res.set(center, halfExtend, this.rotation);
		
		Vec3fPool.store(min, max);
		
		return res;
	}
	
	@Override
	public String toString()
	{
		return "oob3(center=" + this.center + ", halfExtend=" + this.halfExtend + ")";
	}
}
