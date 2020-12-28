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

import org.barghos.core.tuple3.api.Tup3fW;
/**
 * This interface grants writeonly access to a 3-dimensional mathematical float vector.
 * 
 * @author picatrix1899
 * 
 * @since 1.0.0.0
 */
public interface Vec3fW extends Tup3fW
{

	@Override
	Vec3fW setX(float x);

	@Override
	Vec3fW setY(float y);

	@Override
	Vec3fW setZ(float z);

	@Override
	Vec3fW set(Tup3fR t);
	
	@Override
	Vec3fW set(float value);
	
	@Override
	Vec3fW set(float x, float y, float z);
	
	Vec3fW add(Tup3fR t);
	Vec3fW add(float scalar);
	Vec3fW add(float x, float y, float z);
	
	<T extends Tup3fW> T add(Tup3fR t, T res);
	<T extends Tup3fW> T add(float scalar, T res);
	<T extends Tup3fW> T add(float x, float y, float z, T res);
	
	Vec3fW addN(Tup3fR t);
	Vec3fW addN(float scalar);
	Vec3fW addN(float x, float y, float z);
	
	Vec3fW sub(Tup3fR t);
	Vec3fW sub(float scalar);
	Vec3fW sub(float x, float y, float z);
	
	<T extends Tup3fW> T sub(Tup3fR t, T res);
	<T extends Tup3fW> T sub(float scalar, T res);
	<T extends Tup3fW> T sub(float x, float y, float z, T res);
	
	Vec3fW subN(Tup3fR t);
	Vec3fW subN(float scalar);
	Vec3fW subN(float x, float y, float z);
	
	Vec3fW mul(Tup3fR t);
	Vec3fW mul(float scalar);
	Vec3fW mul(float x, float y, float z);
	
	<T extends Tup3fW> T mul(Tup3fR t, T res);
	<T extends Tup3fW> T mul(float scalar, T res);
	<T extends Tup3fW> T mul(float x, float y, float z, T res);
	
	Vec3fW mulN(Tup3fR t);
	Vec3fW mulN(float scalar);
	Vec3fW mulN(float x, float y, float z);
	
	Vec3fW div(Tup3fR t);
	Vec3fW div(float scalar);
	Vec3fW div(float x, float y, float z);
	
	<T extends Tup3fW> T div(Tup3fR t, T res);
	<T extends Tup3fW> T div(float scalar, T res);
	<T extends Tup3fW> T div(float x, float y, float z, T res);
	
	Vec3fW divN(Tup3fR t);
	Vec3fW divN(float scalar);
	Vec3fW divN(float x, float y, float z);
	
	Vec3fW normal();
	
	<T extends Tup3fW> T normal(T res);
	
	Vec3fW normalN();
	
	Vec3fW normalSafe();
	Vec3fW normalSafe(float tolerance);
	
	<T extends Tup3fW> T normalSafe(T res);
	<T extends Tup3fW> T normalSafe(float tolerance, T res);
	
	Vec3fW normalSafeN();
	Vec3fW normalSafeN(float tolerance);
	
	Vec3fW invert();
	
	<T extends Tup3fW> T invert(T res);

	Vec3fW invertN();
	
	Vec3fW cross(Tup3fR t);
	Vec3fW cross(float x, float y, float z);

	<T extends Tup3fW> T cross(Tup3fR t, T res);
	<T extends Tup3fW> T cross(float x, float y, float z, T res);

	Vec3fW crossN(Tup3fR t);
	Vec3fW crossN(float x, float y, float z);
	
	Vec3fW snapToGrid(Tup3fR grid);
	Vec3fW snapToGrid(float scalar);
	Vec3fW snapToGrid(float gx, float gy, float gz);

	<T extends Tup3fW> T snapToGrid(Tup3fR grid, T res);
	<T extends Tup3fW> T snapToGrid(float scalar, T res);
	<T extends Tup3fW> T snapToGrid(float gx, float gy, float gz, T res);
	
	Vec3fW snapToGridN(Tup3fR grid);
	Vec3fW snapToGridN(float scalar);
	Vec3fW snapToGridN(float gx, float gy, float gz);
}
