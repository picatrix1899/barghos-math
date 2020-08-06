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

package org.barghos.math.helper;

import org.barghos.core.tuple3.Tup3fHelper;
import org.barghos.math.geometry.AABB3;
import org.barghos.math.vector.vec3.Vec3;
import org.barghos.math.vector.vec3.Vec3Pool;

/**
 * @author picatrix1899
 *
 */
public class AABB3Helper
{
	public static AABB3 merge(AABB3 a, AABB3 b)
	{
		Vec3 minA = a.getMin(Vec3Pool.get());
		Vec3 maxA = a.getMax(Vec3Pool.get());
		Vec3 minB = b.getMin(Vec3Pool.get());
		Vec3 maxB = b.getMax(Vec3Pool.get());
		
		Vec3 min = Tup3fHelper.min(minA, minB, minA);
		Vec3 max = Tup3fHelper.max(maxA, maxB, maxA);

		Vec3 he = Vec3Pool.get();
		max.sub(min, he).mul(0.5f);
		
		Vec3 center = Vec3Pool.get();
		min.add(he, center);
		
		AABB3 out = new AABB3(center, he);
		
		Vec3Pool.store(minA, maxA, minB, maxB, he, center);
		
		return out;
	}
	
	public static AABB3 merge(AABB3 a, AABB3 b, AABB3 res)
	{
		Vec3 minA = a.getMin(Vec3Pool.get());
		Vec3 maxA = a.getMax(Vec3Pool.get());
		Vec3 minB = b.getMin(Vec3Pool.get());
		Vec3 maxB = b.getMax(Vec3Pool.get());
		
		Vec3 min = Tup3fHelper.min(minA, minB, minA);
		Vec3 max = Tup3fHelper.max(maxA, maxB, maxA);

		Vec3 he = Vec3Pool.get();
		max.sub(min, he).mul(0.5f);
		
		Vec3 center = Vec3Pool.get();
		min.add(he, center);

		res.set(center, he);
		
		Vec3Pool.store(minA, maxA, minB, maxB, he, center);
		
		return res;
	}
}
