public class f{
	public static int f(int x, int y){
		int a = x+1;
		if(x == 0){
			return g(y, x, y);
		}
		else{
			return a + g(f(x-1, y), x, y);
		}
	}
	
	public static int g(int n, int x, int y){
		int a = x+y;
		if(n == 0){	
			return 0;
		}
		return h((n-1), n, x, y, a);
	}
	
	public static int h(int k, int n, int x, int y, int a){
		if(k==0){
			return 0;
		}
		return (n+a+g((n-1), x, y));
	}
	
	public static void main(String args[]){
		int x = Integer.parseInt(args[0]);
		int y = Integer.parseInt(args[1]);
		
		System.out.println("Answer = " + f(x,y));
	}
}

