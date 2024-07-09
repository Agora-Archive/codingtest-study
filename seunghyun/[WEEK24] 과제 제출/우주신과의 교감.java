import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Path implements Comparable<Path> {
		int v; double w;	// vertex num, weight from adjacnet vertex
		Path( int v, double w ) {
			this.v = v;
			this.w = w;
		}
		@Override
		public int compareTo( Path o ) {
			return Double.compare( this.w, o.w );
		}
	}

	static class Node {
		int u, v;  double w;
		Node( int u, int v, double w ) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}

	public static double spanning_prim( List<List<Node>> e, int n ) {
		int u, v, i; double w_u, w, w_sum = 0;
		final double max = Integer.MAX_VALUE;
		boolean c[] = new boolean[n];
		double d[] = new double[n];
		for( i = 1; i < n; i++ )
			d[i] = max;
		PriorityQueue<Path> pq = new PriorityQueue<>();
		pq.add( new Path( 0, 0 ) );
		while( !pq.isEmpty() ) {
			Path p = pq.remove();
			u = p.v; w_u = p.w;
			if( d[u] < w_u ) continue;
			if( c[u] ) continue;
			c[u] = true;
			w_sum += w_u;
			for( Node nd : e.get(u) ) {
				v = nd.v;	// connectnd vertex num
				w = nd.w;	// connectnd weight
				if( d[v] > w && !c[v] ) {
					pq.add( new Path( v, w ) );
					d[v] = w;
				}
			}
		}
		return w_sum;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
		StringTokenizer st = new StringTokenizer( br.readLine() );
		int n = Integer.parseInt( st.nextToken() ),
		    m = Integer.parseInt( st.nextToken() ), i, j;
		long p[][] = new long[n][2];
		for( i = 0; i < n; i++ ) {
			st = new StringTokenizer( br.readLine() );
			for( j = 0; j < 2; j++ )
				p[i][j] = Long.parseLong( st.nextToken() );
		}

		List<List<Node>> e = new LinkedList<>();	// edge
		List<List<Integer>> a = new LinkedList<>(); // already connected edge
		for( i = 0; i < n; i++ )
			e.add( new LinkedList<Node>() );
		for( i = 0; i < n; i++ )
			a.add( new LinkedList<Integer>() );
		int p1, p2;
		for( i = 0; i < m; i++ ) {
			st = new StringTokenizer( br.readLine() );
			p1 = Integer.parseInt( st.nextToken() ) - 1;
			p2 = Integer.parseInt( st.nextToken() ) - 1;
			a.get( p1 ).add( p2 );
			a.get( p1 ).add( p1 );
			e.get( p1 ).add( new Node( p1, p2, 0 ) );
			e.get( p2 ).add( new Node( p2, p1, 0 ) );
		}
		br.close();

		double w; long x_d, y_d;
		for( i = 0; i < n-1; i++ ) {
			loop:
			for( j = i+1; j < n; j++ ) {
				if( a.get(i).contains(j) )
					continue loop;
				x_d = p[j][0] - p[i][0];
				y_d = p[j][1] - p[i][1];
				w = Math.sqrt( x_d * x_d + y_d * y_d );
				e.get( i ).add( new Node( i, j, w ) );
				e.get( j ).add( new Node( j, i, w ) );
			}
		}

		System.out.printf( "%.2f", spanning_prim( e, n ) );

	}
}