package arprast.qiyosq.test;

public class MissInteger {

    public static void main(String[] args) {
        System.out.println(solution(new int[] { 1, 3, 1, 4, 2, 3, 5, 4 }));
        System.out.println(solution(new int[] { 1, 3, 1, 4, 5, 3, 2, 5 }));

    }

    public static int solution(int[] A) {
        int n = A.length;
        int result = 0;
        for (int i = 0; i < n - 1; i++) {
            if (A[i] == A[i + 1])
                result = result + 1;
        }
        int r = 0;
        for (int i = 0; i < n; i++) {
            int count = 0;
            if (i > 0) {
                if (A[i - 1] != A[i])
                    count = count + 1;
                else
                    count = count - 1;
            }
            if (i < n - 1) {
                if (A[i + 1] != A[i])
                    count = count + 1;
                else
                    count = count - 1;
            }
            r = Math.max(r, count);
        }
        return result + r;
    }
}
