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

package org.barghos.math.vector.vec4;

import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.pool.DequePool;
import org.barghos.core.pool.api.IPool;
import org.barghos.core.tuple4.api.Tup4fR;

public final class Vec4Pool
{
	private static IPool<Vec4> pool = new DequePool<>(Vec4.class);
	
	private Vec4Pool() { }
	
	public static Vec4 get() { return pool.get().set(0.0f, 0.0f, 0.0f, 0.0f); }
	public static Vec4 get(Tup4fR v) { if(v == null) throw new ArgumentNullException("v"); return pool.get().set(v); }
	public static Vec4 get(float x, float y, float z, float w) { return pool.get().set(x, y, z, w); }
	
	public static void ensure(int count) { if(count < 0) throw new IllegalArgumentException(); pool.ensure(count); }
	
	public static void store(Vec4... instances) { pool.store(instances); }
	
	public static void setInternalPool(IPool<Vec4> pool) { if(pool == null) throw new ArgumentNullException("pool"); Vec4Pool.pool = pool; }
	public static IPool<Vec4> getInternalPool() { return pool; }
}
