package com.itliusir.test.jdk8.lambda;


public class TestLambda {
	
	@FunctionalInterface
	interface Converter<F, T> {
		
		T convert(F from);
		
		//T convert1(F from);
	}
	
	public static void main(String[] args) {

		double d = 1.61;
		System.out.println((int)d);
		
		Converter<String, Integer> converter = (from) -> Integer.valueOf
				(from);
		Integer converted = converter.convert("123");
		System.out.println(converted); // 123
		
		
		/*List<String> strList = Arrays.asList("a","b","c");
		Collections.sort(strList, new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				return o2.compareTo(o1);
			}
		});
		System.out.println(strList);
		
		Collections.sort(strList, (String o1,String o2) -> {
			return o1.compareTo(o2);
		});
		System.out.println(strList);
		
		Collections.sort(strList,(String o1,String o2) -> o2.compareTo(o1));
		System.out.println(strList);
		
		Collections.sort(strList,(o1,o2) -> o1.compareTo(o2));
		System.out.println(strList);*/
	}
}
