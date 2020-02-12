public class main{
	public static void main (String [] args)
	{
		queen q = new queen();
		if (args.length > 0)
		{
			q.fileName = args[0];
			q.registerMen();
		}
		else
			System.out.println("Please enter filename");
	}
}