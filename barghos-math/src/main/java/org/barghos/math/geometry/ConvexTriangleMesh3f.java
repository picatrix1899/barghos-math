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

import java.util.ArrayList;
import java.util.List;

import org.barghos.math.boundary.AABB3f;
import org.barghos.math.boundary.OBB3f;
import org.barghos.math.matrix.Mat4f;
import org.barghos.math.point.Point3f;
import org.barghos.math.vec3.Vec3f;
import org.barghos.math.vec3.pool.Vec3fPool;

public class ConvexTriangleMesh3f implements FiniteGeometricObject3f
{

	private List<Triangle3f> triangles = new ArrayList<>();
	
	public ConvexTriangleMesh3f()
	{
		
	}
	
	public ConvexTriangleMesh3f(ConvexTriangleMesh3f mesh)
	{
		set(mesh.triangles);
	}
	
	public ConvexTriangleMesh3f(List<Triangle3f> triangles)
	{
		set(triangles);
	}

	public boolean isValid()
	{
		return !this.triangles.isEmpty();
	}
	
	public ConvexTriangleMesh3f set(List<Triangle3f> triangles)
	{
		this.triangles.clear();
		
		for(int i = 0; i < triangles.size(); i++)
			this.triangles.add(new Triangle3f(triangles.get(i)));
		
		return this;
	}

	public Point3f[] getPoints()
	{
		Point3f[] p = new Point3f[triangles.size() * 3];
		
		for(int i = 0; i < triangles.size(); i++)
		{
			p[i * 3] = triangles.get(i).getP1(null);
			p[i * 3 + 1] = triangles.get(i).getP2(null);
			p[i * 3 + 2] = triangles.get(i).getP3(null);
		}
		
		return p;
	}
	
	public List<Triangle3f> getTriangles()
	{
		return new ArrayList<>(this.triangles);
	}
	
	public OBB3f getOBBf(Mat4f t, Mat4f r)
	{
		PointSet3f set = getPointSet(null);

		Vec3f min = Vec3fPool.get(set.getMinX(), set.getMinY(), set.getMinZ());
		Vec3f max = Vec3fPool.get(set.getMaxX(), set.getMaxY(), set.getMaxZ());

		t.transform(min);
		t.transform(max);
		
		Vec3f halfExtend = max.sub(min).mul(0.5f);
		Point3f center = new Point3f(min.add(halfExtend, min));

		OBB3f result = new OBB3f(center, halfExtend, r);
		
		Vec3fPool.store(min, max);
		
		return result;
	}

	public AABB3f getAABBf()
	{
		PointSet3f set = getPointSet(null);
		
		Vec3f min = Vec3fPool.get(set.getMinX(), set.getMinY(), set.getMinZ());
		Vec3f max = Vec3fPool.get(set.getMaxX(), set.getMaxY(), set.getMaxZ());

		AABB3f r = new AABB3f(min, max);
		
		Vec3fPool.store(min, max);
		
		return r;
	}
	
	public ConvexTriangleMesh3f transform(Mat4f t, ConvexTriangleMesh3f res)
	{	
		if(res == null) res = new ConvexTriangleMesh3f();

		List<Triangle3f> tr = new ArrayList<>();
		
		for(int i = 0; i < this.triangles.size(); i++)
		{
			tr.add(this.triangles.get(i).transform(t, null));
		}
		
		return res.set(tr);
	}

}
