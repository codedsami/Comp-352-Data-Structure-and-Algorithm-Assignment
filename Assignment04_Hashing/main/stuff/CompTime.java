package main.stuff;

import java.lang.reflect.Constructor;

public class CompTime {

	public static void main(String[] args) {
		String hashClass1 = args[0];
		String hashClass2 = args[1];
		String hashBuildFile = args[2];

		try {
			Class<?> clazz1 = Class.forName(hashClass1);
			Class<?> clazz2 = Class.forName(hashClass2);

			if (!MyHash.class.isAssignableFrom(clazz1)) {
				System.err.println(hashClass1 + " is not a subclass of MyHash");
				return;
			}
			
			if (!MyHash.class.isAssignableFrom(clazz2)) {
				System.err.println(hashClass2 + " is not a subclass of MyHash");
				return;
			}
			
			

			Constructor<?> constructor1 = clazz1.getConstructor(String.class);
			Constructor<?> constructor2 = clazz2.getConstructor(String.class);
			

			long start = System.currentTimeMillis();

			MyHash myHashInstance1 = (MyHash) constructor1.newInstance(hashBuildFile);

			long buildHash1 = System.currentTimeMillis() - start;
			
			start = System.currentTimeMillis();
			MyHash myHashInstance2 = (MyHash) constructor2.newInstance(hashBuildFile);
			long buildHash2 = System.currentTimeMillis() - start;

			System.out.println("HashSpeed: " + (int)(((float)Math.max(0,buildHash1-buildHash2)/buildHash1)*100));
			System.out.println("HashSize: " + (int)(((float)myHashInstance1.getTableSize()/myHashInstance2.getTableSize())*100));

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Things went sideways: " + e.getMessage());
		}

	}

}
