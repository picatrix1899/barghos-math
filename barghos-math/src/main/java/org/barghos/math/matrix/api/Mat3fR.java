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

package org.barghos.math.matrix.api;

import java.nio.FloatBuffer;

import org.barghos.core.tuple2.api.Tup2fR;
import org.barghos.core.tuple2.api.Tup2fW;
import org.barghos.core.tuple3.Tup3f;
import org.barghos.core.tuple3.api.Tup3fR;
import org.barghos.core.tuple3.api.Tup3fW;
import org.barghos.core.tuple3.pool.Tup3fPool;
import org.barghos.math.matrix.MatUtils;
import org.barghos.math.utils.LinearSystem3;

/**
 * @author picatrix1899
 *
 */
public interface Mat3fR
{
	Tup3fR getRow(int index);
	
	Tup3fR getColumn(int index);
	
	float getCell(int row, int column);
	
	<T extends Tup3fW> T getRow(int index, T res);
	
	<T extends Tup3fW> T getColumn(int index, T res);
	
	default float determinant()
	{
		Tup3f r0 = getRow(0, Tup3fPool.get());
		Tup3f r1 = getRow(1, Tup3fPool.get());
		Tup3f r2 = getRow(2, Tup3fPool.get());
		
		float det = MatUtils.det3x3f(r0, r1, r2);
		
		Tup3fPool.store(r0, r1, r2);
		
		return det;
	}
	
	<T extends Mat3fW> T mul(Mat3fR r, T res);
	
	<T extends Tup3fR & Tup3fW> T transform(T r);
	
	<T extends Tup3fW> T transform(Tup3fR r, T res);
	
	<T extends Tup2fR & Tup2fW> T transform(T r, boolean useLastColumn);
	
	<T extends Tup2fW> T transform(Tup2fR r, boolean useLastColumn, T res);
	
	LinearSystem3 transform(LinearSystem3 system);

	LinearSystem3 transform(LinearSystem3 system, LinearSystem3 res);
	
	boolean isZeroMatrix();
	
	boolean isZeroMatrix(float tr);
	
	boolean isIdentityMatrix();
	
	boolean isIdentityMatrix(float tr);
	
	boolean isRotationMatrix();
	
	boolean isRotationMatrix(float tr);
	
	FloatBuffer toBufferColumnMajor(FloatBuffer res);

	FloatBuffer toBufferRowMajor(FloatBuffer res);

	float[] toArrayColumnMajor();

	float[] toArrayRowMajor();
}
