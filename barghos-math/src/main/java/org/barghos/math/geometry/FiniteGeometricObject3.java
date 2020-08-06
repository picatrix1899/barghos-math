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

import org.barghos.math.matrix.Mat4;
import org.barghos.math.point.Point3;

public interface FiniteGeometricObject3
{
	Point3[] getPoints();
	
	default Point3[] getTransformedPoints(Mat4 t)
	{
		Point3[] p = getPoints();
		
		int i = 0;
		for(; i < p.length; i++)
			t.transform(p[i], p[i]);
		
		return p;
	}
	
	default PointSet3 getPointSet()
	{
		return new PointSet3(getPoints());
	}
	
	default PointSet3 getPointSet(PointSet3 res)
	{
		res = res != null ? res : new PointSet3();

		return res.set(getPoints());
	}
	
	default PointSet3 getTransformedPointSet(Mat4 t)
	{
		PointSet3 s = getPointSet();

		return s.transform(t, null);
	}
	
	default PointSet3 getTransformedPointSet(Mat4 t, PointSet3 res)
	{
		res = res != null ? res : new PointSet3();
		
		getPointSet(res);
		
		res.transform(t, res);
		
		return res;
	}

}
