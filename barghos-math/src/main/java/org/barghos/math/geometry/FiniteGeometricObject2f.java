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
import org.barghos.core.util.Nullable;
import org.barghos.math.BarghosMath;
import org.barghos.math.matrix.Mat3f;
import org.barghos.math.point.Point2f;
import org.barghos.math.utils.api.ITransform2f;

/**
 * @author picatrix1899
 *
 */
public interface FiniteGeometricObject2f
{
	Point2f[] getPoints();

	default Point2f[] getTransformedPoints(ITransform2f t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		Point2f[] p = getPoints();

		Mat3f m = Mat3f.transform2D(t);
		
		for(int i = 0; i < p.length; i++)
			m.transform(p[i]);

		return p;
	}

	default PointSet2f getPointSet()
	{
		return new PointSet2f(getPoints());
	}

	default PointSet2f getPointSet(@Nullable PointSet2f res)
	{
		res = res != null ? res : new PointSet2f();

		return res.set(getPoints());
	}

	default PointSet2f getTransformedPointSet(ITransform2f t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}

		return getTransformedPointSet(t, new PointSet2f());
	}

	default PointSet2f getTransformedPointSet(ITransform2f t, @Nullable PointSet2f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		if(res == null) res = new PointSet2f();

		getPointSet(res);

		res.transform(t, res);

		return res;
	}
}
