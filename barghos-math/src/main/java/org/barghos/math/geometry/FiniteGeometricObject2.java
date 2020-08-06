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
import org.barghos.math.point.Point2;

/**
 * @author picatrix1899
 *
 */
public interface FiniteGeometricObject2
{
	Point2[] getPoints();

	default Point2[] getTransformedPoints(Mat4 t)
	{
		Point2[] p = getPoints();

		int i = 0;
		for(; i < p.length; i++)
			t.transform(p[i], p[i]);

		return p;
	}

	default PointSet2 getPointSet()
	{
		return new PointSet2(getPoints());
	}

	default PointSet2 getPointSet(PointSet2 res)
	{
		res = res != null ? res : new PointSet2();

		return res.set(getPoints());
	}

	default PointSet2 getTransformedPointSet(Mat4 t)
	{
		PointSet2 res = new PointSet2();

		PointSet2 s = getPointSet();

		s.transform(t, s);

		return res.set(s);
	}

	default PointSet2 getTransformedPointSet(Mat4 t, PointSet2 res)
	{
		if(res == null) res = new PointSet2();

		getPointSet(res);

		res.transform(t, res);

		return res;
	}
}
