package main.stuff;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Driver {

	public static void main(String[] args) {
		String hashClass = args[0];
		String hashBuildFile = args[1];

		String hashSearchFile = args[2];

		try {
			// Harnessing the power of reflection to dynamically load a class. It's quite fascinating how much flexibility Java provides.
			// However, the dependence on the fully qualified class name is crucial here. One wrong character and we have a crash on our hands.
			Class<?> clazz = Class.forName(hashClass);

			// Doing a quick check to make sure our class is a valid subclass of MyHash. An informative error message helps with debugging in case it's not.
			// However, maybe there's room for more guidance here, like what the user should do next?
			if (!MyHash.class.isAssignableFrom(clazz)) {
				System.err.println(hashClass + " is not a subclass of MyHash");
				return;
			}

			// Using reflection again to find the constructor. We're assuming it takes a single String parameter. But what happens if it doesn't exist?
			// Handling the possible exception is a start, but perhaps this could be improved.
			Constructor<?> constructor = clazz.getConstructor(String.class);

			// It's time to construct the hash table and measure how long it takes. Are milliseconds sufficient for our needs? Or should we be using nanoTime() for more precision?
			long start = System.currentTimeMillis();

			// Instantiate the class! This step involves several potential pitfalls and the exceptions reflect that. But when it goes right, we get our shiny MyHash subclass.
			MyHash myHashInstance = (MyHash) constructor.newInstance(hashBuildFile);

			// The hash table is built, but how long did it take? This single measurement may not tell the whole story.
			// Are we going to compare this number with other hash implementations? Or maybe we should repeat this process and average the times?
			long buildHash = System.currentTimeMillis() - start;

			System.out.println("The algorithm " + hashClass + " took " + buildHash + "ms to build its hash table.");

			// Time to test out our hash table! Let's start with some lookups.
			BufferedReader reader = new BufferedReader(new FileReader(new File(System.getProperty("user.dir"), hashSearchFile).getAbsolutePath()));
			String line;
			int notFoundCount = 0;
			while ((line = reader.readLine()) != null) {
				String str = line.trim();
				if (!str.isEmpty()) {
					try {
						myHashInstance.find(str);
					} catch (NotFoundException e) {
						// It seems we couldn't find an item. Let's keep track of these for our curiosity's sake.
						System.out.println("Could not find: " + str);
						++notFoundCount;
					} catch (TooFullException e) {
						// Now, this is interesting. A TooFullException during a find operation. Is this a sign of inefficient hashing?
						// Let's treat it as another 'not found' for our record keeping.
						System.out.println("Gave up trying to find: " + str);
						++notFoundCount;
					}
				}
			}
			reader.close();

			// Let's keep track of how many items we couldn't find. This could provide insight into the effectiveness of our hash function.
			System.out.println("Could not find count: " + notFoundCount);

		} catch (ClassNotFoundException e) {
			// A case of 'class not found' in a class loader. The irony doesn't escape us, but this is a crucial reminder of the importance of providing the correct class name.
			System.err.println("Class " + hashClass + " not found");
		} catch (NoSuchMethodException e) {
			// This reminds us to be clear about our class requirements. It appears we were expecting a constructor with a single String parameter. 
			System.err.println("No suitable constructor found in class " + hashClass);
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			// This mishap is a reminder of how many things can go wrong while instantiating a class through reflection.
			System.err.println("Error creating instance of class " + hashClass);
		} catch (FileNotFoundException e) {
			// An intriguing point of failure. The file wasn't found - was it ever there, to begin with?
			// Perhaps we need to double-check the file path we're providing, or ensure the file exists before we try to open it.
			e.printStackTrace();
		} catch (IOException e) {
			// Ah, an IOException. These types of exceptions are like gatekeepers, reminding us that file operations aren't always straightforward.
			// Reading from a file can fail for various reasons. Perhaps the file was unexpectedly closed? Or maybe there was a problem with the disk?
			// Honestly, I've never actually had this one fail when there was a FileNotFoundException being caught ahead, but Java forces my hand!
			e.printStackTrace();
		}


	}

}
