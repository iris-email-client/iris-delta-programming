package br.unb.cic.iris.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class BaseManager<T> {
	private Map<String, Integer> map = Collections
			.synchronizedMap(new HashMap<String, Integer>());
	private List<T> list = new Vector<>();

	public void add(String key, T value) {
		synchronized (list) {
			map.put(key, list.size());
			list.add(value);
		}
	}

	public T get(String key) {
		return list.get(map.get(key));
	}

	public List<T> getAll() {
		return Collections.unmodifiableList(list);
	}

	public void clear() {
		map = Collections.synchronizedMap(new HashMap<String, Integer>());
		list = new Vector<>();
	}

	public void print() {
		// list.forEach(n -> System.out.println(n));
		//list.forEach(System.out::println);
		for(T t: list){
			System.out.println(t);
		}
	}
}
