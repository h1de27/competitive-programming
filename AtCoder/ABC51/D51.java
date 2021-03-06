import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class D51 {
    static InputStream is;
    static PrintWriter out;
    static String INPUT = "";

    static void solve()
    {
        int inf = Integer.MAX_VALUE;
        int n = ni();
        int m = ni();

        int a[] = new int[1000], b[] = new int[1000], c[] = new int[1000], dist[][] = new int[100][100];

        for (int i = 0; i < m; i++) {
            a[i] = ni();
            b[i] = ni();
            c[i] = ni();
            a[i]--;
            b[i]--;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    dist[i][j] = 0;
                else
                    dist[i][j] = inf;
            }
        }

        for (int i = 0; i < m; i++) {
            dist[a[i]][b[i]] = Math.min(dist[a[i]][b[i]], c[i]);
            dist[b[i]][a[i]] = Math.min(dist[b[i]][a[i]], c[i]);
        }

        for (int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                if (dist[i][k] == Integer.MAX_VALUE) {
                    continue;
                }
                for(int j = 0; j < n; j++) {
                    if (dist[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int ans = m;
        for(int i = 0; i < m; i++) {
            boolean shortest = false;
            for (int j = 0; j < n; j++) if (dist[j][a[i]] + c[i] == dist[j][b[i]]) shortest = true;
            if(shortest) {
                ans = ans - 1;
            }
        }

        out.println(ans);
    }

    public static void main(String[] args) throws Exception
    {
        long S = System.currentTimeMillis();
        is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
        out = new PrintWriter(System.out);

        solve();
        out.flush();
        long G = System.currentTimeMillis();
        tr(G-S+"ms");
    }

    private static boolean eof()
    {
        if(lenbuf == -1)return true;
        int lptr = ptrbuf;
        while(lptr < lenbuf)if(!isSpaceChar(inbuf[lptr++]))return false;

        try {
            is.mark(1000);
            while(true){
                int b = is.read();
                if(b == -1){
                    is.reset();
                    return true;
                }else if(!isSpaceChar(b)){
                    is.reset();
                    return false;
                }
            }
        } catch (IOException e) {
            return true;
        }
    }

    private static byte[] inbuf = new byte[1024];
    static int lenbuf = 0, ptrbuf = 0;

    private static int readByte()
    {
        if(lenbuf == -1)throw new InputMismatchException();
        if(ptrbuf >= lenbuf){
            ptrbuf = 0;
            try { lenbuf = is.read(inbuf); } catch (IOException e) { throw new InputMismatchException(); }
            if(lenbuf <= 0)return -1;
        }
        return inbuf[ptrbuf++];
    }

    private static boolean isSpaceChar(int c) { return !(c >= 33 && c <= 126); }

    private static int skip() { int b; while((b = readByte()) != -1 && isSpaceChar(b)); return b; }

    private static double nd() { return Double.parseDouble(ns()); }
    private static char nc() { return (char)skip(); }

    private static String ns()
    {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while(!(isSpaceChar(b))){
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    private static char[] ns(int n)
    {
        char[] buf = new char[n];
        int b = skip(), p = 0;
        while(p < n && !(isSpaceChar(b))){
            buf[p++] = (char)b;
            b = readByte();
        }
        return n == p ? buf : Arrays.copyOf(buf, p);
    }

    private static char[][] nm(int n, int m)
    {
        char[][] map = new char[n][];
        for(int i = 0;i < n;i++)map[i] = ns(m);
        return map;
    }

    private static int[] na(int n)
    {
        int[] a = new int[n];
        for(int i = 0;i < n;i++)a[i] = ni();
        return a;
    }

    private static int ni()
    {
        int num = 0, b;
        boolean minus = false;
        while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if(b == '-'){
            minus = true;
            b = readByte();
        }

        while(true){
            if(b >= '0' && b <= '9'){
                num = num * 10 + (b - '0');
            }else{
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    private static long nl()
    {
        long num = 0;
        int b;
        boolean minus = false;
        while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if(b == '-'){
            minus = true;
            b = readByte();
        }

        while(true){
            if(b >= '0' && b <= '9'){
                num = num * 10 + (b - '0');
            }else{
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    private static void tr(Object... o) { if(INPUT.length() != 0)System.out.println(Arrays.deepToString(o)); }

    // Union-Find
    // https://algs4.cs.princeton.edu/15uf/UF.java.html
    public static class UnionFind {
        private int count = 0;
        private int[] parent, rank;

        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int p) {
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP ==rootQ) return;
            if (rank[rootQ] > rank[rootP]) {
                parent[rootP] = rootQ;
            } else {
                parent[rootQ] = rootP;
                if (rank[rootP] == rank[rootQ]) {
                    rank[rootP]++;
                }
            }
            count--;
        }

        public int count() {
            return count;
        }

        public void print() {
            out.println("Parent:");
            for (int i : parent) {
                out.print(i);
                out.print(" ");
            }
            out.println();
            out.println("Rank:");
            for (int i : rank) {
                out.print(i);
                out.print(" ");
            }
            out.println();
        }
    }

    // Dijkstra
    // Return min distance from start to end O(ElogV) (negative cost is prohibited)
    // edge is int[3] array {from,to,cost}
    // edges is edge list from specific node
    // all_edges is Map<from node number,edges>
    static int dijkstra(Map<Integer,List<int[]>> all_edges,int start,int end,int max_node_number) {
        int[] distance = new int[max_node_number + 1];
        Arrays.fill(distance, -1);
        PriorityQueue<int[]> p_que = new PriorityQueue<>((a,b) -> ((distance[a[0]] + a[2]) - (distance[b[0]] + b[2])));
        distance[start] = 0;
        List<int[]> edges = all_edges.get(start);
        p_que.addAll(edges);
        while (distance[end] < 0) {
            int[] nearest_edge = p_que.poll();
            assert nearest_edge != null;
            if (distance[nearest_edge[1]] < 0) {
                distance[nearest_edge[1]] = distance[nearest_edge[0]] + nearest_edge[2];
                if (all_edges.containsKey(nearest_edge[1])) {
                    edges = all_edges.get(nearest_edge[1]);
                    for (int[] edge : edges) {
                        if (distance[edge[1]] < 0) p_que.add(edge);
                    }
                }
            }
        }
        return distance[end];
    }
}
