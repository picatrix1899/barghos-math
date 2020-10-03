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

package org.barghos.math.vector.vec2.pool;

import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.pool.DequePool;
import org.barghos.core.pool.api.IPool;
import org.barghos.core.tuple2.api.Tup2dR;
import org.barghos.math.vector.vec2.Vec2d;


public final class Vec2dPool
{
	private static IPool<Vec2d> pool = new DequePool<>(Vec2d.class);
	
	private Vec2dPool() { }
	
	public static Vec2d get() { return pool.get().set(0.0, 0.0); }
	public static Vec2d get(Tup2dR v) { if(v == null) throw new ArgumentNullException("v"); return pool.get().set(v); }
	public static Vec2d get(float x, float y) { return pool.get().set(x, y); }
	
	public static void ensure(int count) { if(count < 0) throw new IllegalArgumentException(); pool.ensure(count); }
	
	public static void store(Vec2d... instances) { pool.store(instances); }
	
	public static void setInternalPool(IPool<Vec2d> pool) { if(pool == null) throw new ArgumentNullException("pool"); Vec2dPool.pool = pool; }
	public static IPool<Vec2d> getInternalPool() { return pool; }
}
