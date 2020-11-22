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

import org.barghos.core.tuple3.api.Tup3fR;
import org.barghos.core.tuple3.api.Tup3fW;
import org.barghos.core.tuple4.Tup4f;
import org.barghos.core.tuple4.api.Tup4fR;
import org.barghos.core.tuple4.api.Tup4fW;
import org.barghos.core.tuple4.pool.Tup4fPool;
import org.barghos.math.matrix.MatUtils;

/**
 * @author picatrix1899
 *
 */
public interface Mat4fR
{
	Tup4fR getRow(int index);
	
	Tup4fR getColumn(int index);
	
	float getCell(int row, int column);
	
	<T extends Tup4fW> T getRow(int index, T res);
	
	<T extends Tup4fW> T getColumn(int index, T res);
	
	default float determinant()
	{
		Tup4f r0 = getRow(0, Tup4fPool.get());
		Tup4f r1 = getRow(1, Tup4fPool.get());
		Tup4f r2 = getRow(2, Tup4fPool.get());
		Tup4f r3 = getRow(3, Tup4fPool.get());
		
		float det = MatUtils.det4x4f(r0, r1, r2, r3);
		
		Tup4fPool.store(r0, r1, r2, r3);
		
		return det;
	}
	
	<T extends Mat4fW> T mul(Mat4fR r, T res);
	
	<T extends Tup4fR & Tup4fW> T transform(T r);
	
	<T extends Tup4fW> T transform(Tup4fR r, T res);
	
	<T extends Tup3fR & Tup3fW> T transform(T r, boolean useLastColumn);
	
	<T extends Tup3fW> T transform(Tup3fR r, boolean useLastColumn, T res);
	
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
