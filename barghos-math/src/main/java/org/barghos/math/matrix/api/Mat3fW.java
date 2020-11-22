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

import org.barghos.core.tuple2.api.Tup2fR;
import org.barghos.core.tuple3.api.Tup3fR;

/**
 * @author picatrix1899
 *
 */
public interface Mat3fW
{
	Mat3fW setRow(int index, Tup3fR t);
	
	Mat3fW setRow(int index, Tup2fR t, float z);
	
	Mat3fW setRow(int index, float x, Tup2fR t);
	
	Mat3fW setRow(int index, float x, float y, float z);

	Mat3fW setColumn(int index, Tup3fR t);
	
	Mat3fW setColumn(int index, Tup2fR t, float z);
	
	Mat3fW setColumn(int index, float x, Tup2fR t);
	
	Mat3fW setColumn(int index, float x, float y, float z);
	
	Mat3fW setCell(int row, int column, float value);
}
