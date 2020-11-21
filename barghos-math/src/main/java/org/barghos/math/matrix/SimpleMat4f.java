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

package org.barghos.math.matrix;

import org.barghos.core.tuple2.api.Tup2fR;
import org.barghos.core.tuple2.api.Tup2fW;
import org.barghos.core.tuple3.api.Tup3fR;
import org.barghos.core.tuple3.api.Tup3fW;
import org.barghos.core.tuple4.Tup4f;
import org.barghos.core.tuple4.api.Tup4fR;
import org.barghos.core.tuple4.api.Tup4fW;

import org.barghos.math.matrix.api.Mat4fR;
import org.barghos.math.matrix.api.Mat4fW;

/**
 * @author picatrix1899
 *
 */
public class SimpleMat4f implements Mat4fR, Mat4fW
{

	/*
	 *
	 */
	public Tup4f getRow(int index)
	{
		return null;
	}

	/*
	 *
	 */
	public Tup4f getColumn(int index)
	{
		return null;
	}

	/*
	 *
	 */
	public float getCell(int row, int column)
	{
		return 0;
	}

	/*
	 *
	 */
	public <T extends Tup4fW> T getRow(int index, T res)
	{
		return null;
	}

	/*
	 *
	 */
	public <T extends Tup4fW> T getColumn(int index, T res)
	{
		return null;
	}

	/*
	 *
	 */
	public float determinant()
	{
		return 0;
	}

	/*
	 *
	 */
	public Mat4f mul(Mat4f r, Mat4f res)
	{
		return null;
	}

	/*
	 *
	 */
	public <T extends Tup4fR & Tup4fW> T transform(T r)
	{
		return null;
	}

	/*
	 *
	 */
	public <T extends Tup3fR & Tup3fW> T transform(T r)
	{
		return null;
	}

	/*
	 *
	 */
	public <T extends Tup4fW> T transform(Tup4fR r, T res)
	{
		return null;
	}

	/*
	 *
	 */
	public <T extends Tup3fW> T transform(Tup3fR r, T res)
	{
		return null;
	}

	/*
	 *
	 */
	public <T extends Tup2fW> T transform(Tup2fR r, T res)
	{
		return null;
	}

}
