public class main2{
	public static void main (String [] args)
	{
		queen2 q = new queen2();
		if (args.length > 0)
		{
			q.fileName = args[0];
			q.registerMen();
		}
		else
			System.out.println("Please enter filename");
	}
}