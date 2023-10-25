package DSA_GIT_Eclipse_Package;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Java8Practice {

	//Practice Java8
	
	
	
	public static void main(String[] args) {
		practiceStreamAPI();
	}
	
	//Practice Stream API 
	// https://www.youtube.com/playlist?list=PLeDFPvjs51fPThsCeLo2wdnQqGjj_ayZ0
	static void practiceStreamAPI() {
		List<String> strList = Arrays.asList("ak", "bk", "Ck", "Dk");
		List<Integer> intList = new ArrayList<>(); intList.addAll(Arrays.asList(2,5,3,2,3,8));
		
		int[] arr = new int[] {3,6,1,8,0}; // int[] arr = {3,6,1,8,0}; also works
		System.out.print(" range() Demo: ");
			IntStream.range(1, 10).forEach(System.out::print);
		System.out.print("\n rangeClosed() Demo : ");
			IntStream.rangeClosed(1, 10).parallel().forEach(n -> System.out.print(n));
		System.out.print("\n forEachOrdered() : ");
			"akshay".chars().parallel().forEachOrdered(s -> System.out.print((char) s));
		System.out.print("\n String Lambda : ");
			IntStream.range(0, strList.size())
				.forEach( 
				i -> { String s = strList.get(i);   
						System.out.print(s); 
					}	);
		System.out.print("\n iterate() limit(): ");
			IntStream.iterate(0, i->i+2)
				.limit(10)
				.forEach(System.out::print);
		System.out.print("\n of()");		
			IntStream.of(6).forEach(System.out::print);
			IntStream.of(1,2,3,4,5).forEach(System.out::print);
		System.out.print("\n generate() : ");
			IntSupplier intSupplier = () -> (int) (Math.random() * 100);
			IntStream myStream = IntStream.generate(intSupplier);
			//Method 1 : myStream.limit(6).forEach(System.out::print);
			//Method 2 myStream.limit(6).forEach(i -> System.out.print(i + " "));
			String ans = myStream.limit(6).mapToObj( Integer::toString ).collect(Collectors.joining(" "));
				System.out.println(ans);
		System.out.print("\n min() / max() : ");
			OptionalInt minElement = Arrays.stream(arr).min(); // max()
			minElement.ifPresentOrElse( (n) -> System.out.print(n), ()-> System.out.print("No min"));
			//minElement.getAsInt() gives exception if no value
		System.out.print("\n distinct(): ");
			intList.stream().distinct().forEach((n) -> System.out.print(n) );
		System.out.print("\n Predicate() | filter() ");
			Predicate<Integer> isOddpredicate = new Predicate<Integer>() {
					@Override
					public boolean test(Integer num) {
						return num%2!=0;
					}
				};
			IntPredicate isEvenPredicate = (num) -> num%2==0;
			
			IntStream.range(0, 10).mapToObj(Integer::valueOf)
				.filter(isOddpredicate).forEach(System.out::print); //int Stream => needs IntPredicate
			intList.stream().filter(isOddpredicate).forEach(System.out::print);//Integer stream > needs Predicate<Integer>

		System.out.print("\n sorted() | skip() : ");
			IntStream.of(4,6,2,0,9,4,7).sorted().limit(5).skip(2).forEach(System.out::print);
			intList.stream().sorted(Comparator.reverseOrder()).forEach(System.out::print);
	
		System.out.print("\n Function<> | skip() : ");
			Function<Integer, Integer> fSquared = (num) -> num*num;
			List<Integer> ansSquared = intList.stream().map(fSquared).collect(Collectors.toList());
			System.out.println(ansSquared.toString());		
	}

}
