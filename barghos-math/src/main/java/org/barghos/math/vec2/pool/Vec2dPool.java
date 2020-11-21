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

package org.barghos.math.vec2.pool;

import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.pool.DequePool;
import org.barghos.core.pool.api.Pool;
import org.barghos.core.tuple2.api.Tup2dR;
import org.barghos.math.BarghosMath;
import org.barghos.math.vec2.Vec2d;

/**
 * This specialized instance pool contains instances of the type {@link Vec2d}.
 * 
 * @author picatrix1899
 * 
 * @since 1.0
 */
public final class Vec2dPool
{
	/**
	 * This variable contains the internal pool that is backing this specialized pool.
	 */
	private static Pool<Vec2d> pool = new DequePool<>(Vec2d.class);
	
	/**
	 * This class contains only static methods and therefore it should not be possible to create
	 * instances from it.
	 */
	private Vec2dPool() { }
	
	/**
	 * Returns an instance of {@link Vec2d} from the pool and does not reset it.
	 * This function is useful for reducing unneccessary calls and operations if a value is
	 * applied to to the tuple anyway before it is used.
	 * 
	 * @return A stored instance.
	 * 
	 * @since 1.0
	 */
	public static Vec2d getPlain()
	{
		return pool.get();
	}
	
	/**
	 * Returns an instance of {@link Vec2d} from the pool and resets it.
	 * 
	 * @return A stored instance with the components set to 0.0.
	 * 
	 * @since 1.0
	 */
	public static Vec2d get()
	{
		return pool.get().set(0.0);
	}
	
	/**
	 * Returns an instance of {@link Vec2d} from the pool and sets its components to the values of t.
	 * 
	 * @param t A tuple that is used as initial values of the returned tuple.
	 * 
	 * @return A stored instance.
	 * 
	 * @since 1.0
	 */
	public static Vec2d get(Tup2dR v)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(v == null) throw new ArgumentNullException("v");
		}
		
		return pool.get().set(v);
	}
	
	/**
	 * Returns an instance of {@link Vec2d} from the pool and sets its components to x and y.
	 * 
	 * @param x The x component.
	 * @param y The y component.
	 * 
	 * @return A stored instance.
	 * 
	 * @since 1.0
	 */
	public static Vec2d get(float x, float y)
	{
		return pool.get().set(x, y);
	}
	
	/**
	 * Ensures a certain amount of instances to be present in the pool at any time.
	 * A call to this method will eventually cause the pool to create instances to fullfill the ensured amount.
	 * 
	 * @param count The amount of instances present in the pool at any time.
	 * 
	 * @since 1.0
	 */
	public static void ensure(int count)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(count < 0) throw new IllegalArgumentException();
		}
		
		pool.ensure(count);
	}
	
	/**
	 * Stores {@link Vec2d} instances in the pool for later reuse.
	 * 
	 * @param elements The instances to store.
	 * 
	 * @since 1.0
	 */
	public static void store(Vec2d... instances)
	{
		pool.store(instances);
	}
	
	/** 
	 * Sets the internal used pool. This can be used for replacing the default pool
	 * by a more efficient pool or a debuggable pool.
	 * 
	 * @param pool The new pool instance the specialized {@link Vec2dPool} should use internal.
	 * 
	 * @since 1.0
	 */
	public static void setInternalPool(Pool<Vec2d> pool)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pool == null) throw new ArgumentNullException("pool");
		}
		
		Vec2dPool.pool = pool;
	}
	
	/**
	 * Returns the internal used pool instance.
	 * 
	 * @return The internal used pool instance of the specialized pool {@link Vec2dPool}.
	 * 
	 * @since 1.0
	 */
	public static Pool<Vec2d> getInternalPool()
	{
		return pool;
	}
}
