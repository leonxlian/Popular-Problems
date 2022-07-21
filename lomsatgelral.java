import java.io.*;
import java.util.*;
public class lomsatgelral{
	public static PrintWriter out;
	static long ans[];
	static int n;
	static int color[];
	static boolean vis[];
	static ArrayList<ArrayList<Integer>>al=new ArrayList<>();
	public static void main(String[] args)throws IOException{
		Scanner sc=new Scanner();
		out=new PrintWriter(System.out);
		n=sc.nextInt();
		color=new int[n];
		for(int i=0;i<n;i++) {
			color[i]=sc.nextInt();
		}
		for(int i=0;i<n;i++) {
			al.add(new ArrayList<Integer>());
		}
		for(int i=0;i<n-1;i++) {
			int x=sc.nextInt()-1;
			int y=sc.nextInt()-1;
			al.get(x).add(y);
			al.get(y).add(x);
		}
		ans=new long[n];
		vis=new boolean[n];
		dfs(0);
		for(long i:ans) {
			out.print(i+" ");
		}
		out.close();
	}
	static Pair dfs(int cur) {
		vis[cur] = true;
		HashMap<Integer, Integer>hm=new HashMap<>();
		long ret=color[cur];
		hm.put(-1, 1);
		hm.put(color[cur], 1);
		for(int next:al.get(cur)) {
			if(!vis[next]) {
				Pair p=dfs(next);//get values of children
				HashMap<Integer, Integer>hm1=p.hm;
				if(hm.size()<hm1.size()) {//do swaps so that smaller is the child
					HashMap<Integer, Integer>temp=hm;
					hm=hm1;
					hm1=temp;
					ret=p.sum;
				}
				int count=hm.get(-1);//max number of some color
				long sum=ret;//current ans at this node
				for(int key:hm1.keySet()) {//merging process
					if(key==-1) {
						continue;
					}
					int r1=hm.getOrDefault(key, 0);
					r1+=hm1.get(key);//combine sum
					if(r1>count) {//if total num of this color is the highest, make updates
						count=r1;
						sum=key;
					}else if(r1==count) {//if equal to the count add
						sum+=key;//make updates to the answer
					}
					hm.put(key, r1);
				}
				hm.put(-1, count);//stores the current running max
				ret=sum;
			}
		}
		ans[cur]=ret;
		return new Pair(ret, hm);
	}
	static class Pair{
		long sum;
		HashMap<Integer, Integer>hm;
		public Pair(long sum, HashMap<Integer, Integer>hm) {
			this.sum=sum;
			this.hm=hm;
		}
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
