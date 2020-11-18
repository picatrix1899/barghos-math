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
import org.barghos.math.point.Point3f;

public interface FiniteGeometricObject3f
{
	Point3f[] getPoints();
	
	default Point3f[] getTransformedPoints(Mat4 t)
	{
		Point3f[] p = getPoints();
		
		int i = 0;
		for(; i < p.length; i++)
			t.transform(p[i], p[i]);
		
		return p;
	}
	
	default PointSet3f getPointSet()
	{
		return new PointSet3f(getPoints());
	}
	
	default PointSet3f getPointSet(PointSet3f res)
	{
		res = res != null ? res : new PointSet3f();

		return res.set(getPoints());
	}
	
	default PointSet3f getTransformedPointSet(Mat4 t)
	{
		PointSet3f s = getPointSet();

		return s.transform(t, null);
	}
	
	default PointSet3f getTransformedPointSet(Mat4 t, PointSet3f res)
	{
		res = res != null ? res : new PointSet3f();
		
		getPointSet(res);
		
		res.transform(t, res);
		
		return res;
	}

}
