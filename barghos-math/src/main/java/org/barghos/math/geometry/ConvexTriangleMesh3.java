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

import org.barghos.math.Transform3;
import org.barghos.math.matrix.Mat4;
import org.barghos.math.point.Point3;
import org.barghos.math.vector.vec3.Vec3;
import org.barghos.math.vector.vec3.Vec3Pool;

public class ConvexTriangleMesh3 implements FiniteGeometricObject3
{

	private List<Triangle3> triangles = new ArrayList<>();
	
	public ConvexTriangleMesh3()
	{
		
	}
	
	public ConvexTriangleMesh3(ConvexTriangleMesh3 mesh)
	{
		set(mesh.triangles);
	}
	
	public ConvexTriangleMesh3(List<Triangle3> triangles)
	{
		set(triangles);
	}

	public boolean isValid()
	{
		return !this.triangles.isEmpty();
	}
	
	public ConvexTriangleMesh3 set(List<Triangle3> triangles)
	{
		this.triangles.clear();
		
		for(int i = 0; i < triangles.size(); i++)
			this.triangles.add(new Triangle3(triangles.get(i)));
		
		return this;
	}

	public Point3[] getPoints()
	{
		Point3[] p = new Point3[triangles.size() * 3];
		
		for(int i = 0; i < triangles.size(); i++)
		{
			p[i * 3] = triangles.get(i).getP1(null);
			p[i * 3 + 1] = triangles.get(i).getP2(null);
			p[i * 3 + 2] = triangles.get(i).getP3(null);
		}
		
		return p;
	}
	
	public List<Triangle3> getTriangles()
	{
		return new ArrayList<>(this.triangles);
	}
	
	public OBB3 getOBBf(Mat4 t, Mat4 r)
	{
		PointSet3 set = getPointSet(null);

		Vec3 min = Vec3Pool.get(set.getMinX(), set.getMinY(), set.getMinZ());
		Vec3 max = Vec3Pool.get(set.getMaxX(), set.getMaxY(), set.getMaxZ());

		t.transform(min);
		t.transform(max);
		
		Vec3 halfExtend = max.sub(min).mul(0.5f);
		Point3 center = new Point3(min.add(halfExtend, min));

		OBB3 result = new OBB3(center, halfExtend, r);
		
		Vec3Pool.store(min, max);
		
		return result;
	}

	public AABB3 getAABBf()
	{
		PointSet3 set = getPointSet(null);
		
		Vec3 min = Vec3Pool.get(set.getMinX(), set.getMinY(), set.getMinZ());
		Vec3 max = Vec3Pool.get(set.getMaxX(), set.getMaxY(), set.getMaxZ());
		
		Vec3 halfExtend = max.sub(min).mul(0.5f);
		Point3 center = new Point3(min.add(halfExtend, min));

		AABB3 r = new AABB3(center, halfExtend);
		
		Vec3Pool.store(min, max);
		
		return r;
	}
	
	public ConvexTriangleMesh3 transform(Mat4 t, ConvexTriangleMesh3 res)
	{	
		if(res == null) res = new ConvexTriangleMesh3();

		List<Triangle3> tr = new ArrayList<>();
		
		for(int i = 0; i < this.triangles.size(); i++)
		{
			tr.add(this.triangles.get(i).transform(t, null));
		}
		
		return res.set(tr);
	}

}
