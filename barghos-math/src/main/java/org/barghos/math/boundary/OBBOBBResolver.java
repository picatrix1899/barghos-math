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

import org.barghos.core.tuple4.Tup4f;
import org.barghos.core.tuple4.pool.Tup4fPool;
import org.barghos.math.geometry.PointSet3f;
import org.barghos.math.matrix.Mat4;
import org.barghos.math.vec3.Vec3f;
import org.barghos.math.vec3.pool.Vec3fPool;

public class OBBOBBResolver
{
	public static boolean iOBBOBB3f(OBB3f a, OBB3f b)
	{
		Vec3f aCenter = a.getCenter(Vec3fPool.get());
		Vec3f bCenter = b.getCenter(Vec3fPool.get());

		Mat4 modelSpaceA = a.getModelSpaceMatrix();
		Mat4 modelSpaceB = b.getModelSpaceMatrix();
		
		PointSet3f aInSpaceA = a.getTransformedPointSet(modelSpaceA);
		PointSet3f bInSpaceA = b.getTransformedPointSet(modelSpaceA);

		float aMinX = aInSpaceA.getMinX();
		float aMaxX = aInSpaceA.getMaxX();

		float bMinX = bInSpaceA.getMinX();
		float bMaxX = bInSpaceA.getMaxX();

		float aMinY = aInSpaceA.getMinY();
		float aMaxY = aInSpaceA.getMaxY();

		float bMinY = bInSpaceA.getMinY();
		float bMaxY = bInSpaceA.getMaxY();

		float aMinZ = aInSpaceA.getMinZ();
		float aMaxZ = aInSpaceA.getMaxZ();

		float bMinZ = bInSpaceA.getMinZ();
		float bMaxZ = bInSpaceA.getMaxZ();
		
		Tup4f t1 = modelSpaceA.getRow(0, Tup4fPool.get());
		Tup4f t2 = modelSpaceA.getRow(1, Tup4fPool.get());
		Tup4f t3 = modelSpaceA.getRow(2, Tup4fPool.get());
		
		float aPNX = aCenter.dot(t1);
		float bPNX = bCenter.dot(t1);
		float aPNY = aCenter.dot(t2);
		float bPNY = bCenter.dot(t2);
		float aPNZ = aCenter.dot(t3);
		float bPNZ = bCenter.dot(t3);

		if(aPNX < bPNX)
			if(!(aMaxX > bMinX))
				return false;
		else
			if(!(bMaxX > aMinX))
				return false;
		
		if(aPNY < bPNY)
			if(!(aMaxY > bMinY))
				return false;
		else
			if(!(bMaxY > aMinY))
				return false;
		
		if(aPNZ < bPNZ)
			if(!(aMaxZ > bMinZ))
				return false;
		else
			if(!(bMaxZ > aMinZ))
				return false;

		PointSet3f aInSpaceB = a.getTransformedPointSet(modelSpaceB);
		PointSet3f bInSpaceB = b.getTransformedPointSet(modelSpaceB);
		
		aMinX = aInSpaceB.getMinX();
		aMaxX = aInSpaceB.getMaxX();
		
		bMinX = bInSpaceB.getMinX();
		bMaxX = bInSpaceB.getMaxX();
		
		aMinY = aInSpaceB.getMinY();
		aMaxY = aInSpaceB.getMaxY();
		
		bMinY = bInSpaceB.getMinY();
		bMaxY = bInSpaceB.getMaxY();
		
		aMinZ = aInSpaceB.getMinZ();
		aMaxZ = aInSpaceB.getMaxZ();
		
		bMinZ = bInSpaceB.getMinZ();
		bMaxZ = bInSpaceB.getMaxZ();
		
		modelSpaceB.getRow(0, t1);
		modelSpaceB.getRow(1, t2);
		modelSpaceB.getRow(2, t3);
		
		aPNX = aCenter.dot(t1);
		bPNX = bCenter.dot(t1);
		aPNY = aCenter.dot(t2);
		bPNY = bCenter.dot(t2);
		aPNZ = aCenter.dot(t3);
		bPNZ = bCenter.dot(t3);
		
		if(aPNX < bPNX)
			if(!(aMaxX > bMinX))
				return false;
		else
			if(!(bMaxX > aMinX))
				return false;
		
		if(aPNY < bPNY)
			if(!(aMaxY > bMinY))
				return false;
		else
			if(!(bMaxY > aMinY))
				return false;
		
		if(aPNZ < bPNZ)
			if(!(aMaxZ > bMinZ))
				return false;
		else
			if(!(bMaxZ > aMinZ))
				return false;
		
		Vec3fPool.store(aCenter, bCenter);
		Tup4fPool.store(t1, t2, t3);
		
		return true;
	}
	
	public static Vec3f rOBBOBB3f(OBB3f a, OBB3f b)
	{
		Vec3f aCenter = a.getCenter(Vec3fPool.get());
		Vec3f bCenter = b.getCenter(Vec3fPool.get());
		
		Mat4 modelSpaceA = a.getModelSpaceMatrix();

		PointSet3f aInSpaceA = a.getTransformedPointSet(modelSpaceA);
		PointSet3f bInSpaceA = b.getTransformedPointSet(modelSpaceA);
		
		float aMinX = aInSpaceA.getMinX();
		float aMaxX = aInSpaceA.getMaxX();
		
		float bMinX = bInSpaceA.getMinX();
		float bMaxX = bInSpaceA.getMaxX();
		
		float aMinY = aInSpaceA.getMinY();
		float aMaxY = aInSpaceA.getMaxY();
		
		float bMinY = bInSpaceA.getMinY();
		float bMaxY = bInSpaceA.getMaxY();
		
		float aMinZ = aInSpaceA.getMinZ();
		float aMaxZ = aInSpaceA.getMaxZ();
		
		float bMinZ = bInSpaceA.getMinZ();
		float bMaxZ = bInSpaceA.getMaxZ();

		Tup4f t1 = modelSpaceA.getRow(0, Tup4fPool.get());
		Tup4f t2 = modelSpaceA.getRow(1, Tup4fPool.get());
		Tup4f t3 = modelSpaceA.getRow(2, Tup4fPool.get());
		
		float aPNX = aCenter.dot(t1);
		float bPNX = bCenter.dot(t1);
		float aPNY = aCenter.dot(t2);
		float bPNY = bCenter.dot(t2);
		float aPNZ = aCenter.dot(t3);
		float bPNZ = bCenter.dot(t3);
		
		float valAX = 0;
		int signAX = 0;
		float valAY = 0;
		int signAY = 0;
		float valAZ = 0;
		int signAZ = 0;
		float valBX = 0;
		int signBX = 0;
		float valBY = 0;
		int signBY = 0;
		float valBZ = 0;
		int signBZ = 0;
		
		if(aPNX < bPNX)
			if(aMaxX > bMinX)
			{
				valAX = aMaxX - bMinX;
				signAX = -1;
			}
			else
				return new Vec3f();
		else
			if(bMaxX > aMinX)
			{
				valAX = bMaxX - aMinX;
				signAX = 1;
			}
			else
				return new Vec3f();
		
		if(aPNY < bPNY)
			if(aMaxY > bMinY)
			{
				valAY = aMaxY - bMinY;
				signAY = -1;
			}
			else
				return new Vec3f();
		else
			if(bMaxY > aMinY)
			{
				valAY = bMaxY - aMinY;
				signAY = 1;
			}
			else
				return new Vec3f();
		
		if(aPNZ < bPNZ)
			if(aMaxZ > bMinZ)
			{
				valAZ = aMaxZ - bMinZ;
				signAZ = -1;
			}
			else
				return new Vec3f();
		else
			if(bMaxZ > aMinZ)
			{
				valAZ = bMaxZ - aMinZ;
				signAZ = 1;
			}
			else
				return new Vec3f();
		
		Mat4 modelSpaceB = b.getModelSpaceMatrix();
		
		PointSet3f aInSpaceB = a.getTransformedPointSet(modelSpaceB);
		PointSet3f bInSpaceB = b.getTransformedPointSet(modelSpaceB);
		
		aMinX = aInSpaceB.getMinX();
		aMaxX = aInSpaceB.getMaxX();
		
		bMinX = bInSpaceB.getMinX();
		bMaxX = bInSpaceB.getMaxX();
		
		aMinY = aInSpaceB.getMinY();
		aMaxY = aInSpaceB.getMaxY();
		
		bMinY = bInSpaceB.getMinY();
		bMaxY = bInSpaceB.getMaxY();
		
		aMinZ = aInSpaceB.getMinZ();
		aMaxZ = aInSpaceB.getMaxZ();
		
		bMinZ = bInSpaceB.getMinZ();
		bMaxZ = bInSpaceB.getMaxZ();
		
		modelSpaceB.getRow(0, t1);
		modelSpaceB.getRow(1, t2);
		modelSpaceB.getRow(2, t3);
		
		aPNX = aCenter.dot(t1);
		bPNX = bCenter.dot(t1);
		aPNY = aCenter.dot(t2);
		bPNY = bCenter.dot(t2);
		aPNZ = aCenter.dot(t3);
		bPNZ = bCenter.dot(t3);
		
		if(aPNX < bPNX)
			if(aMaxX > bMinX)
			{
				valBX = aMaxX - bMinX;
				signBX = -1;
			}
			else
				return new Vec3f();
		else
			if(bMaxX > aMinX)
			{
				valBX = bMaxX - aMinX;
				signBX = 1;
			}
			else
				return new Vec3f();

		if(aPNY < bPNY)
			if(aMaxY > bMinY)
			{
				valBY = aMaxY - bMinY;
				signBY = -1;
			}
			else
				return new Vec3f();
		else
			if(bMaxY > aMinY)
			{
				valBY = bMaxY - aMinY;
				signBY = 1;
			}
			else
				return new Vec3f();
		
		if(aPNZ < bPNZ)
			if(aMaxZ > bMinZ)
			{
				valBZ = aMaxZ - bMinZ;
				signBZ = -1;
			}
			else
				return new Vec3f();
		else
			if(bMaxZ > aMinZ)
			{
				valBZ = bMaxZ - aMinZ;
				signBZ = 1;
			}
			else
				return new Vec3f();
		
		Vec3f[] v = new Vec3f[6];

		Vec3f axisAX = new Vec3f(modelSpaceA.getRow(0).getX(), modelSpaceA.getRow(0).getY(), modelSpaceA.getRow(0).getZ());
		Vec3f axisAY = new Vec3f(modelSpaceA.getRow(1).getX(), modelSpaceA.getRow(1).getY(), modelSpaceA.getRow(1).getZ());
		Vec3f axisAZ = new Vec3f(modelSpaceA.getRow(2).getX(), modelSpaceA.getRow(2).getY(), modelSpaceA.getRow(2).getZ());
		Vec3f axisBX = new Vec3f(modelSpaceB.getRow(0).getX(), modelSpaceB.getRow(0).getY(), modelSpaceB.getRow(0).getZ());
		Vec3f axisBY = new Vec3f(modelSpaceB.getRow(1).getX(), modelSpaceB.getRow(1).getY(), modelSpaceB.getRow(1).getZ());
		Vec3f axisBZ = new Vec3f(modelSpaceB.getRow(2).getX(), modelSpaceB.getRow(2).getY(), modelSpaceB.getRow(2).getZ());
		
		v[0] = axisAX.mul(valAX * signAX);
		v[1] = axisAY.mul(valAY * signAY);
		v[2] = axisAZ.mul(valAZ * signAZ);
		
		v[3] = axisBX.mul(valBX * signBX);
		v[4] = axisBY.mul(valBY * signBY);
		v[5] = axisBZ.mul(valBZ * signBZ);
		
		int index = 0;
		double min = Double.MAX_VALUE;
		
		for(int i = 0; i < 6; i++)
		{
			Vec3f c = v[i];
			double sq = c.squaredLength();
			
			if(sq < min)
			{
				min = sq;
				index = i;
			}
		}
		
		Vec3fPool.store(aCenter, bCenter);
		Tup4fPool.store(t1, t2, t3);
		
		return v[index];
	}
}
