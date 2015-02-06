package VariableLocker;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
public class VariableLocker<T> {
	private final ReentrantReadWriteLock lock;
	private final Lock readLock;
	private final Lock writeLock;
	private T t_variable;
	public VariableLocker(T t_variable){
		lock = new ReentrantReadWriteLock();
		readLock = lock.readLock();
		writeLock = lock.writeLock();
		this.t_variable = t_variable;
	}
	public T get() {
		readLock.lock();
		try {
			return t_variable;
		}
		finally {
			readLock.unlock();
		}
	}
	public void set(T t_variable) {
		writeLock.lock();
		try {
			this.t_variable = t_variable;
		}
		finally {
			writeLock.unlock();
		}
	}
}