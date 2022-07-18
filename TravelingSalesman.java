import java.io.*;
import java.util.*;
public class TravelingSalesman {
	public static PrintWriter out;
	public static void main(String[] args)throws IOException{
		Scanner sc=new Scanner();
		out=new PrintWriter(System.out);
		int t=sc.nextInt();
		while(t-->0) {
			int n=sc.nextInt();
			int m=sc.nextInt();
			HashMap<String, Integer>hm=new HashMap<>();
			for(int i=0;i<m;i++) {
				int x=sc.nextInt()-1;
				int y=sc.nextInt()-1;
				int val=sc.nextInt();
				hm.put(x+" "+y, val);
				hm.put(y+" "+x, val);
			}
			int dp[][]=new int[(1<<n)][n];
			for(int mask=2;mask<(1<<n);mask++) {
				for(int j=0;j<n;j++) {
					if(((mask>>j)&1)==0) {//loop through what current node is
						continue;
					}
					int prev=mask-(1<<j);
					dp[mask][j]=Integer.MAX_VALUE;
					for(int k=0;k<n;k++) {
						if(((prev>>k)&1)==0){
							continue;
						}
						if(dp[prev][k]!=Integer.MAX_VALUE) {
							int cur=dp[prev][k]+hm.get(k+" "+j);
							dp[mask][j]=Math.min(dp[mask][j], cur);
						}
					}
				}
			}
			int ans=Integer.MAX_VALUE;
			for(int i=0;i<n;i++) {
				if(hm.containsKey((0+" "+i))) {
					ans=Math.min(dp[(1<<n)-1][i]+hm.get(0+" "+i), ans);
				}
			}
			out.println(ans);
		}
		out.close();
	}
	public static class Scanner {
	    BufferedReader br;
	    StringTokenizer st;
	
	    public Scanner() {
	        br = new BufferedReader(new InputStreamReader(System.in));
	    }

	    String next() {
	        while (st == null || !st.hasMoreElements()) {
	            try {
	                st = new StringTokenizer(br.readLine());
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        return st.nextToken();
	    }
	
	    int nextInt() {
	        return Integer.parseInt(next());
	    }
	
	    long nextLong() {
	        return Long.parseLong(next());
	    }
	
	    double nextDouble() {
	        return Double.parseDouble(next());
	    }
	
	    String nextLine(){
	        String str = "";
	        try {
	            str = br.readLine();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return str;
	    }
	}
}
