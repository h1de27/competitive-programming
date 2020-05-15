import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;

public class JOIC6 {
    static InputStream is;
    static PrintWriter out;
    static String INPUT = "";

    static void solve() {
        int N = ni();
        Set<Point> points = new HashSet<>();
        for (int i = 0; i < N; i++) {
            points.add(new Point(ni(), ni()));
        }
        // メモリ削減
        Point buf1=new Point(0,0),buf2=new Point(0,0);

        long ans = 0;
        // ２点間を全探索。
        for (Point i : points) {
            for (Point j : points) {
                // Vector の算出。
                int[] vector = {j.x - i.x, j.y - i.y};
                buf1.x = i.x - vector[1]; buf1.y = i.y + vector[0];
                buf2.x = j.x - vector[1]; buf2.y = j.y + vector[0];
                // ArrayList でなければ、中身を比較不可能（配列は使えない）。
                if (points.contains(buf1)
                        && points.contains(buf2)) {
                    ans = Math.max(ans, (long) Math.pow(vector[0], 2) + (long) Math.pow(vector[1], 2));
                }
            }
        }
        out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        long S = System.currentTimeMillis();
        is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
        out = new PrintWriter(System.out);

        solve();
        out.flush();
        long G = System.currentTimeMillis();
        tr(G-S+"ms");
    }

    private static byte[] inbuf = new byte[1024];
    static int lenbuf = 0, ptrbuf = 0;

    private static int readByte() {
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

    private static int ni() {
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

    private static void tr(Object... o) { if(INPUT.length() != 0)System.out.println(Arrays.deepToString(o)); }
}
