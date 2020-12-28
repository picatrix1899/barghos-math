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

package org.barghos.math.vec3.api;

import org.barghos.core.tuple3.api.Tup3fR;
import org.barghos.core.tuple3.api.Tup3fRW;

/**
 * @author picatrix1899
 *
 */
public interface Vec3fRW extends Vec3fR, Vec3fW, Tup3fRW
{
	@Override
	Vec3fRW setX(float x);

	@Override
	Vec3fRW setY(float y);

	@Override
	Vec3fRW setZ(float z);

	@Override
	Vec3fRW set(Tup3fR t);
	
	@Override
	Vec3fRW set(float value);
	
	@Override
	Vec3fRW set(float x, float y, float z);
	
	@Override
	Vec3fRW add(Tup3fR t);
	
	@Override
	Vec3fRW add(float scalar);
	
	@Override
	Vec3fRW add(float x, float y, float z);
	
	@Override
	Vec3fRW addN(Tup3fR t);
	
	@Override
	Vec3fRW addN(float scalar);
	
	@Override
	Vec3fRW addN(float x, float y, float z);
	
	@Override
	Vec3fRW sub(Tup3fR t);
	
	@Override
	Vec3fRW sub(float scalar);
	
	@Override
	Vec3fRW sub(float x, float y, float z);
	
	@Override
	Vec3fRW subN(Tup3fR t);
	
	@Override
	Vec3fRW subN(float scalar);
	
	@Override
	Vec3fRW subN(float x, float y, float z);
	
	@Override
	Vec3fRW mul(Tup3fR t);
	
	@Override
	Vec3fRW mul(float scalar);
	
	@Override
	Vec3fRW mul(float x, float y, float z);
	
	@Override
	Vec3fRW mulN(Tup3fR t);
	
	@Override
	Vec3fRW mulN(float scalar);
	
	@Override
	Vec3fRW mulN(float x, float y, float z);
	
	@Override
	Vec3fRW div(Tup3fR t);
	
	@Override
	Vec3fRW div(float scalar);
	
	@Override
	Vec3fRW div(float x, float y, float z);
	
	@Override
	Vec3fRW divN(Tup3fR t);
	
	@Override
	Vec3fRW divN(float scalar);
	
	@Override
	Vec3fRW divN(float x, float y, float z);
	
	@Override
	Vec3fRW normal();
	
	@Override
	Vec3fRW normalN();
	
	@Override
	Vec3fRW normalSafe();
	
	@Override
	Vec3fRW normalSafe(float tolerance);
	
	@Override
	Vec3fRW normalSafeN();
	
	@Override
	Vec3fRW normalSafeN(float tolerance);
	
	@Override
	Vec3fRW invert();

	@Override
	Vec3fRW invertN();
	
	@Override
	Vec3fRW cross(Tup3fR t);
	
	@Override
	Vec3fRW cross(float x, float y, float z);

	@Override
	Vec3fRW crossN(Tup3fR t);
	
	@Override
	Vec3fRW crossN(float x, float y, float z);
	
	@Override
	Vec3fRW snapToGrid(Tup3fR grid);
	
	@Override
	Vec3fRW snapToGrid(float scalar);
	
	@Override
	Vec3fRW snapToGrid(float gx, float gy, float gz);
	
	@Override
	Vec3fRW snapToGridN(Tup3fR grid);
	
	@Override
	Vec3fRW snapToGridN(float scalar);
	
	@Override
	Vec3fRW snapToGridN(float gx, float gy, float gz);
}
